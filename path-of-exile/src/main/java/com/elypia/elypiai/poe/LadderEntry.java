package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

public class LadderEntry {

	@SerializedName("rank")
	private int rank;

	@SerializedName("dead")
	private boolean dead;

	@SerializedName("online")
	private boolean online;

	@SerializedName("character")
	private Exile exile;

	@SerializedName("account")
	private Account account;

	public int getRank() {
		return rank;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isOnline() {
		return online;
	}

	public Exile getExile() {
		return exile;
	}

	public Account getAccount() {
		return account;
	}
}
