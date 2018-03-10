package com.elypia.elypiai.google.translate;

import com.elypia.elypiai.google.translate.data.GoogleTranslateEndpoint;
import com.elypia.elypiai.utils.Language;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public void getSupportedLanguages(Consumer<List<Language>> success, Consumer<UnirestException> failure) {
        String endpoint = GoogleTranslateEndpoint.LANGUAGES.getEndpoint();

        Unirest.get(endpoint).queryString("key", apiKey).asJsonAsync(new Callback<JsonNode>() {

            @Override
            public void completed(HttpResponse<JsonNode> response) {
                List<Language> results = new ArrayList<>();

                JSONObject object = response.getBody().getObject();
                JSONArray languages = object.getJSONObject("data").getJSONArray("languages");

                for (int i = 0; i < languages.length(); i++) {
                    String language = languages.getJSONObject(i).getString("language");
                    Language l = Language.getByCode(language);

                    if (l != null)
                        results.add(l);
                }

                success.accept(results);
            }

            @Override
            public void failed(UnirestException e) {
                failure.accept(e);
            }

            @Override
            public void cancelled() {

            }
        });
    }

    public void translate(String body, Language source, Language target, Consumer<Translation> success, Consumer<UnirestException> failure) {
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

        Unirest.post(endpoint).queryString("key", apiKey).body(formdata).asJsonAsync(new Callback<JsonNode>() {

            @Override
            public void completed(HttpResponse<JsonNode> response) {
                JSONObject object = response.getBody().getObject();
                JSONArray translations = object.getJSONObject("data").getJSONArray("translations");
                JSONObject translation = translations.getJSONObject(0);
                Translation trans = new Translation(googleTranslate, body, target, translation);

                success.accept(trans);
            }

            @Override
            public void failed(UnirestException e) {
                failure.accept(e);
            }

            @Override
            public void cancelled() {

            }
        });
    }
}
