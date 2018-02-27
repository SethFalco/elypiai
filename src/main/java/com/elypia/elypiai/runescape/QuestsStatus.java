package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.QuestStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestsStatus {

	private String username;
	private Collection<QuestStats> quests;
	private int completed;
	private int started;
	private int notStarted;

	public QuestsStatus(RuneScape runescape, String username, JSONArray array) {
		quests = new ArrayList<>();
		this.username = username;

		for (int i = 0; i < array.length(); i++) {
			JSONObject quest = array.getJSONObject(i);
			quests.add(new QuestStats(runescape, quest));
		}
	}

	public Collection<QuestStats> getQuests(QuestStatus status) {
		Stream<QuestStats> completed = quests.stream();

		completed = completed.filter(o -> {
			return o.getStatus() == status;
		});

		return completed.collect(Collectors.toCollection(ArrayList::new));
	}

	public Collection<String> getQuestsNames(QuestStatus status) {
		Collection<QuestStats> stats = getQuests(status);
		Stream<QuestStats> streamQuests = stats.stream();
		Stream<String> streamNames = streamQuests.map(QuestStats::getTitle);

		return streamNames.collect(Collectors.toCollection(ArrayList::new));
	}

	private Stream<QuestStats> getStreamOfStatus(QuestStatus status) {
		Stream<QuestStats> completed = quests.stream();

		return completed.filter(o -> {
			return o.getStatus() == status;
		});
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
