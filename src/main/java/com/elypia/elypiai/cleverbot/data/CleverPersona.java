package com.elypia.elypiai.cleverbot.data;

public enum CleverPersona {

    WACKY("Tweak1"),
    TALKITIVE("Tweak2"),
    ATTENTIVE("Tweak3");

    private String apiName;

    CleverPersona(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }
}
