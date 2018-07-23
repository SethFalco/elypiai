package com.elypia.elypiai.utils;

public class Tuple<T1, T2> {

    private T1 one;
    private T2 two;

    public static <T1, T2> Tuple<T1, T2> of(T1 one, T2 two) {
        return new Tuple<>(one, two);
    }

    private Tuple(T1 one, T2 two) {
        this.one = one;
        this.two = two;
    }

    public T1 itemOne() {
        return one;
    }

    public void itemOne(T1 one) {
        this.one = one;
    }

    public T2 itemTwo() {
        return two;
    }

    public void itemTwo(T2 two) {
        this.two = two;
    }
}
