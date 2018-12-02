package com.elypia.elypiai.runescape.impl;

import com.elypia.elypiai.runescape.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IRuneScapeService {

    @GET("profile/profile")
    Call<Player> getUser(@Query("user") String user);

    @GET("quests")
    Call<List<QuestStats>> getQuestStats(@Query("user") String user);
}
