package com.elypia.elypiai.runescape.data;

public enum RuneScapeEquipment {

	HEAD(0),
	CAPE(1),
	NECK(2),
	MAIN_HAND_UNSHEATHED(3),
	BODY(4),
	OFF_HAND_UNSHEATHED(5),
	TWO_HANDED_UNSHEATHED(6),
	LEG_WEAR(7),
	HANDS(9),
	FEET(10),
	RING(12),
	AMMUNITION(13),
	AURA(14),
	MAIN_HANDED_SHEATHED(15),
	OFF_HAND_SHEATHED(16),
	POCKET(17),
	WINGS(18);

	private int id;

	RuneScapeEquipment(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
