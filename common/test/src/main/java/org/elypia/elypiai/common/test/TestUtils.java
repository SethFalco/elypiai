/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.common.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Utility methods for testing the rest of Elypiai.
 *
 * @author seth@elypia.org (Seth Falco)
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
