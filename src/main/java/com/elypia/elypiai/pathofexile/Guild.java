package com.elypia.elypiai.pathofexile;

import org.json.JSONObject;

import java.time.Instant;

public class Guild extends PoEObject {

    private int id;
    private String name;
    private String tag;
    private String status;
    private Instant creationDate;

    public Guild(PathOfExile poe, JSONObject object) {
        super(poe);

        id = object.optInt("id");
        name = object.getString("name");
        status = object.getString("statusMessage");
        creationDate = Instant.parse(object.getString("createdAt"));
        tag = object.getString("tag");

        if (tag.isEmpty())
            tag = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }
}
