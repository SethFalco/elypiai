package com.elypia.elypiai.runescape.impl;

import com.elypia.elypiai.runescape.Player;
import com.elypia.elypiai.runescape.QuestStats;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface RuneScapeService {

    @GET("profile/profile")
    Call<Player> getUser(
        @Query("user") String user
    );

    @GET("quests")
    Call<List<QuestStats>> getQuestStats(
        @Query("user") String user
    );
}
