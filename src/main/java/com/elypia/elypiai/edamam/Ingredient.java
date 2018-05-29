package com.elypia.elypiai.edamam;

import org.json.JSONObject;

public class Ingredient {

    /**
     * The name of the ingredient, occasionally with how it
     * should be prepared.
     */

    private String name;

    /**
     * The weight of the ingredient in grams.
     */

    private double weight;

    public Ingredient(JSONObject object) {
        name = object.getString("text");
        weight = object.getDouble("weight");
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
