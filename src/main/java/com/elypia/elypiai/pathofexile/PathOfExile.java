package com.elypia.elypiai.pathofexile;

import com.google.gson.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Collection;

public class PathOfExile implements PathOfExileService {

	private static final String BASE_URL = "http://api.pathofexile.com/";

	private PathOfExileService service;

	public PathOfExile() {
		this(BASE_URL);
	}

	public PathOfExile(String baseUrl) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
		service = retrofit.create(PathOfExileService.class);
	}

	@Override
	public Call<StashTabs> getStashTabs() {
		return service.getStashTabs();
	}

	@Override
	public Call<Collection<LeagueRule>> getLeagueRules() {
		return service.getLeagueRules();
	}

	@Override
	public Call<LeagueRule> getLeagueRule(int id) {
		return service.getLeagueRule(id);
	}
}
