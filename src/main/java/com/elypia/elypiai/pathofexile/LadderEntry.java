package com.elypia.elypiai.pathofexile;

import org.json.JSONObject;

public class LadderEntry extends PoEObject {

	private int rank;
	private boolean dead;
	private boolean online;
	private Exile exile;
	private Account account;

	public LadderEntry(PathOfExile poe, JSONObject object) {
		super(poe);

		rank = object.getInt("rank");
		dead = object.getBoolean("dead");
		online = object.getBoolean("online");
		exile = new Exile(poe, object.getJSONObject("character"));
		account = new Account(poe, object.getJSONObject("account"));
	}

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
