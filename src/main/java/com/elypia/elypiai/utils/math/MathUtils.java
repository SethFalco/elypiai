package com.elypia.elypiai.utils.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MathUtils {

    private MathUtils() {
        // Don't construct this!
    }

    public static String asWritten(long value) {
        if (value == 0)
            return Number.ZERO.getName();

        List<String> tokens = new ArrayList<>();

        if (value < 0) {
            tokens.add("negative");
            value *= -1;
        }

        Number[] numbers = Number.values();

        for (int i = numbers.length - 1; i >= Number.ONE.ordinal(); i--) {
            Number number = numbers[i];

            if (number == Number.THOUSAND)
                i = Number.TWO.ordinal();

            long num = value / number.asNumeric();

            if (num == 0)
                continue;

            char[] chars = String.valueOf(num).toCharArray();
            Number[] digits = Number.of(chars);

            switch (digits.length) {
                case 3:
                    tokens.add(digits[0].getName() + " hundred");

                    if (digits[1] == Number.ZERO && digits[2] == Number.ZERO)
                        break;

                    digits = Arrays.copyOfRange(digits, 1, digits.length);

                case 2:
                    if (digits[0] != Number.ZERO) {
                        if (tokens.size() > 0)
                            tokens.add("and");

                        if (digits[0] == Number.ONE)
                            tokens.add(Number.of(digits[1].asNumeric() + 10).getName());

                        else if (digits[1] == Number.ZERO)
                            tokens.add(Number.of(digits[0].asNumeric() * 10).getName());

                        else
                            tokens.add(Number.of(digits[0].asNumeric() * 10).getName() + "-" + digits[1].getName());

                        break;
                    }

                    digits = Arrays.copyOfRange(digits, 1, digits.length);

                case 1:
                    if (digits[0] != Number.ZERO) {
                        if (tokens.size() > 0)
                            tokens.add("and");

                        tokens.add(digits[0].getName());
                    }

                    break;
            }

            if (number != Number.ONE)
                tokens.add(number.getName());

            value -= num * number.asNumeric();
        }

        return String.join(" ", tokens);
    }
}
