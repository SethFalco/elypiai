package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.QuestStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestsStatus {

	private RuneScape runescape;

	private String username;
	private Collection<QuestStats> quests;
	private int completed;
	private int started;
	private int notStarted;

	public QuestsStatus(RuneScape runescape, String username, JSONArray array) {
		this.runescape = runescape;
		this.username = username;

		quests = new ArrayList<>();

		for (int i = 0; i < array.length(); i++) {
			JSONObject quest = array.getJSONObject(i);
			QuestStats stats = new QuestStats(runescape, quest);

			switch (stats.getStatus()) {
				case COMPLETED: 	completed++; 	break;
				case STARTED: 		started++; 		break;
				case NOT_STARTED: 	notStarted++; 	break;
			}

			quests.add(new QuestStats(runescape, quest));
		}
	}

	public RuneScape getRunescape() {
		return runescape;
	}

	public Collection<QuestStats> getQuests() {
		return quests;
	}

	public Collection<QuestStats> getQuests(QuestStatus status) {
		Stream<QuestStats> quests = this.quests.stream();
		quests = quests.filter(o -> o.getStatus() == status);

		return quests.collect(Collectors.toCollection(ArrayList::new));
	}

	public String getUsername() {
		return username;
	}

	public int getCompleted() {
		return completed;
	}

	public int getStarted() {
		return started;
	}

	public int getNotStarted() {
		return notStarted;
	}
}
