package com.elypia.elypiai.google.translate;

import com.elypia.elypiai.google.translate.data.GoogleTranslateEndpoint;
import com.elypia.elypiai.utils.Language;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class TranslationRequester {

    private GoogleTranslate googleTranslate;
    private String apiKey;

    public TranslationRequester(GoogleTranslate googleTranslate, String apiKey) {
        this.googleTranslate = googleTranslate;
        this.apiKey = apiKey;
    }

    public void getSupportedLanguages(Consumer<List<Language>> success, Consumer<IOException> failure) {
        String endpoint = GoogleTranslateEndpoint.LANGUAGES.getEndpoint();

        ElyRequest req = new ElyRequest(endpoint);
        req.addParam("key", apiKey);

        req.get(result -> {
            List<Language> results = new ArrayList<>();

            JSONObject object = result.asJSONObject();
            JSONArray languages = object.getJSONObject("data").getJSONArray("languages");

            for (int i = 0; i < languages.length(); i++) {
                String language = languages.getJSONObject(i).getString("language");
                Language l = Language.getByCode(language);

                if (l != null)
                    results.add(l);
            }

            success.accept(results);
        }, err -> {
            failure.accept(err);
        });
    }

    public void translate(String body, Language source, Language target, Consumer<Translation> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);
        Objects.requireNonNull(target);

        if (source != null) {
            if (!googleTranslate.isSupportedLanguage(source))
                throw new IllegalArgumentException(String.format("Source %s is not a supported language.", source));
        }

        if (!googleTranslate.isSupportedLanguage(target))
            throw new IllegalArgumentException(String.format("Target %s is not a supported language.", target));

        String endpoint = GoogleTranslateEndpoint.TRANSLATE.getEndpoint();

        JSONObject formdata = new JSONObject();
        formdata.put("q", body);
        formdata.put("target", target.getCode());

        if (source != null)
            formdata.put("source", source);

        ElyRequest req = new ElyRequest(endpoint);
        req.addParam("key", apiKey);
        req.setFormData(formdata);

        req.post(result -> {
            JSONObject object = result.asJSONObject();
            JSONArray translations = object.getJSONObject("data").getJSONArray("translations");
            JSONObject translation = translations.getJSONObject(0);
            Translation trans = new Translation(googleTranslate, body, target, translation);

            success.accept(trans);
        }, err -> {
            failure.accept(err);
        });
    }
}
