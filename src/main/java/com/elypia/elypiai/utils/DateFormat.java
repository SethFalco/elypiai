package com.elypia.elypiai.utils;

import java.util.regex.Pattern;

public enum DateFormat {

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"),
    DD_MMM_YYYY_HH_MM("dd-MMM-yyyy HH:mm", "(?i)\\d{2}-[A-Z]{3}-\\d{4} \\d{2}:\\d{2}");

    private final String FORMAT;
    private final Pattern PATTERN;

    DateFormat(final String FORMAT, final String REGEX) {
        this.FORMAT = FORMAT;
        this.PATTERN = Pattern.compile(REGEX);
    }

    public String getFormat() {
        return FORMAT;
    }

    public Pattern getPattern() {
        return PATTERN;
    }
}
