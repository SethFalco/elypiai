package com.elypia.elypiai.sightengine;

import com.elypia.elypiai.sightengine.data.SightEngineErrorType;
import com.google.gson.annotations.SerializedName;

public class SightEngineError {

    @SerializedName("type")
    private SightEngineErrorType type;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public SightEngineErrorType getType() {
        return type;
    }

    public void setType(SightEngineErrorType type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
