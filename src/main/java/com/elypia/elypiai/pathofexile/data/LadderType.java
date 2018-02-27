package com.elypia.elypiai.pathofexile.data;

public enum LadderType {

	LEAGUE("league"),
	PVP("pvp"),
	LABYRINTH("labyrinth");

	private String apiName;

	LadderType(String apiName) {
		this.apiName = apiName;
	}

	public String getApiName() {
		return apiName;
	}

	@Override
	public String toString() {
		return apiName;
	}

	public static LadderType getTypeByApiName(String apiName) {

		for (LadderType type : LadderType.values()) {
			if (type.getApiName().equals(apiName))
				return type;
		}

		return null;
	}
}
