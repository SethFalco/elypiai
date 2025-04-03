/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package org.elypia.elypiai.mojang;

import org.elypia.elypiai.mojang.models.DefaultSkin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public final class MinecraftUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "fffffff0-ffff-fff0-ffff-fff0fffffff0",
        "fffffff0-ffff-fff0-ffff-fff1fffffff1",
        "fffffff0-ffff-fff1-ffff-fff0fffffff1",
        "fffffff0-ffff-fff1-ffff-fff1fffffff0",
        "fffffff1-ffff-fff0-ffff-fff0fffffff1",
        "fffffff1-ffff-fff0-ffff-fff1fffffff0",
        "fffffff1-ffff-fff1-ffff-fff0fffffff0",
        "fffffff1-ffff-fff1-ffff-fff1fffffff1"
    })
    public void testDefaultSkinUtilSteve(final String uuidString) {
        final DefaultSkin expected = DefaultSkin.STEVE;
        final DefaultSkin actual = MinecraftUtils.getDefaultSkin(uuidString);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "fffffff0-ffff-fff0-ffff-fff0fffffff1",
        "fffffff0-ffff-fff0-ffff-fff1fffffff0",
        "fffffff0-ffff-fff1-ffff-fff0fffffff0",
        "fffffff0-ffff-fff1-ffff-fff1fffffff1",
        "fffffff1-ffff-fff0-ffff-fff0fffffff0",
        "fffffff1-ffff-fff0-ffff-fff1fffffff1",
        "fffffff1-ffff-fff1-ffff-fff0fffffff1",
        "fffffff1-ffff-fff1-ffff-fff1fffffff0"
    })
    public void testDefaultSkinUtilAlex(final String uuidString) {
        final DefaultSkin expected = DefaultSkin.ALEX;
        final DefaultSkin actual = MinecraftUtils.getDefaultSkin(uuidString);

        assertEquals(expected, actual);
    }

    @Test
    public void testHyphanateUuidNotch() {
        final UUID expected = UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5");
        final UUID actual = MinecraftUtils.trimmedUuidToUuid("069a79f444e94726a5befca90e38aaf5");

        assertEquals(expected, actual);
    }
}
