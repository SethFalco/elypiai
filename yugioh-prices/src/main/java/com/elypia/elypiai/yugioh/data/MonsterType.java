package com.elypia.elypiai.yugioh.data;

public enum MonsterType {

    NORMAL("Normal"),
    EFFECT("Effect"),
    FUSION("Fusion"),
    RITUAL("Ritual"),
    TUNER("Tuner"),
    SYNCHRO("Synchro"),
    FLIP("Flip");

    private final String NAME;

    MonsterType(String name) {
        NAME = name;
    }

    public static MonsterType get(String name) {
        for (MonsterType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return null;
    }
}
