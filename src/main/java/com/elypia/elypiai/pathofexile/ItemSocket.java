package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.GemAttribute;
import com.elypia.elypiai.pathofexile.data.GemColor;
import org.json.JSONObject;

public class ItemSocket extends PoEObject {

	private int group;
	private GemAttribute attribute;
	private GemColor color;

	public ItemSocket(PathOfExile poe, JSONObject object) {
		super(poe);

		group = object.getInt("group");
		attribute = GemAttribute.getByName(object.getString("attr"));
		color = GemColor.getByName(object.getString("sColour"));
	}

	public int getGroup() {
		return group;
	}

	public GemAttribute getAttribute() {
		return attribute;
	}

	public GemColor getColor() {
		return color;
	}
}
