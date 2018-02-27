package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.AscendancyClass;
import org.json.JSONObject;

public class Exile extends PoEObject {

	private String name;
	private int level;
	private AscendancyClass ascendancy;
	private String id;
	private long experience;

	public Exile(PathOfExile poe, JSONObject object) {
		super(poe);

		name = object.getString("name");
		level = object.getInt("level");
		ascendancy = AscendancyClass.getTypeByApiName(object.getString("class"));
		id = object.getString("id");
		experience = object.getLong("experience");
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public AscendancyClass getAscendancy() {
		return ascendancy;
	}

	public String getId() {
		return id;
	}

	public long getExperience() {
		return experience;
	}
}
