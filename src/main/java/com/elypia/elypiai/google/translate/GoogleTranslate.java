package com.elypia.elypiai.google.translate;

import com.elypia.elypiai.utils.Language;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class GoogleTranslate {

    private String apiKey;
    private TranslationRequester requester;
    private List<Language> supportedLanguages;

    public GoogleTranslate(String apiKey) {
        this.apiKey = Objects.requireNonNull(apiKey);
        requester = new TranslationRequester(this, apiKey);

        requester.getSupportedLanguages(o -> {
            supportedLanguages = o;
        }, failure -> {
            failure.printStackTrace();
        });
    }

    public void getSupportedLanguages(Consumer<List<Language>> success, Consumer<UnirestException> failure) {
        requester.getSupportedLanguages(success, failure);
    }

    public void translate(String body, Language target, Consumer<Translation> success, Consumer<UnirestException> failure) {
        translate(body, null, target, success, failure);
    }

    public void translate(String body, Language source, Language target, Consumer<Translation> success, Consumer<UnirestException> failure) {
        requester.translate(body, source, target, success, failure);
    }

    public boolean isSupportedLanguage(Language language) {
        return supportedLanguages.contains(language);
    }

    public List<Language> getSupportedLanguages() {
        return supportedLanguages;
    }
}
