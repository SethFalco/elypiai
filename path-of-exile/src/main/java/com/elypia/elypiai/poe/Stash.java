package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.data.StashType;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Stash {

	@SerializedName("id")
	private String id;

	@SerializedName("public")
	private boolean isPublic;

	@SerializedName("accountName")
	private String accountName;

	@SerializedName("lastCharacterName")
	private String lastCharacterName;

	@SerializedName("stash")
	private String name;

	@SerializedName("stashType")
	private StashType stashType;

	@SerializedName("league")
	private String league;

	@SerializedName("items")
	private List<StashItem> items;

	public String getId() {
		return id;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getLastCharacterName() {
		return lastCharacterName;
	}

	public String getName() {
		return name;
	}

	public StashType getStashType() {
		return stashType;
	}

	public String getLeague() {
		return league;
	}

	public List<StashItem> getItems() {
		return items;
	}
}
