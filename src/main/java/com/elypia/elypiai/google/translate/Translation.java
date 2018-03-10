package com.elypia.elypiai.google.translate;

import com.elypia.elypiai.google.translate.data.TranslationModel;
import com.elypia.elypiai.utils.Language;
import org.json.JSONObject;

public class Translation {

    private GoogleTranslate googleTranslate;

    private Language source;
    private Language target;
    private String body;
    private String translatedBody;
    private TranslationModel model;

    public Translation(GoogleTranslate googleTranslate, String body, Language target, JSONObject resp) {
        this.googleTranslate = googleTranslate;
        this.body = body;
        this.target = target;

        source = Language.getByCode(resp.getString("detectedSourceLanguage"));
        translatedBody = resp.getString("translatedText");
    }

    public Language getSource() {
        return source;
    }

    public Language getTarget() {
        return target;
    }

    public String getBody() {
        return body;
    }

    public String getTranslatedBody() {
        return translatedBody;
    }

    public TranslationModel getModel() {
        return model;
    }
}
