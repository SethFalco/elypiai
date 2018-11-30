package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.deserializers.JoinedStringDeserializer;
import com.elypia.elypiai.poe.deserializers.StashItemNameDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.List;

public class StashItem {

	@SerializedName("verified")
	private boolean verified;

	@SerializedName("w")
	private int width;

	@SerializedName("h")
	private int height;

	@SerializedName("ilvl")
	private int level;

	@SerializedName("icon")
	private String icon;

	@SerializedName("support")
	private boolean support;

	@SerializedName("league")
	private String league;

	@SerializedName("id")
	private String id;

	@SerializedName("sockets")
	private List<ItemSocket> sockets;

	@SerializedName("name")
	@JsonAdapter(StashItemNameDeserializer.class)
	private String name;

	@SerializedName("typeLine")
	private String typeLine;

	@SerializedName("identified")
	private boolean identified;

	@SerializedName("corrupted")
	private boolean corrupted;

	@SerializedName("note")
	private String note;

	@SerializedName("properties")
	private List<ItemProperty> properties;

	@SerializedName("additionalProperties")
	private List<AdditionalProperty> additionalProperties;

	@SerializedName("requirements")
	private List<ItemProperty> requirements;

	@SerializedName("nextLevelRequirements")
	private List<ItemProperty> nextLevelRequirements;

	@SerializedName("utilityMods")
	private List<String> utilityMods;

	@SerializedName("setDescrText")
	private String setDescriptionText;

	@SerializedName("explicitMods")
	private List<String> explicitMods;

	@SerializedName("descrText")
	private String decriptionText;

	@SerializedName("flavourText")
	@JsonAdapter(JoinedStringDeserializer.class)
	private String flavourText;

	@SerializedName("frameType")
	private int frameType;

	@SerializedName("x")
	private int xPos;

	@SerializedName("y")
	private int yPos;

	@SerializedName("inventoryId")
	private String inventoryId;

	@SerializedName("socketedItems")
	private List<ItemSocket> socketedItems;

	public boolean isVerified() {
		return verified;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLevel() {
		return level;
	}

	public String getIcon() {
		return icon;
	}

	public String getLeague() {
		return league;
	}

	public String getId() {
		return id;
	}

	public Collection<ItemSocket> getSockets() {
		return sockets;
	}

	public String getName() {
		return name;
	}

	public String getTypeLine() {
		return typeLine;
	}

	public boolean isIdentified() {
		return identified;
	}

	public boolean isCorrupted() {
		return corrupted;
	}

	public String getNote() {
		return note;
	}

	public Collection<ItemProperty> getProperties() {
		return properties;
	}

	public Collection<AdditionalProperty> getAdditionalProperties() {
		return additionalProperties;
	}

	public Collection<ItemProperty> getRequirements() {
		return requirements;
	}

	public List<String> getExplicitMods() {
		return explicitMods;
	}

	public String getFlavourText() {
		return flavourText;
	}

	public int getFrameType() {
		return frameType;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public Collection<ItemSocket> getSocketedItems() {
		return socketedItems;
	}
}
