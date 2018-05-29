package com.elypia.elypiai.edamam;

import java.util.Objects;

public class Edamam {

    private final String ID;
    private final String KEY;

    public Edamam(String id, String key) {
        ID = Objects.requireNonNull(id);
        KEY = Objects.requireNonNull(key);
    }

    public String getAppId() {
        return ID;
    }

    public String getAppKey() {
        return KEY;
    }
}
