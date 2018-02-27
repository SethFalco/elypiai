package com.elypia.elypiai.pathofexile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class StashTabs extends PoEObject {

	private String nextChangeId;
	private Collection<Stash> stashes;

	public StashTabs(PathOfExile poe, JSONObject object) {
		super(poe);
		stashes = new ArrayList<>();

		this.nextChangeId = object.getString("next_change_id");

		JSONArray stashesArray = object.getJSONArray("stashes");

		for (int i = 0; i < stashesArray.length(); i++) {
			Stash stash = new Stash(poe, stashesArray.getJSONObject(i));
			stashes.add(stash);
		}
	}

	public String getNextChangeId() {
		return nextChangeId;
	}

	public Collection<Stash> getStashes() {
		return stashes;
	}
}