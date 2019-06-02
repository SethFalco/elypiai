package com.elypia.elypiai.steam;

import com.elypia.elypiai.common.core.Elypiai;
import com.elypia.elypiai.common.core.RequestService;
import com.elypia.elypiai.common.core.RestAction;
import com.elypia.elypiai.steam.deserializers.SteamGameDeserializer;
import com.elypia.elypiai.steam.deserializers.SteamSearchDeserializer;
import com.elypia.elypiai.steam.deserializers.SteamUserDeserializer;
import com.elypia.elypiai.steam.impl.SteamService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Steam {

    /** A regular expression that matches against profile urls and returns the username or id. */
    private static final Pattern VANITY_URL = Pattern.compile("^(?:https?://)?steamcommunity\\.com/id/([^/]+)/?$");

    private static final Logger logger = LoggerFactory.getLogger(Steam.class);

    /**
     * The default URL we call too. <br>
     * Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.
     */
    private static URL BASE_URL;

    static {
        try {
            BASE_URL = new URL("http://api.steampowered.com/");
        } catch (MalformedURLException ex) {
            logger.error(Elypiai.MALFORMED, ex);
        }
    }

    private final String API_KEY;
    private final SteamService service;

    /**
     * The Steam API allows calls to basic Steam information
     * as well as user information such as query the inventory
     * or obtaining stats.
     *
     * @param apiKey API key obtained from Steam.
     */

    public Steam(String apiKey) {
        this(BASE_URL, apiKey);
    }

    public Steam(URL baseUrl, String apiKey) {
        API_KEY = Objects.requireNonNull(apiKey);

        OkHttpClient client = RequestService.getBuilder().addInterceptor((chain) -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }).build();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(SteamSearch.class, new SteamSearchDeserializer())
                .registerTypeAdapter(new TypeToken<List<SteamGame>>(){}.getType(), new SteamGameDeserializer())
                .registerTypeAdapter(new TypeToken<List<SteamUser>>(){}.getType(), new SteamUserDeserializer(this));

        service = new Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
                .create(SteamService.class);
    }

    /**
     * @param vanityUrl The custom url entirely, or custom url route after id.
     * @return The search result of if a user was found or not.
     */
    public RestAction<SteamSearch> getIdFromVanityUrl(String vanityUrl) {
        String name = getUsernameFromUrl(vanityUrl);
        Call<SteamSearch> call = service.getSteamId((name == null) ? vanityUrl : name);
        return new RestAction<>(call);
    }

    public RestAction<List<SteamUser>> getUsers(long...ids) {
        if (ids == null || ids.length == 0)
            throw new IllegalArgumentException("Must specify at least one user to fetch.");

        StringJoiner joiner = new StringJoiner(",");

        for (long id : ids)
            joiner.add(String.valueOf(id));

        Call<List<SteamUser>> call = service.getUsers(joiner.toString());
        return new RestAction<>(call);
    }

    /**
     * Get the Steam users Library, do note the first time
     * the method is called for each SteamProfile; it consumes
     * another API call, the Library is cached however upon
     * method call. This contains a list of games the steam user owns
     * (or has played for free games) sorted from RecentPlaytime, and
     * when RecentPlaytime is 0, from TotalPlaytime.
     *
     * @param id Steam user to obtain library for.
     */

    public RestAction<List<SteamGame>> getLibrary(long id) {
        return getLibrary(id, true);
    }

    public RestAction<List<SteamGame>> getLibrary(long id, boolean freeGames) {
        return getLibrary(id, freeGames, true);
    }

    public RestAction<List<SteamGame>> getLibrary(long id, boolean freeGames, boolean info) {
        Call<List<SteamGame>> call = service.getLibrary(id, freeGames ? 1 : 0, info ? 1 : 0);
        return new RestAction<>(call);
    }

    public static String getUsernameFromUrl(String vanityUrl) {
        Matcher matcher = VANITY_URL.matcher(vanityUrl);
        return (matcher.find()) ? matcher.group(1) : null;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
