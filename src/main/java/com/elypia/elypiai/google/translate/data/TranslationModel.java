package com.elypia.elypiai.google.translate.data;

public enum TranslationModel {

    NMT("nmt"),
    PBMT("base");

    private String model;

    TranslationModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
