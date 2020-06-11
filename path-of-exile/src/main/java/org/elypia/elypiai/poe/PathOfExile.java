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

package org.elypia.elypiai.poe;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.poe.data.*;
import org.elypia.elypiai.poe.deserializers.LadderEntryDeserializer;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.exceptions.InvalidEnumException;
import org.elypia.retropia.gson.deserializers.DateDeserializer;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class PathOfExile {

	private static final Logger logger = LoggerFactory.getLogger(PathOfExile.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL baseUrl;

	static {
		try {
			baseUrl = new URL("http://api.pathofexile.com/");
		} catch (MalformedURLException ex) {
			logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
		}
	}

	private PathOfExileService service;

	public PathOfExile() {
		this(baseUrl);
	}

	public PathOfExile(URL baseUrl) {
		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateDeserializer("yyyy-MM-dd'T'HH:mm:ss'Z'"));

		gsonBuilder.registerTypeAdapter(new TypeToken<List<LadderEntry>>(){}.getType(), new LadderEntryDeserializer(gsonBuilder.create()));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(HttpClientSingleton.getBuilder().build())
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.build()
			.create(PathOfExileService.class);
	}

	public Single<StashTabs> getStashTabs() {
		return getStashTabs(null);
	}

	public Single<StashTabs> getStashTabs(String cursor) {
		return  service.getStashTabs(cursor);
	}

	public Single<List<League>> getSeasonsLeagues(String season) {
		return getSeasonsLeagues(null, season);
	}

	public Single<List<League>> getSeasonsLeagues(Realm realm, String season) {
		return getSeasonsLeagues(realm, season, false);
	}

	public Single<List<League>> getSeasonsLeagues(Realm realm, String season, boolean compact) {
		return getSeasonsLeagues(realm, season, compact, getLeagueLimit(compact));
	}

	public Single<List<League>> getSeasonsLeagues(Realm realm, String season, boolean compact, int limit) {
		return getSeasonsLeagues(realm, season, compact, limit, 0);
	}

	public Single<List<League>> getSeasonsLeagues(Realm realm, String season, boolean compact, int limit, int offset) {
		return getLeagues(LeagueType.SEASON, realm, season, compact, limit, offset);
	}

	public Single<List<League>> getLeagues() {
		return getLeagues(null);
	}

	public Single<List<League>> getLeagues(LeagueType type) {
		return getLeagues(type, null);
	}

	public Single<List<League>> getLeagues(LeagueType type, Realm realm) {
		return getLeagues(type, realm, null);
	}

	public Single<List<League>> getLeagues(LeagueType type, Realm realm, String season) {
		return getLeagues(type, realm, season, false);
	}

	public Single<List<League>> getLeagues(LeagueType type, Realm realm, String season, boolean compact) {
		return getLeagues(type, realm, season, compact, getLeagueLimit(compact));
	}

	public Single<List<League>> getLeagues(LeagueType type, Realm realm, String season, boolean compact, int limit) {
		return getLeagues(type, realm, season, compact, limit, 0);
	}

	public Single<List<League>> getLeagues(LeagueType type, Realm realm, String season, boolean compact, int limit, int offset) {
		if (type == LeagueType.UNKNOWN)
			throw new InvalidEnumException(type);

		if (realm == Realm.UNKNOWN)
			throw new InvalidEnumException(realm);

		if (type == LeagueType.SEASON)
			Objects.requireNonNull(season, "Paramater `season` can not be null when `type` is `LeagueType.SEASON`.");

		return service.getLeagues(
			(type != null) ? type.getName() : null,
			(realm != null) ? realm.getName() : null,
			season,
			compact ? 1 : 0,
			limit,
			offset
		);
	}

	public Single<LeagueRule> getRule(String id) {
		return service.getLeagueRule(id);
	}

	public Single<List<LeagueRule>> getRules() {
		return service.getLeagueRules();
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id) {
		return getLeagueLadder(id, null);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm) {
		return getLeagueLadder(id, realm, 200);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit) {
		return getLeagueLadder(id, realm, limit, 0);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset) {
		return getLeagueLadder(id, realm, limit, offset, null);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type) {
		return getLeagueLadder(id, realm, limit, offset, type, true);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track) {
		return getLeagueLadder(id, realm, limit, offset, type, track, null);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track, String accountName) {
		return getLeagueLadder(id, realm, limit, offset, type, track, accountName, null);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track, String accountName, LabyrinthDifficulty difficulty) {
		return getLeagueLadder(id, realm, limit, offset, type, track, accountName, difficulty, null);
	}

	public Single<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track, String accountName, LabyrinthDifficulty difficulty, Date start) {
		if (realm == Realm.UNKNOWN)
			throw new InvalidEnumException(realm);

		if (type == LadderType.UNKNOWN)
			throw new InvalidEnumException(type);

		if (difficulty == LabyrinthDifficulty.UNKNOWN)
			throw new InvalidEnumException(difficulty);

		if (type != LadderType.LEAGUE && accountName != null)
			throw new IllegalArgumentException("`accountName` should only be specified if `type` is `" + LadderType.LEAGUE + "`.");

		if (type != LadderType.LABYRINTH && (difficulty != null || start != null))
			throw new IllegalArgumentException("`difficulty` and `start` should only be specified if `type` is `" + LadderType.LABYRINTH + "`.");

		return service.getLeagueLadder(
			id,
			(realm != null) ? realm.getName() : null,
			limit,
			offset,
			(type != null) ? type.getName() : null,
			track,
			accountName,
			(difficulty != null) ? difficulty.getValue() : null,
			(start != null) ? start.getTime() : null
		);
	}

	public Single<List<PvpMatch>> getUpcomingPvpMatches() {
		return getUpcomingPvpMatches(null);
	}

	public Single<List<PvpMatch>> getUpcomingPvpMatches(Realm realm) {
		return service.getPvpMatches(null, (realm != null) ? realm.getName() : null);
	}

	public Single<List<PvpMatch>> getPvpMatches(String season) {
		return getPvpMatches(season, null);
	}

	public Single<List<PvpMatch>> getPvpMatches(String season, Realm realm) {
		Objects.requireNonNull(season, "To get PvP matches, `seasonId` must be specified. If after upcoming matches, use `getUpcomingPvpMatches`.");

		if (realm == Realm.UNKNOWN)
			throw new InvalidEnumException(realm);

		return service.getPvpMatches(season, (realm != null) ? realm.getName() : null);
	}

	/**
	 * @param compact If we want compact results or full.
	 * @return The max league limit for this type.
	 */
	private int getLeagueLimit(boolean compact) {
		return (compact) ? 230 : 50;
	}
}
