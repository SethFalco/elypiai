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

package fun.falco.elypiai.mojang;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fun.falco.elypiai.mojang.models.MinecraftUser;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangApiTest {

    private static MojangApi mojangApi;

    @BeforeEach
    public void beforeEach() {
        mojangApi = new MojangApi();
    }

    @Test
    public void testGetUuid() {
        final MinecraftUser user = mojangApi.getUuid("Notch").blockingGet();

        assertAll(
            () -> assertEquals("Notch", user.getName()),
            () -> assertEquals(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), user.getUuid()),
            () -> assertFalse(user.isDemo()),
            () -> assertFalse(user.isLegacy())
        );
    }
}
