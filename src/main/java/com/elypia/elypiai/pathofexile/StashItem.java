package com.elypia.elypiai.pathofexile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class StashItem extends PoEObject {

	private boolean verified;
	private int width;
	private int height;
	private int level;
	private String icon;
	private String league;
	private String id;
	private Collection<ItemSocket> sockets;
	private String name;
	private String typeLine;
	private boolean identified;
	private boolean corrupted;
	private String note;
	private Collection<ItemProperty> properties;
	private Collection<AdditionalProperty> additionalProperties;
	private Collection<ItemProperty> requirements;
	private String setDescription;
	private int talismanTier;
	private String[] implicitMods;
	private String[] explicitMods;
	private String description;
	private String flavourText;
	private int frameType;
	// Category isn't done yet
	private int xPos;
	private int yPos;
	private String inventoryId;
	private Collection<ItemSocket> socketedItems;

	public StashItem(PathOfExile poe, JSONObject object) {
		super(poe);
		properties = new ArrayList<>();
		requirements = new ArrayList<>();

		verified = object.getBoolean("verified");
		width = object.getInt("w");
		height = object.getInt("h");
		level = object.getInt("ilvl");
		icon = object.getString("icon");
		league = object.getString("league");
		id = object.getString("id");
		name = object.getString("name").replaceAll("^(?:<<set:[MS]+>>)+", "");
		typeLine = object.getString("typeLine");
		identified = object.getBoolean("identified");
		setDescription = object.optString("secDescrText");
		description = object.optString("descrText");
		note = object.optString("note", null);
		frameType = object.getInt("frameType");
		xPos = object.getInt("x");
		yPos = object.getInt("y");
		inventoryId = object.getString("inventoryId");

		JSONArray socketsArray = object.optJSONArray("sockets");

		if (socketsArray != null) {
			sockets = new ArrayList<>();

			for (int i = 0; i < socketsArray.length(); i++) {
				ItemSocket socket = new ItemSocket(poe, socketsArray.getJSONObject(i));
				sockets.add(socket);
			}
		}

		if (object.has("properties")) {
			JSONArray propertiesArray = object.getJSONArray("properties");

			for (int i = 0; i < propertiesArray.length(); i++) {
				ItemProperty property = new ItemProperty(poe, propertiesArray.getJSONObject(i));
				properties.add(property);
			}
		}


		JSONArray additionalPropertiesArray = object.optJSONArray("additionalProperties");

		if (additionalPropertiesArray != null) {
			additionalProperties = new ArrayList<>();

			for (int i = 0; i < additionalPropertiesArray.length(); i++) {
				AdditionalProperty property = new AdditionalProperty(poe, additionalPropertiesArray.getJSONObject(i));
				additionalProperties.add(property);
			}
		}

		JSONArray requirementsArray = object.optJSONArray("requirements");

		if (requirementsArray != null) {
			for (int i = 0; i < requirementsArray.length(); i++) {
				ItemProperty property = new ItemProperty(poe, requirementsArray.getJSONObject(i));
				requirements.add(property);
			}
		}

		JSONArray socketedItemsArray = object.optJSONArray("socketedItems");

		if (socketedItemsArray != null) {
			socketedItems = new ArrayList<>();

			for (int i = 0; i < socketedItemsArray.length(); i++) {
				ItemSocket socket = new ItemSocket(poe, socketedItemsArray.getJSONObject(i));
				socketedItems.add(socket);
			}
		}

		JSONArray flavourTextArray = object.optJSONArray("flavourText");

		if (flavourTextArray != null) {
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < flavourTextArray.length(); i++)
				builder.append(flavourTextArray.getString(i));

			flavourText = builder.toString();
		}
	}

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

	public String getSetDescription() {
		return setDescription;
	}

	public int getTalismanTier() {
		return talismanTier;
	}

	public String[] getImplicitMods() {
		return implicitMods;
	}

	public String[] getExplicitMods() {
		return explicitMods;
	}

	public String getDescription() {
		return description;
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
