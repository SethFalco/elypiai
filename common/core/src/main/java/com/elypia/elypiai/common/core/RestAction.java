package com.elypia.elypiai.common.core;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RestAction<T> implements RestInterface<T> {

    private Call<T> call;
    private List<Consumer<T>> pipes;

    public RestAction(Call<T> call) {
        this.call = call;
        this.pipes = new ArrayList<>();
    }

    @Override
    public void queue(Consumer<Optional<T>> success, Consumer<Throwable> ex) {
        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                T body = response.body();

                for (Consumer<T> pipe : pipes) {
                    try {
                        pipe.accept(body);
                    } catch (FriendlyException ex) {
                        onFailure(call, ex);
                        return;
                    }
                }

                if (success != null)
                    success.accept((body != null) ? Optional.of(body) : Optional.empty());

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (ex != null)
                    ex.accept(t);
            }
        });
    }

    @Override
    public Optional<T> complete() throws IOException {
        T body = call.execute().body();

        for (Consumer<T> pipe : pipes)
            pipe.accept(body);

        return (body != null) ? Optional.of(body) : Optional.empty();
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    /**
     * Add a post action to perform or process the result prior to
     * performing the success action.
     *
     * @param consumer The action to perform.
     */
    public void pipe(Consumer<T> consumer) {
        pipes.add(consumer);
    }
}
