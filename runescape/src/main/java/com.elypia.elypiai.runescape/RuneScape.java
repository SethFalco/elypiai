package com.elypia.elypiai.runescape;

import com.elypia.elypiai.restutils.RestAction;
import com.elypia.elypiai.runescape.deserializers.QuestStatDeserializer;
import com.elypia.elypiai.runescape.impl.IRuneScapeService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

public class RuneScape {

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://apps.runescape.com/runemetrics/");
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	private IRuneScapeService service;
	private Map<String, Player> cache;

	public RuneScape() {
		this(BASE_URL);
	}

	public RuneScape(URL baseUrl) {
		cache = new HashMap<>();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(new TypeToken<List<QuestStats>>(){}.getType(), new QuestStatDeserializer());

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl.toString());
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

		service = retrofitBuilder.build().create(IRuneScapeService.class);
	}

	/**
	 * Return the RuneScape player with the username provided.
	 * Possible null, if user doesn't exist. If the user does exist
	 * but has their profile set to private, name will be "PROFILE_PRIVATE"
	 * and the rest of the object will be effectively null.
	 *
	 * @param username The username of the player to get.
	 */
	public RestAction<Player> getUser(String username) {
		Call<Player> call = service.getUser(username);
		return new RestAction<>(call);
	}

	public RestAction<List<QuestStats>> getQuestStatuses(String user) {
		Call<List<QuestStats>> call = service.getQuestStats(user);
		return new RestAction<>(call);
	}

	/**
	 * @return	Get the list of cached players.
	 */
	public Collection<Player> getUsers() {
		return Collections.unmodifiableCollection(cache.values());
	}

    /**
     * Convert XP to the level equivilent. <br>
     * Exactly the same as calling {@link #parseXpAsLevel(long, boolean)},
     * with parameter boolean as <strong>false</strong>.
     *
     * @param	xp      The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */
    public static int parseXpAsLevel(long xp) {
        return parseXpAsLevel(xp, false);
    }

    /**
     * Convert XP to the level equivilent.
     *
     * @param	xp      The xp to convert to level.
     * @param	elite	Convert as an elite skill or standard skill.
     * @return	        The level a player would be with the XP provided.
     */
	public static int parseXpAsLevel(long xp, boolean elite) {
		if (xp < 0)
			throw new IllegalArgumentException("XP (long) can not be of a negative value.");

		int level = 1;
		long result;

		while (xp >= (result = parseLevelAsXp(level + 1, elite))) {
			if (result == -1)
				break;

			level++;
		}

		return level;
	}

    /**
     * Convert a level, or virtual level to the XP equivilent using
     * RuneScapes XP formula. <br>
     * Note: Returns -1 if the level is too high. <br>
     * Exactly the same as calling {@link #parseLevelAsXp(int, boolean)},
     * with parameter boolean as <strong>false</strong>.
     *
     * @param	level   The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */
    public static long parseLevelAsXp(int level) {
        return parseLevelAsXp(level, false);
    }

	/**
	 * Convert a level, or virtual level to the XP equivilent using
	 * RuneScapes XP formula. <br>
	 * Note: Returns -1 if the level is too high.
	 *
	 * @param   level	The level to convert to XP.
	 * @param	elite	Convert as an elite skill or standard skill.
	 * @return			The XP required to attain this level.
	 */
	public static long parseLevelAsXp(int level, boolean elite) {
		if (level < 1)
			throw new IllegalArgumentException("Level (int) can not be zero or a negative value.");

		double xp = 0;

		for (int count = 1; count < level; count++) {
			xp += (long)(count + 300 * Math.pow(2, (double)count / 7));

			if (xp >= (double)Long.MAX_VALUE * 4)
				return -1;
		}

		return (long)(xp / 4);
	}
}
