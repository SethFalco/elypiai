package com.elypia.elypiai.utils.math;

public class Dimension3D {

    private double length;
    private double width;
    private double height;
    private double weight;

    public Dimension3D(double length, double width, double height) {
        this(length, width, height, 0);
    }

    public Dimension3D(double length, double width, double height, double weight) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public double getVolume() {
        return length * width * height;
    }

    public double getSurfaceArea() {
        return (2 * length * width) + (2 * width * height) + (2 * length * height);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
