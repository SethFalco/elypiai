package com.elypia.elypiai.osu.data;

public enum MapGenre {

	ANY(0),
	UNSPECIFIED(1),
	VIDEO_GAME(2),
	ANIME(3),
	ROCK(4),
	POP(5),
	OTHER(6),
	NOVELTY(7),
	// There is no 8 -> Fuck if I'd know why. DX
	HIP_HOP(9),
	ELECTRONIC(10);

	private int id;

	MapGenre(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static MapGenre getById(int id) {
		for (MapGenre genre : values()) {
			if (genre.getId() == id)
				return genre;
		}

		return null;
	}
}
