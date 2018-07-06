package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum AccountType {

    @SerializedName("staff")
    STAFF,

    @SerializedName("admin")
    ADMIN,

    @SerializedName("global_mod")
    GLOBAL_MOD,

    @SerializedName("")
    USER
}
