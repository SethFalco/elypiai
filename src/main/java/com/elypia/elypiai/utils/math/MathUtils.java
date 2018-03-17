//package com.elypia.elypiai.utils.math;
//
//import com.elypia.elypiai.utils.Regex;
//
//import java.util.Objects;
//import java.util.regex.Matcher;
//
//public final class MathUtils {
//
//    private MathUtils() {
//
//    }
//
//    public static String asWrittenString(double value) {
//        StringBuilder builder = new StringBuilder();
//
//        if (value < 0)
//            builder.append("negative ");
//
//
//
//        // Significant digits
//
//        switch (length) {
//            case 33: hundredsTensAndUnits(builder, digits[32], digits[31], digits[30], "nonillion");
//            case 30: hundredsTensAndUnits(builder, digits[29], digits[28], digits[27], "octillion");
//            case 27: hundredsTensAndUnits(builder, digits[26], digits[25], digits[24], "septillion");
//            case 24: hundredsTensAndUnits(builder, digits[23], digits[22], digits[21], "sextillion");
//            case 21: hundredsTensAndUnits(builder, digits[20], digits[19], digits[18], "quintillion");
//            case 18: hundredsTensAndUnits(builder, digits[17], digits[16], digits[15], "quadrillion");
//            case 15: hundredsTensAndUnits(builder, digits[14], digits[13], digits[12], "trillion");
//            case 12: hundredsTensAndUnits(builder, digits[11], digits[10], digits[9], "billion");
//            case 9: hundredsTensAndUnits(builder, digits[8], digits[7], digits[6], "million");
//            case 6: hundredsTensAndUnits(builder, digits[5], digits[4], digits[3], "thousand");
//            case 3: hundredsTensAndUnits(builder, digits[2], digits[1], digits[0], null); break;
//            case 0: builder.append(" zero ");
//        }
//
//        // Decimal points
//
//        if (points != null) {
//            if (!builder.toString().endsWith(" "))
//                builder.append(" ");
//
//            builder.append("point");
//
//            if (points.isEmpty())
//                builder.append(" " + Number.ZERO);
//
//            for (char c : points.toCharArray())
//                builder.append(" " + Number.toString(c));
//        }
//
//        return builder.toString().trim();
//    }
//
//    private static void hundredsTensAndUnits(StringBuilder builder, char h, char t, char u, String set) {
//
//        if (h != '0')
//            builder.append(Number.toString(h) + " hundred");
//
//        String temp = Number.toString(t, u);
//
//        if ("zero".equals(temp) && h != '0')
//            return;
//
//        if (h != '0')
//            builder.append(" and ");
//
//        if (temp != null)
//            builder.append(temp);
//
//        else
//            builder.append(String.format("%s-%s", Number.toString(t, '0'), Number.toString(u)));
//
//        if (set != null)
//            builder.append(" " + set + " ");
//    }
//}
