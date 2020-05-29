/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.osu;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.elypia.elypiai.osu.data.*;
import org.elypia.elypiai.osu.deserializers.*;
import org.elypia.elypiai.osu.impl.OsuService;
import org.elypia.retropia.core.*;
import org.elypia.retropia.core.extensions.*;
import org.elypia.retropia.core.requests.*;
import org.elypia.retropia.gson.deserializers.DateDeserializer;
import org.slf4j.*;
import retrofit2.Call;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Osu extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(Osu.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL baseUrl;

	static {
		try {
			baseUrl = new URL("https://osu.ppy.sh/api/");
		} catch (MalformedURLException ex) {
			logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
		}
	}

	private final String API_KEY;
	private final OsuService service;

	public Osu(String apiKey) {
		this(apiKey, new WrapperExtension[0]);
	}

	/**
	 * Creates an osu! object for making calls to the osu API.
	 * Using this you can call information on each user, as well
	 * as beatmaps, and recentplay data for users.
	 *
	 * @param 	apiKey	The API obtained from the osu! website.
	 */
	public Osu(String apiKey, WrapperExtension... exts) {
		this(baseUrl, apiKey, exts);
	}

	public Osu(URL baseUrl, String apiKey, WrapperExtension... exts) {
		super(exts);
		API_KEY = Objects.requireNonNull(apiKey);

		OkHttpClient client = RequestService.getBuilder()
			.addInterceptor((chain) -> {
				Request request = chain.request();
				HttpUrl url = request.url().newBuilder().addQueryParameter("k", apiKey).build();
				request = request.newBuilder().url(url).build();
				return chain.proceed(request);
			})
			.addInterceptor(new ExtensionInterceptor(exts))
			.build();

		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(new TypeToken<List<OsuMod>>(){}.getType(), new OsuModDeserializer())
			.registerTypeAdapter(Date.class, new DateDeserializer("yyyy-MM-dd HH:mm:ss"));

		Gson gson = gsonBuilder.create();

		gsonBuilder
			.registerTypeAdapter(Player.class, new OsuPlayerDeserializer(gson))
			.registerTypeAdapter(Match.class, new OsuMatchDeserializer(gson))
			.registerTypeAdapter(new TypeToken<List<BeatMap>>(){}.getType(), new BeatMapDeserializer(gson));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.build()
			.create(OsuService.class);
	}

	public OptionalRestAction<Player> getPlayer(int id) {
		return getPlayer(id, OsuMode.OSU);
	}

	public OptionalRestAction<Player> getPlayer(String username) {
		return getPlayer(username, OsuMode.OSU);
	}

	public OptionalRestAction<Player> getPlayer(int id, OsuMode mode) {
		return getPlayer(id, mode, 31);
	}

	public OptionalRestAction<Player> getPlayer(String username, OsuMode mode) {
		return getPlayer(username, mode, 31);
	}

	/**
	 * Calls the osu! API for the a player by id.
	 *
	 * @param id The players username.
	 * @param mode The gamemode to view data for.
	 * @param days The number of days to look back for events.
	 */
	public OptionalRestAction<Player> getPlayer(int id, OsuMode mode, int days) {
		return getPlayer(String.valueOf(id), OsuId.USER_ID, mode, days);
	}

	/**
	 * Calls the osu! API for the a player by username.
	 *
	 * @param username The players username.
	 * @param mode The gamemode to view data for.
	 * @param days The number of days to look back for events.
	 */
	public OptionalRestAction<Player> getPlayer(String username, OsuMode mode, int days) {
		return getPlayer(username, OsuId.USERNAME, mode, days);
	}

	public OptionalRestAction<Player> getPlayer(String username, OsuId type, OsuMode mode, int days) {
		Call<Player> call = service.getPlayer(username, type.getType(), mode.getId(), days);
		return new OptionalRestAction<>(call);
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

	public OptionalRestAction<Match> getMatch(int id) {
		Call<Match> call = service.getMatch(id);
		return new OptionalRestAction<>(call);
	}

	public String getApiKey() {
		return API_KEY;
	}
}
