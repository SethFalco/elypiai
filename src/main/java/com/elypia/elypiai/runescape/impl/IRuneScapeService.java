package com.elypia.elypiai.runescape.impl;

import com.elypia.elypiai.runescape.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Collection;

public interface IRuneScapeService {

    @GET("profile/profile")
    Call<RuneScapeUser> getUser(@Query("user") String user);

    @GET("quests")
    Call<Collection<QuestStats>> getQuestStats(@Query("user") String user);
}
