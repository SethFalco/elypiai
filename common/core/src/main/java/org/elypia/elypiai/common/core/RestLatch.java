/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.common.core;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @param <T> The type of response to recieve and manage from this instance.
 * @author seth@elypia.org (Syed Shah)
 */
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
