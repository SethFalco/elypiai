package com.elypia.elypiai.osu;

import com.elypia.elypiai.common.RestAction;
import com.elypia.elypiai.osu.data.OsuId;
import com.elypia.elypiai.osu.data.OsuMod;
import com.elypia.elypiai.osu.data.OsuMode;
import com.elypia.elypiai.osu.deserializers.BeatMapDeserializer;
import com.elypia.elypiai.osu.deserializers.OsuMatchDeserializer;
import com.elypia.elypiai.osu.deserializers.OsuModDeserializer;
import com.elypia.elypiai.osu.deserializers.OsuPlayerDeserializer;
import com.elypia.elypiai.osu.impl.IOsuService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Osu {

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://osu.ppy.sh/api/");
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	private final String API_KEY;

	private IOsuService service;
	private Collection<Player> cache;

	/**
	 * Creates an OSU object for making calls to the osu API.
	 * Using this you can call information on each user, as well
	 * as beatmaps, and recentplay data for users.
	 *
	 * @param 	apiKey	The API obtained from the osu! website.
	 */
	public Osu(String apiKey) {
		this(BASE_URL, apiKey);
	}

	public Osu(URL baseUrl, String apiKey) {
		API_KEY = apiKey;
		cache = new ArrayList<>();

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor((chain) -> {
			Request request = chain.request();
			HttpUrl url = request.url().newBuilder().addQueryParameter("k", apiKey).build();
			request = request.newBuilder().url(url).build();
			return chain.proceed(request);
		}).build();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(new TypeToken<List<OsuMod>>(){}.getType(), new OsuModDeserializer());
		gsonBuilder.registerTypeAdapter(Match.class, new OsuMatchDeserializer());
		gsonBuilder.registerTypeAdapter(Player.class, new OsuPlayerDeserializer());
		gsonBuilder.registerTypeAdapter(new TypeToken<List<BeatMap>>(){}.getType(), new BeatMapDeserializer());

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl.toString());
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

		service = retrofitBuilder.client(client).build().create(IOsuService.class);
	}

	/**
	 * Calls the OSU API for the osuplayer by id.
	 *
	 * @param 	id		The players id.
	 * @param	mode	The gamemode to view data for.
	 */
	public RestAction<Player> getPlayer(int id, OsuMode mode, int days) {
		return getPlayer(String.valueOf(id), OsuId.USER_ID, mode, days);
	}

	/**
	 * Calls the OSU API for the osuplayer by username.
	 *
	 * @param 	username	The players username.
	 * @param	mode		The gamemode to view data for.
	 */
	public RestAction<Player> getPlayer(String username, OsuMode mode, int days) {
		return getPlayer(username, OsuId.USERNAME, mode, days);
	}

	private RestAction<Player> getPlayer(String username, OsuId type, OsuMode mode, int days) {
		Call<Player> call = service.getPlayer(username, type.getType(), mode.getId(), days);
		return new RestAction<>(call);
	}

	/**
	 * Get the all BeatMap information associated with a BeatMap id.
	 *
	 * @param	id	The id of the beatmap to search grab.
	 */
	public RestAction<List<BeatMap>> getBeatMaps(int id, OsuMode mode, int limit) {
		Call<List<BeatMap>> call = service.getBeatMaps(id, mode.getId(), limit);
		return new RestAction<>(call);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(int id, OsuMode mode, int limit) {
		return getRecentPlays(String.valueOf(id), OsuId.USER_ID, mode, limit);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(String id, OsuMode mode, int limit) {
		return getRecentPlays(id, OsuId.USERNAME, mode, limit);
	}

	private RestAction<List<RecentPlay>> getRecentPlays(String id, OsuId type, OsuMode mode, int limit) {
		Call<List<RecentPlay>> call = service.getRecentPlays(id, type.getType(), mode.getId(), limit);
		return new RestAction<>(call);
	}

	public RestAction<Match> getMatch(int id) {
		Call<Match> call = service.getMatch(id);
		return new RestAction<>(call);
	}

	public String getApiKey() {
		return API_KEY;
	}
}
