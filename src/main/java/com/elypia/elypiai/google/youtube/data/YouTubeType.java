package com.elypia.elypiai.google.youtube.data;

public enum YouTubeType {

	VIDEO("video"),
	PLAYLIST("playlist"),
	CHANNEL("channel");

	private String apiName;

	YouTubeType(String apiName) {
		this.apiName = apiName;
	}

	public String getApiName() {
		return apiName;
	}
}
