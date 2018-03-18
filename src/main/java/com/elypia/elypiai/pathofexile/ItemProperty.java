package com.elypia.elypiai.pathofexile;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemProperty extends PoEObject {

	private String name;
	private String value;
	private int displayMode;
	private int type;

	public ItemProperty(PathOfExile poe, JSONObject object) {
		super(poe);

		name = object.getString("name");
		displayMode = object.getInt("displayMode");
		type = object.optInt("type", Integer.MIN_VALUE);

		JSONArray valuesArray = object.getJSONArray("values");

		if (valuesArray.length() > 0) {
			JSONArray valueArray = valuesArray.getJSONArray(0);
			value = valueArray.getString(0);
		}
	}

	public String getName() {
		return name;
	}

	public String getValues() {
		return value;
	}

	public int getDisplayMode() {
		return displayMode;
	}

	public int getType() {
		return type;
	}
}
