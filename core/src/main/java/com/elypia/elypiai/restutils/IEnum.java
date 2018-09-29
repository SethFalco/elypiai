package com.elypia.elypiai.restutils;

public interface IEnum<T extends IEnum<? extends Enum>> {

    /**
     * Obtain the value this enum constant is referred
     * to in the formal API documentation.
     *
     * @return The name of this enum the API uses.
     */
    String getName();

    /**
     * Iterate through an enum and search if any of
     * the values match the name provided.
     *
     * @param name
     * @return
     */
    default T get(String name) {
        var clazz = this.getClass();

        if (!clazz.isEnum())
            throw new IllegalStateException("Not an enum!");

        for (var e : this.getClass().getEnumConstants()) {
            if (((Enum)e).name().equals(name))
                return (T)e;
        }

        return null;
    }
}
