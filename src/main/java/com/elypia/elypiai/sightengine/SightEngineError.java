package com.elypia.elypiai.sightengine;

import com.elypia.elypiai.sightengine.data.SightEngineErrorType;
import org.json.JSONObject;

public class SightEngineError {

    private SightEngine engine;

    private SightEngineErrorType type;
    private int code;
    private String message;

    public SightEngineError(SightEngine engine, JSONObject object) {
        this.engine = engine;
        type = SightEngineErrorType.getByName(object.getString("type"));
        code = object.getInt("code");
        message = object.getString("message");
    }

    public SightEngine getSightEngine() {
        return engine;
    }

    public SightEngineErrorType getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
