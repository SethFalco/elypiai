package com.elypia.elypiai.poe.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public class NestedDeserializer implements JsonDeserializer<Object> {

    private static final String OBJECT_ERROR = "Don't use nested deserializer when there is more than one nested object.";
    private static final String ARRAY_ERROR = "Don't use nested deserializer when there can be more than one element in the array.";

    private static Gson gson = new Gson();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();
            Set<String> keys = object.keySet();

            if (keys.size() > 1)
                throw new IllegalArgumentException(OBJECT_ERROR);

            String key = keys.iterator().next();
            return gson.fromJson(object.get(key), typeOfT);
        }

        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();

            if (array.size() > 1)
                throw new IllegalArgumentException(ARRAY_ERROR);

            return gson.fromJson(array.get(0), typeOfT);
        }

        return null;
    }
}
