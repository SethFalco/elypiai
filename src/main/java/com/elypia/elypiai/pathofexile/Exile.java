package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.*;
import com.google.gson.annotations.SerializedName;

public class Exile {

	@SerializedName("name")
	private String name;

	@SerializedName("level")
	private int level;

	@SerializedName("class")
	private String ascendancy;

	@SerializedName("id")
	private String id;

	@SerializedName("experience")
	private long experience;

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public AscendancyClass getAscendancyClass() {
		return AscendancyClass.get(ascendancy);
	}

	public AscendancyType getAscendancy() {
		AscendancyType type = AscendancyType.get(ascendancy);
		return type != null ? type : getAscendancyClass().getType();
	}

	public String getId() {
		return id;
	}

	public long getExperience() {
		return experience;
	}
}
