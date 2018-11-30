package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.data.LadderType;
import com.elypia.elypiai.poe.data.LeagueType;
import com.elypia.elypiai.poe.deserializers.LadderEntryDeserializer;
import com.elypia.elypiai.poe.impl.PathOfExileService;
import com.elypia.elypiai.restutils.RestAction;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class PathOfExile {

	private static final String BASE_URL = "http://api.pathofexile.com/";

	private PathOfExileService service;

	public PathOfExile() {
		this(BASE_URL);
	}

	public PathOfExile(String baseUrl) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ssZ");
		gsonBuilder.registerTypeAdapter(new TypeToken<List<LadderEntry>>(){}.getType(), new LadderEntryDeserializer());

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

		service = retrofitBuilder.build().create(PathOfExileService.class);
	}

	public RestAction<StashTabs> getStashTabs() {
		return getStashTabs(null);
	}

	public RestAction<StashTabs> getStashTabs(String cursor) {
		Call<StashTabs> call = service.getStashTabs(cursor);
		return new RestAction<>(call);
	}

	public RestAction<List<League>> getLeagues(LeagueType type, boolean compact, int limit) {
		return getLeagues("", type, compact, limit);
	}

	public RestAction<List<League>> getLeagues(String id, LeagueType type, boolean compact, int limit) {
		Call<List<League>> call = service.getLeagues(id, type.name(), compact ? 1 : 0, limit, 0);
		return new RestAction<>(call);
	}

	public RestAction<LeagueRule> getRule(int id) {
		Call<LeagueRule> call = service.getLeagueRule(id);
		return new RestAction<>(call);
	}

	public RestAction<List<LeagueRule>> getRules() {
		Call<List<LeagueRule>> call = service.getLeagueRules();
		return new RestAction<>(call);
	}

	public RestAction<List<LadderEntry>> getLeagueLadder(String id, int limit, int offset, LadderType type) {
		Call<List<LadderEntry>> call = service.getLeagueLadder(id, limit, offset);
		return new RestAction<>(call);
	}

	public RestAction<List<PvPMatch>> getPvPMatches(String id) {
		Call<List<PvPMatch>> call = service.getPvPMatches(id);
		return new RestAction<>(call);
	}
}
