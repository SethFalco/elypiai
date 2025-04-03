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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fun.falco.elypiai.mojang.models.MinecraftProfile;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangSessionApiTest {

    private static MojangSessionApi mojangSessionApi;

    @BeforeEach
    public void beforeEach() {
        mojangSessionApi = new MojangSessionApi();
    }

    @Test
    public void testGetMinecraftProfile() {
        UUID uuid = UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5");
        MinecraftProfile minecraftProfile = mojangSessionApi.getMinecraftProfile(uuid).blockingGet();

        final String expected = "Notch";
        final String actual = minecraftProfile.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void testBlockedServerList() {
        List<String> blockedServers = mojangSessionApi.getBlockedServerList().blockingGet();
        assertFalse(blockedServers.isEmpty());
    }
}
