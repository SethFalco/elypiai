package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.StashType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Stash extends PoEObject {

	private String id;
	private boolean isPublic;
	private String accountName;
	private String lastCharacterName;
	private String name;
	private StashType stashType;
	private Collection<StashItem> items;

	public Stash(PathOfExile poe, JSONObject object) {
		super(poe);
		items = new ArrayList<>();

		id = object.getString("id");
		isPublic = object.getBoolean("public");
		accountName = object.optString("accountName", null);
		lastCharacterName = object.optString("lastCharacterName", null);
		name = object.optString("stash", null);
		stashType = StashType.getTypeByApiName(object.getString("stashType"));

		JSONArray itemsArray = object.getJSONArray("items");

		for (int i = 0; i < itemsArray.length(); i++) {
			StashItem item = new StashItem(poe, itemsArray.getJSONObject(i));
			items.add(item);
		}
	}

	public String getId() {
		return id;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getLastCharacterName() {
		return lastCharacterName;
	}

	public String getName() {
		return name;
	}

	public StashType getStashType() {
		return stashType;
	}

	public Collection<StashItem> getItems() {
		return items;
	}
}
