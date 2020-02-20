/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.poe;

import com.google.gson.annotations.*;
import org.elypia.elypiai.common.gson.deserializers.JoinedStringDeserializer;
import org.elypia.elypiai.poe.deserializers.StashItemNameDeserializer;

import java.util.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
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
