package com.elypia.elypiai.yugioh;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface YuGiOhService {

    @GET("card_data/{name}")
    Call<TradingCard> getCard(
        @Path("name") String name
    );
}
