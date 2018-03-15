package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.RSEndpoint;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class RuneScape {

	public static final String PROFILE_PRIVATE = "PROFILE_PRIVATE";

	private RuneScape runescape;

	private Collection<RuneScapeUser> users;
	private Collection<RuneScapeListener> listeners;

	public RuneScape() {
		runescape = this;
		users = new ArrayList<>();
		listeners = new ArrayList<>();
		runescape = this;
	}

	/**
	 * Return the RuneScape player with the username provided.
	 * Possible null, if user doesn't exist. If the user does exist
	 * but has their profile set to private, name will be "PROFILE_PRIVATE"
	 * and the rest of the object will be effectively null.
	 *
	 * @param username The username of the player to get.
	 * @param success What do to with the result.
	 * @param failure What to do in case of failure, eg timeout.
	 */

	public void getUser(String username, Consumer<RuneScapeUser> success, Consumer<IOException> failure) {
		String endpoint = RSEndpoint.RUNEMETRICS_PROFILE.getEndpoint();

		ElyRequest req = new ElyRequest(endpoint);
		req.addParam("user", username);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			RuneScapeUser user = new RuneScapeUser(runescape, object);

			success.accept(user);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * @return If the player's profile was set to private.
	 */

	public boolean isPrivate(RuneScapeUser player) {
		if (player == null)
			return false;

		return (player.getUsername().equals(PROFILE_PRIVATE));
	}

	public void getOnlineUserCount(Consumer<String> success, Consumer<IOException> failure) {
        String endpoint = RSEndpoint.PLAYER_COUNT.getEndpoint();

        ElyRequest req = new ElyRequest(endpoint);

        req.get(result -> {
			String string = result.asString();
			string = string.substring(string.indexOf("(") + 1, string.lastIndexOf("}"));
			success.accept(string);
		}, err -> {
        	failure.accept(err);
		});
	}

	public void getQuestStatuses(String user, Consumer<QuestsStatus> success, Consumer<IOException> failure) {
		String endpoint = RSEndpoint.RUNEMETRICS_QUESTS.getEndpoint();

		ElyRequest req = new ElyRequest(endpoint);
		req.addParam("user", user);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			JSONArray quests = object.getJSONArray("quests");

			QuestsStatus questsStatus = new QuestsStatus(runescape, user, quests);

			success.accept(questsStatus);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * @return	Get the list of cached players.
	 */

	public Collection<RuneScapeUser> getUsers() {
		return users;
	}

    /**
     * Convert XP to the level equivilent. <br>
     * Exactly the same as calling {@link #convertXpToLevel(long, boolean)},
     * with parameter boolean as <strong>false</strong>.
     *
     * @param	xp      The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */

    public static int convertXpToLevel(long xp) {
        return convertXpToLevel(xp, false);
    }

    /**
     * Convert XP to the level equivilent.
     *
     * @param	xp      The xp to convert to level.
     * @param	elite	Convert as an elite skill or standard skill.
     * @return	        The level a player would be with the XP provided.
     */

	public static int convertXpToLevel(long xp, boolean elite) {
		if (xp < 0)
			throw new IllegalArgumentException("XP (long) can not be of a negative value.");

		int level = 1;
		long result;

		while (xp >= (result = convertLevelToXp(level + 1, elite))) {
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
     * Exactly the same as calling {@link #convertLevelToXp(int, boolean)},
     * with parameter boolean as <strong>false</strong>.
     *
     * @param	level   The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */

    public static long convertLevelToXp(int level) {
        return convertLevelToXp(level, false);
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

	public static long convertLevelToXp(int level, boolean elite) {
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
