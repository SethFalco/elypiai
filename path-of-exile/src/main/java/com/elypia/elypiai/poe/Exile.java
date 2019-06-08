package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.data.AscendancyClass;
import com.elypia.elypiai.poe.data.AscendancyType;
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

	/**
	 * @return The class of the {@link Exile}, null if
	 * there is no specific {@link AscendancyClass} but just
	 * {@link AscendancyType}, or {@link AscendancyClass#UNKNOWN}
	 * if the class/type is not known to this API wrapper.
	 */
	public AscendancyClass getAscendancyClass() {
		AscendancyClass aclass = AscendancyClass.get(ascendancy);

		if (aclass != AscendancyClass.UNKNOWN)
			return aclass;

		AscendancyType type = getAscendancy();

		if (type != AscendancyType.UNKNOWN)
			return null;

		return AscendancyClass.UNKNOWN;
	}

	public AscendancyType getAscendancy() {
		AscendancyType type = AscendancyType.get(ascendancy);
		return (type != AscendancyType.UNKNOWN) ? type : getAscendancyClass().getType();
	}

	public String getId() {
		return id;
	}

	public long getExperience() {
		return experience;
	}
}
