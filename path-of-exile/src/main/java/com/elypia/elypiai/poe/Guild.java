package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.deserializers.EmptyNullDeserializer;
import com.google.gson.annotations.*;

import java.util.Date;

public class Guild {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("tag")
    private String tag;

    @SerializedName("statusMessage")
    @JsonAdapter(EmptyNullDeserializer.class)
    private String status;

    @SerializedName("createdAt")
    private Date creationDate;

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

    public Date getCreationDate() {
        return creationDate;
    }
}
