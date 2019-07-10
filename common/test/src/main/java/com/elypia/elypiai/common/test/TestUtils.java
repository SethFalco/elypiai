package com.elypia.elypiai.common.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Utility methods for testing the rest of Elypiai.
 */
public final class TestUtils {

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
     * @throws IOException If no resource if found at this path.
     */
    public static String read(String name) throws IOException {
        String path = "/" + name;

        try (InputStream stream = TestUtils.class.getResourceAsStream(path)) {
            Objects.requireNonNull(stream, "Resource at `" + path + "`" + " doesn't exist.");
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
