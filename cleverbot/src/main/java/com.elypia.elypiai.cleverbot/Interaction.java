package com.elypia.elypiai.cleverbot;

import com.google.gson.annotations.SerializedName;

public class Interaction {

    @SerializedName("say")
    private String say;

    @SerializedName("response")
    private String response;

    public String getSay() {
        return say;
    }

    public String getResponse() {
        return response;
    }
}
