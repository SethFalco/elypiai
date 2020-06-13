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

package org.elypia.elypiai.runescape;

import com.google.gson.GsonBuilder;
import io.reactivex.rxjava3.core.Maybe;
import okhttp3.OkHttpClient;
import org.elypia.elypiai.runescape.deserializers.PlayerDeserializer;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.exceptions.FriendlyException;
import org.elypia.retropia.gson.deserializers.TemporalDeserializer;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class RuneScape {

	private static final Logger logger = LoggerFactory.getLogger(RuneScape.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL baseUrl;

	static {
		try {
			baseUrl = new URL("https://apps.runescape.com/runemetrics/");
		} catch (MalformedURLException ex) {
			logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
		}
	}

	private RuneScapeService service;

	public RuneScape() {
		this(baseUrl);
	}

	public RuneScape(URL baseUrl) {
		this(baseUrl, HttpClientSingleton.getClient());
	}

	public RuneScape(URL baseUrl, OkHttpClient client) {
		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new TemporalDeserializer("dd-MMM-yyyy HH:mm"));

		gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer(gsonBuilder.create()));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.build()
			.create(RuneScapeService.class);
	}

	/**
	 * Return the RuneScape player with the username provided.
	 * Possible null, if user doesn't exist. If the user does exist
	 * it may throw a the consumer may throw a {@link FriendlyException}
	 * in the failure consumer.
	 *
	 * @param username The username of the player to get.
	 * @return A rest action represeting this HTTP request.
	 */
	public Maybe<Player> getUser(String username) {
		return service.getUser(username);
	}

	public Maybe<QuestStatuses> getQuestStatuses(String user) {
		return service.getQuestStatuses(user)
			.mapOptional((result) -> result.getQuestStatuses().isEmpty() ? Optional.empty() : Optional.of(result));
	}

    /**
     * Convert XP to the level equivilent.
     *
     * @param	xp      The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */
	public static int getLevelFromXp(long xp) {
		if (xp < 0)
			throw new IllegalArgumentException("XP can not be of a negative value.");

		int level = 1;
		long result;

		while (xp >= (result = getXpFromLevel(level + 1))) {
			if (result == -1)
				break;

			level++;
		}

		return level;
	}

	/**
	 * Convert a level, or virtual level to the XP equivilent using
	 * RuneScapes XP formula. <br>
	 * Note: Returns -1 if the level is too high.
	 *
	 * @param   level	The level to convert to XP.
	 * @return			The XP required to attain this level.
	 */
	public static long getXpFromLevel(int level) {
		if (level < 1)
			throw new IllegalArgumentException("Level can not be zero or a negative value.");

		double xp = 0;

		for (int count = 1; count < level; count++) {
			xp += (long)(count + 300 * Math.pow(2, (double)count / 7));

			if (xp >= (double)Long.MAX_VALUE * 4)
				return -1;
		}

		return (long)(xp / 4);
	}
}
