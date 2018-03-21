package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.AscendancyClass;
import com.elypia.elypiai.pathofexile.data.AscendancyType;
import org.json.JSONObject;

public class Exile extends PoEObject {

	private String name;
	private int level;
	private AscendancyType ascendancy;
	private AscendancyClass ascendancyClass;
	private String id;
	private long experience;

	public Exile(PathOfExile poe, JSONObject object) {
		super(poe);

		name = object.getString("name");
		level = object.getInt("level");
		id = object.optString("id");
		experience = object.getLong("experience");

		ascendancyClass = AscendancyClass.getByName(object.getString("class"));

		if (ascendancyClass != null)
			ascendancy = ascendancyClass.getType();
		else
			ascendancy = AscendancyType.getByName(object.getString("class"));
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public AscendancyType getAscendancy() {
		return ascendancy;
	}

	public AscendancyClass getAscendancyClass() {
		return ascendancyClass;
	}

	public String getId() {
		return id;
	}

	public long getExperience() {
		return experience;
	}
}
