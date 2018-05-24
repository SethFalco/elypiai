package com.elypia.elypiai.edamam;

import org.json.JSONObject;

import java.util.Objects;

public class Nutrient {

    private String label;
    private double quantity;
    private String unit;

    public Nutrient(JSONObject object) {
        Objects.requireNonNull(object);

        label = object.getString("label");
        quantity = object.getDouble("quantity");
        unit = object.getString("unit");
    }

    public String getLabel() {
        return label;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}
