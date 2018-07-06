package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.*;
import com.google.gson.annotations.SerializedName;

public class QuestStats implements Comparable<QuestStats> {

	@SerializedName("title")
	private String title;

	@SerializedName("status")
	private CompletionStatus status;

	@SerializedName("difficulty")
	private QuestDifficulty difficulty;

	@SerializedName("members")
	private boolean members;

	@SerializedName("questPoints")
	private int questPoints;

	@SerializedName("userEligible")
	private boolean userEligible;

	public String getTitle() {
		return title;
	}

	public CompletionStatus getStatus() {
		return status;
	}

	public QuestDifficulty getDifficulty() {
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
	public int compareTo(QuestStats o) {
		return title.compareToIgnoreCase(o.title);
	}
}
