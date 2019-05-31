package com.elypia.elypiai.runescape.data;

import com.google.gson.annotations.SerializedName;

public enum CompletionStatus {

	UNKNOWN,

	@SerializedName("NOT_STARTED")
	NOT_STARTED,

	@SerializedName("STARTED")
	STARTED,

	@SerializedName("COMPLETED")
	COMPLETED
}
