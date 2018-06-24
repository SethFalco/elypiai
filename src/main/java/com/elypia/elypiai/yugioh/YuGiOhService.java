package com.elypia.elypiai.yugioh;

import retrofit2.Call;
import retrofit2.http.*;

public interface YuGiOhService {

    @GET("api/card_data/{name}")
    Call<YuGiOhCard> getCard(@Path("name") String name);
}
