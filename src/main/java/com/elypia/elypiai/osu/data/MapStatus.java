package com.elypia.elypiai.osu.data;

public enum MapStatus {

	GRAVEYARD(-2),
	WIP(-1),
	PENDING(0),
	RANKED(1),
	APPROVED(2),
	QUALIFIED(3),
	LOVED(4);

	private int id;

	MapStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static MapStatus getById(int id) {
		for (MapStatus status : values()) {
			if (status.getId() == id)
				return status;
		}

		return null;
	}
}