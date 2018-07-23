package com.elypia.elypiai.utils.okhttp;

import com.elypia.elypiai.utils.okhttp.impl.AbstractRestAction;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class RestLatch<T> extends AbstractRestAction<List<T>> implements Iterable<RestAction<T>> {

    private final int TIMEOUT;
    private final boolean PARTIAL;

    private List<RestAction<T>> restActions;
    private boolean cancelled;

    public RestLatch() {
        this(4096);
    }

    public RestLatch(int timeout) {
        this(timeout, true);
    }

    public RestLatch(int timeout, boolean partial) {
        TIMEOUT = timeout;
        PARTIAL = partial;

        restActions = new ArrayList<>();
    }

    public void add(RestAction<T> restAction) {
        restActions.add(restAction);
    }

    @Override
    public void queue(Consumer<List<T>> success, Consumer<Throwable> ex) {
        if (restActions.isEmpty())
            throw new IllegalStateException("No requests added to latch prior to execution.");

        new Thread(() -> {
            List<T> results = new ArrayList<>();
            CountDownLatch latch = new CountDownLatch(restActions.size());

            forEach((restAction) -> {
                restAction.queue((result) -> {
                    results.add(result);
                    latch.countDown();
                }, (e) -> {
                    if (!PARTIAL)
                        cancel();

                    latch.countDown();
                });
            });

            try {
                latch.await(TIMEOUT, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                cancel();

                if (!PARTIAL || results.isEmpty()) {
                    ex.accept(e);
                    return;
                }
            }

            success.accept(results);
        }).start();
    }

    @Override
    public List<T> complete() throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public void cancel() {
        if (!cancelled) {
            restActions.forEach(RestAction::cancel);
            cancelled = true;
        }
    }

    @Override
    public Iterator<RestAction<T>> iterator() {
        return restActions.iterator();
    }
}
