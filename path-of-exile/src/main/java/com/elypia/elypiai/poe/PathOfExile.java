package com.elypia.elypiai.poe;

import com.elypia.elypiai.common.core.*;
import com.elypia.elypiai.common.core.ext.WrapperExtension;
import com.elypia.elypiai.common.core.utils.Checks;
import com.elypia.elypiai.common.gson.deserializers.DateDeserializer;
import com.elypia.elypiai.poe.data.*;
import com.elypia.elypiai.poe.deserializers.LadderEntryDeserializer;
import com.elypia.elypiai.poe.impl.PathOfExileService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

public class PathOfExile extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(PathOfExile.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("http://api.pathofexile.com/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private PathOfExileService service;

	public PathOfExile(WrapperExtension... exts) {
		this(BASE_URL, exts);
	}

	public PathOfExile(URL baseUrl, WrapperExtension... exts) {
		super(exts);
		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateDeserializer("yyyy-MM-dd'T'HH:mm:ss'Z'"));

		gsonBuilder.registerTypeAdapter(new TypeToken<List<LadderEntry>>(){}.getType(), new LadderEntryDeserializer(gsonBuilder.create()));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(RequestService.withExtensionInterceptor(this))
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.build()
			.create(PathOfExileService.class);
	}

	public RestAction<StashTabs> getStashTabs() {
		return getStashTabs(null);
	}

	public RestAction<StashTabs> getStashTabs(String cursor) {
		Call<StashTabs> call = service.getStashTabs(cursor);
		return new RestAction<>(call);
	}

	public RestAction<List<League>> getSeasonsLeagues(String season) {
		return getSeasonsLeagues(null, season);
	}

	public RestAction<List<League>> getSeasonsLeagues(Realm realm, String season) {
		return getSeasonsLeagues(realm, season, false);
	}

	public RestAction<List<League>> getSeasonsLeagues(Realm realm, String season, boolean compact) {
		return getSeasonsLeagues(realm, season, compact, getLeagueLimit(compact));
	}

	public RestAction<List<League>> getSeasonsLeagues(Realm realm, String season, boolean compact, int limit) {
		return getSeasonsLeagues(realm, season, compact, limit, 0);
	}

	public RestAction<List<League>> getSeasonsLeagues(Realm realm, String season, boolean compact, int limit, int offset) {
		return getLeagues(LeagueType.SEASON, realm, season, compact, limit, offset);
	}

	public RestAction<List<League>> getLeagues() {
		return getLeagues(null);
	}

	public RestAction<List<League>> getLeagues(LeagueType type) {
		return getLeagues(type, null);
	}

	public RestAction<List<League>> getLeagues(LeagueType type, Realm realm) {
		return getLeagues(type, realm, null);
	}

	public RestAction<List<League>> getLeagues(LeagueType type, Realm realm, String season) {
		return getLeagues(type, realm, season, false);
	}

	public RestAction<List<League>> getLeagues(LeagueType type, Realm realm, String season, boolean compact) {
		return getLeagues(type, realm, season, compact, getLeagueLimit(compact));
	}

	public RestAction<List<League>> getLeagues(LeagueType type, Realm realm, String season, boolean compact, int limit) {
		return getLeagues(type, realm, season, compact, limit, 0);
	}

	public RestAction<List<League>> getLeagues(LeagueType type, Realm realm, String season, boolean compact, int limit, int offset) {
		Checks.requireNonUnknown(type, "type");
		Checks.requireNonUnknown(realm, "realm");

		if (type == LeagueType.SEASON)
			Objects.requireNonNull(season, "Paramater `season` can not be null when `type` is `LeagueType.SEASON`.");

		Call<List<League>> call = service.getLeagues(
			(type != null) ? type.getName() : null,
			(realm != null) ? realm.getName() : null,
			season,
			compact ? 1 : 0,
			limit,
			offset
		);

		return new RestAction<>(call);
	}

	public RestAction<LeagueRule> getRule(String id) {
		Call<LeagueRule> call = service.getLeagueRule(id);
		return new RestAction<>(call);
	}

	public RestAction<List<LeagueRule>> getRules() {
		Call<List<LeagueRule>> call = service.getLeagueRules();
		return new RestAction<>(call);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id) {
		return getLeagueLadder(id, null);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm) {
		return getLeagueLadder(id, realm, 200);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit) {
		return getLeagueLadder(id, realm, limit, 0);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset) {
		return getLeagueLadder(id, realm, limit, offset, null);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type) {
		return getLeagueLadder(id, realm, limit, offset, type, true);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track) {
		return getLeagueLadder(id, realm, limit, offset, type, track, null);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track, String accountName) {
		return getLeagueLadder(id, realm, limit, offset, type, track, accountName, null);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track, String accountName, LabyrinthDifficulty difficulty) {
		return getLeagueLadder(id, realm, limit, offset, type, track, accountName, difficulty, null);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, Realm realm, int limit, int offset, LadderType type, boolean track, String accountName, LabyrinthDifficulty difficulty, Date start) {
		Checks.requireNonUnknown(realm, "realm");
		Checks.requireNonUnknown(type, "type");
		Checks.requireNonUnknown(difficulty, "difficulty");

		if (type != LadderType.LEAGUE && accountName != null)
			throw new IllegalArgumentException("`accountName` should only be specified if `type` is `" + LadderType.LEAGUE + "`.");

		if (type != LadderType.LABYRINTH && (difficulty != null || start != null))
			throw new IllegalArgumentException("`difficulty` and `start` should only be specified if `type` is `" + LadderType.LABYRINTH + "`.");

		Call<List<LadderEntry>> call = service.getLeagueLadder(
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

		return new RestAction<>(call);
	}

	public RestAction<List<PvpMatch>> getUpcomingPvpMatches() {
		return getUpcomingPvpMatches(null);
	}

	public RestAction<List<PvpMatch>> getUpcomingPvpMatches(Realm realm) {
		Call<List<PvpMatch>> call = service.getPvpMatches(
			null,
			(realm != null) ? realm.getName() : null
		);

		return new RestAction<>(call);
	}

	public RestAction<List<PvpMatch>> getPvpMatches(String season) {
		return getPvpMatches(season, null);
	}

	public RestAction<List<PvpMatch>> getPvpMatches(String season, Realm realm) {
		Objects.requireNonNull(season, "To get PvP matches, `seasonId` must be specified. If after upcoming matches, use `getUpcomingPvpMatches`.");
		Checks.requireNonUnknown(realm);

		Call<List<PvpMatch>> call = service.getPvpMatches(
			season,
			(realm != null) ? realm.getName() : null
		);

		return new RestAction<>(call);
	}

	/**
	 * @param compact If we want compact results or full.
	 * @return The max league limit for this type.
	 */
	private int getLeagueLimit(boolean compact) {
		return (compact) ? 230 : 50;
	}
}
