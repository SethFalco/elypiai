package com.elypia.elypiai.utils;

public class Tuple<T1, T2> {

    private T1 valueOne;
    private T2 valueTwo;

    public static <T1, T2> Tuple<T1, T2> of(T1 valueOne, T2 valueTwo) {
        return new Tuple<>(valueOne, valueTwo);
    }

    private Tuple(T1 valueOne, T2 valueTwo) {
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public T1 getValueOne() {
        return valueOne;
    }

    public void setValueOne(T1 valueOne) {
        this.valueOne = valueOne;
    }

    public T2 getValueTwo() {
        return valueTwo;
    }

    public void setValueTwo(T2 valueTwo) {
        this.valueTwo = valueTwo;
    }
}
