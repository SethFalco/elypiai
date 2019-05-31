package com.elypia.elypiai.common.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Utility methods for testing the rest of Elypiai.
 */
public final class TestUtils {

    private static final String format = "/%s.json";

    private TestUtils() {
        // Can't construct it.
    }

    /**
     * Load all content from the file specified.
     * File name should be named relative to the root of the classpath
     * with <code>.json</code> extension omitted.
     *
     * @param name The name of the file to load excluding the extension.
     * @return The content of the file.
     */
    public static String read(String name) throws IOException {
        String path = String.format(format, name);

        try (InputStream stream = TestUtils.class.getResourceAsStream(path)) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
