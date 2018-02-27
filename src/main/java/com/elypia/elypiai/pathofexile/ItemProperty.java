package com.elypia.elypiai.pathofexile;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemProperty extends PoEObject {

	private String name;
	private String[] values;
	private int displayMode;
	private int type;

	public ItemProperty(PathOfExile poe, JSONObject object) {
		super(poe);

		name = object.getString("name");

		JSONArray valuesArray = object.getJSONArray("values");
		int length = valuesArray.length();

		values = new String[length];

		for (int i = 0; i < length; i++)
			values[i] = valuesArray.getString(i);

		displayMode = object.getInt("displayMode");
		type = object.optInt("type", Integer.MIN_VALUE);
	}

	public String getName() {
		return name;
	}

	public String[] getValues() {
		return values;
	}

	public int getDisplayMode() {
		return displayMode;
	}

	public int getType() {
		return type;
	}
}
