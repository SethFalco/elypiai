package com.elypia.elypiai.osu;

import com.elypia.elypiai.common.Elypiai;
import com.elypia.elypiai.common.RequestService;
import com.elypia.elypiai.common.RestAction;
import com.elypia.elypiai.common.gson.deserializers.DateDeserializer;
import com.elypia.elypiai.osu.data.OsuId;
import com.elypia.elypiai.osu.data.OsuMod;
import com.elypia.elypiai.osu.data.OsuMode;
import com.elypia.elypiai.osu.deserializers.BeatMapDeserializer;
import com.elypia.elypiai.osu.deserializers.OsuMatchDeserializer;
import com.elypia.elypiai.osu.deserializers.OsuModDeserializer;
import com.elypia.elypiai.osu.deserializers.OsuPlayerDeserializer;
import com.elypia.elypiai.osu.impl.OsuService;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.List;

public class Osu {

	private static final Logger logger = LoggerFactory.getLogger(Osu.class);

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
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private final String API_KEY;

	private final OsuService service;

	/**
	 * Creates an osu! object for making calls to the osu API.
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

		OkHttpClient client = RequestService.getBuilder().addInterceptor((chain) -> {
			Request request = chain.request();
			HttpUrl url = request.url().newBuilder().addQueryParameter("k", apiKey).build();
			request = request.newBuilder().url(url).build();
			return chain.proceed(request);
		}).build();

		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(new TypeToken<List<OsuMod>>(){}.getType(), new OsuModDeserializer())
			.registerTypeAdapter(Date.class, new DateDeserializer("yyyy-MM-dd HH:mm:ss"));

		Gson gson = gsonBuilder.create();

		gsonBuilder
			.registerTypeAdapter(Player.class, new OsuPlayerDeserializer(gson))
			.registerTypeAdapter(Match.class, new OsuMatchDeserializer(gson))
			.registerTypeAdapter(new TypeToken<List<BeatMap>>(){}.getType(), new BeatMapDeserializer(gson));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl.toString())
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.build()
			.create(OsuService.class);
	}

	public RestAction<Player> getPlayer(int id) {
		return getPlayer(id, OsuMode.OSU);
	}

	public RestAction<Player> getPlayer(String username) {
		return getPlayer(username, OsuMode.OSU);
	}

	public RestAction<Player> getPlayer(int id, OsuMode mode) {
		return getPlayer(id, mode, 31);
	}

	public RestAction<Player> getPlayer(String username, OsuMode mode) {
		return getPlayer(username, mode, 31);
	}

	/**
	 * Calls the osu! API for the a player by id.
	 *
	 * @param id The players username.
	 * @param mode The gamemode to view data for.
	 * @param days The number of days to look back for events.
	 */
	public RestAction<Player> getPlayer(int id, OsuMode mode, int days) {
		return getPlayer(String.valueOf(id), OsuId.USER_ID, mode, days);
	}

	/**
	 * Calls the osu! API for the a player by username.
	 *
	 * @param username The players username.
	 * @param mode The gamemode to view data for.
	 * @param days The number of days to look back for events.
	 */
	public RestAction<Player> getPlayer(String username, OsuMode mode, int days) {
		return getPlayer(username, OsuId.USERNAME, mode, days);
	}

	public RestAction<Player> getPlayer(String username, OsuId type, OsuMode mode, int days) {
		Call<Player> call = service.getPlayer(username, type.getType(), mode.getId(), days);
		return new RestAction<>(call);
	}

	public RestAction<List<BeatMap>> getBeatMaps(int id) {
		return getBeatMaps(id, OsuMode.OSU);
	}

	public RestAction<List<BeatMap>> getBeatMaps(int id, OsuMode mode) {
		return getBeatMaps(id, mode, 500);
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

	public RestAction<List<RecentPlay>> getRecentPlays(int id) {
		return getRecentPlays(id, OsuMode.OSU);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(String username) {
		return getRecentPlays(username, OsuMode.OSU);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(int id, OsuMode mode) {
		return getRecentPlays(id, mode, 50);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(String username, OsuMode mode) {
		return getRecentPlays(username, mode, 50);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(int id, OsuMode mode, int limit) {
		return getRecentPlays(String.valueOf(id), OsuId.USER_ID, mode, limit);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(String username, OsuMode mode, int limit) {
		return getRecentPlays(username, OsuId.USERNAME, mode, limit);
	}

	public RestAction<List<RecentPlay>> getRecentPlays(String user, OsuId type, OsuMode mode, int limit) {
		Call<List<RecentPlay>> call = service.getRecentPlays(user, type.getType(), mode.getId(), limit);
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
