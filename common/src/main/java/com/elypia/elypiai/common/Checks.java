package com.elypia.elypiai.common;

import java.util.Objects;

public final class Checks {

    /** Require Non-Unknown Format */
    private static final String rnnf = "`%s` can not be `%s`#`%s`.";

    public static void requireNonUnknown(Enum e) {
        requireNonUnknown(e, "parameter");
    }

    public static void requireNonUnknown(Enum e, String param) {
        Objects.requireNonNull(param);

        if (e == null)
            return;

        if (e.ordinal() == 0 && e.name().equalsIgnoreCase("UNKNOWN"))
            throw new IllegalArgumentException(String.format(rnnf, param, e.getDeclaringClass(), e.name()));
    }
}
