package com.elypia.elypiai.google.youtube;

public enum YouTubeType {

	VIDEO("video"),
	PLAYLIST("playlist"),
	CHANNEL("channel");

	private String type;

	YouTubeType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
