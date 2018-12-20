package com.elypia.elypiai.restutils;

import com.elypia.elypiai.restutils.impl.AbstractRestAction;
import retrofit2.*;

import java.io.IOException;
import java.util.function.Consumer;

public class RestAction<T> extends AbstractRestAction<T> {

    private Call<T> call;

    public RestAction(Call<T> call) {
        this.call = call;
    }

    @Override
    public void queue(Consumer<T> success, Consumer<Throwable> ex) {
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (success != null)
                    success.accept(response.body());

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (ex != null)
                    ex.accept(t);
            }
        });
    }

    @Override
    public T complete() throws IOException {
        return call.execute().body();
    }

    @Override
    public void cancel() {
        call.cancel();
    }
}
