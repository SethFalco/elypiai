package com.elypia.elypiai.dailymotion.deserializer;

import com.elypia.elypiai.dailymotion.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class DailymotionVideoDeserializer implements JsonDeserializer<DailymotionVideo> {

    private static final Gson GSON = new Gson();

    private final Dailymotion DAILYMOTION;

    public DailymotionVideoDeserializer(Dailymotion dailymotion) {
        DAILYMOTION = dailymotion;
    }

    @Override
    public DailymotionVideo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DailymotionVideo video = GSON.fromJson(json, DailymotionVideo.class);
        video.setDailymotion(DAILYMOTION);

        return video;
    }
}


