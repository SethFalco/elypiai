package com.elypia.elypiai.utils.okhttp.impl;

import java.io.IOException;
import java.util.function.Consumer;

public interface IRestAction<T> {

    Consumer<Throwable> DEFAULT_FAILURE = Throwable::printStackTrace;

    void queue();
    void queue(Consumer<T> success);
    void queue(Consumer<T> success, Consumer<Throwable> ex);
    T complete() throws IOException;
    void cancel();
}
