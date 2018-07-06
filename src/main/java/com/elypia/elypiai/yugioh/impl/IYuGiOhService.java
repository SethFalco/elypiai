package com.elypia.elypiai.yugioh.impl;

import retrofit2.Call;
import retrofit2.http.*;

public interface IYuGiOhService {

    @GET("card_data/{name}")
    Call<TradingCard> getCard(@Path("name") String name);
}
