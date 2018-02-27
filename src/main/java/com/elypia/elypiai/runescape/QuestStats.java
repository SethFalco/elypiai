package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.QuestStatus;
import org.json.JSONObject;

public class QuestStats {

	private RuneScape runescape;

	private String title;
	private QuestStatus status;
	private int difficulty;
	private boolean members;
	private int questPoints;
	private boolean userEligible;

	public QuestStats(RuneScape runescape, JSONObject object) {
		this.runescape = runescape;

		title = object.getString("title");
		status = QuestStatus.valueOf(object.getString("status"));
		difficulty = object.getInt("difficulty");
		members = object.getBoolean("members");
		questPoints = object.getInt("questPoints");
		userEligible = object.getBoolean("userEligible");
	}

	public RuneScape getRuneScape() {
		return runescape;
	}

	public String getTitle() {
		return title;
	}

	public QuestStatus getStatus() {
		return status;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public boolean isMembers() {
		return members;
	}

	public int getQuestPoints() {
		return questPoints;
	}

	public boolean isUserEligible() {
		return userEligible;
	}

	@Override
	public String toString() {
		return title;
	}
}
