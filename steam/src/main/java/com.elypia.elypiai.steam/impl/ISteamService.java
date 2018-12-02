package com.elypia.elypiai.steam.impl;

import com.elypia.elypiai.steam.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ISteamService {

    @GET("ISteamUser/ResolveVanityURL/v0001/")
    Call<SteamSearch> getSteamId(
        @Query("vanityUrl") String vanityUrl
    );

    @GET("ISteamUser/GetPlayerSummaries/v0002/")
    Call<List<SteamUser>> getUsers(
        @Query("steamids") String steamIds
    );

    @GET("IPlayerService/GetOwnedGames/v0001/")
    Call<List<SteamGame>> getLibrary(
        @Query("steamid") long steamId,
        @Query("include_played_free_games") int freeGames,
        @Query("include_appinfo") int appInfo
    );
}
