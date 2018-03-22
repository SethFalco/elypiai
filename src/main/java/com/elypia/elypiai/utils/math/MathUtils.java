package com.elypia.elypiai.utils.math;

import java.util.Arrays;
import java.util.StringJoiner;

import static com.elypia.elypiai.utils.math.Numeral.*;

public final class MathUtils {

    private static final Numeral[] NUMERALS = {
        QUINTILLION,
        QUADRILLION,
        TRILLION,
        BILLION,
        MILLION,
        THOUSAND,
        ONE
    };

    private MathUtils() {
        // Don't construct this!
    }

    public static String asWritten(long value) {
        if (value == 0)
            return Numeral.ZERO.getName();

        StringJoiner joiner = new StringJoiner(" ");

        if (value < 0) {
            joiner.add("negative");
            value *= -1;
        }

        for (Numeral number : NUMERALS) {
            long num = value / number.asNumeric();

            if (num == 0)
                continue;

            char[] chars = String.valueOf(num).toCharArray();
            Numeral[] digits = Numeral.of(chars);

            switch (digits.length) {
                case 3:
                    joiner.add(digits[0].getName() + " hundred");

                    if (digits[1] == Numeral.ZERO && digits[2] == Numeral.ZERO)
                        break;

                    digits = Arrays.copyOfRange(digits, 1, digits.length);

                case 2:
                    if (digits[0] != Numeral.ZERO) {
                        if (joiner.length() > 0)
                            joiner.add("and");

                        if (digits[0] == Numeral.ONE)
                            joiner.add(Numeral.of(digits[1].asNumeric() + 10).getName());

                        else if (digits[1] == Numeral.ZERO)
                            joiner.add(Numeral.of(digits[0].asNumeric() * 10).getName());

                        else
                            joiner.add(Numeral.of(digits[0].asNumeric() * 10).getName() + "-" + digits[1].getName());

                        break;
                    }

                    digits = Arrays.copyOfRange(digits, 1, digits.length);

                case 1:
                    if (digits[0] != Numeral.ZERO) {
                        if (joiner.length() > 0)
                            joiner.add("and");

                        joiner.add(digits[0].getName());
                    }

                    break;
            }

            if (number != Numeral.ONE)
                joiner.add(number.getName());

            value -= num * number.asNumeric();
        }

        return joiner.toString();
    }
}
