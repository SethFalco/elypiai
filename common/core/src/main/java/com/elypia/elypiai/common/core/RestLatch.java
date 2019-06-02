package com.elypia.elypiai.common.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RestLatch<T> implements RestIterable<T>, Iterable<RestAction<T>> {

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
    public void queue(Consumer<List<Optional<T>>> success, Consumer<Throwable> ex) {
        if (restActions.isEmpty())
            return;

        new Thread(() -> {
            List<Optional<T>> results = new ArrayList<>();
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
