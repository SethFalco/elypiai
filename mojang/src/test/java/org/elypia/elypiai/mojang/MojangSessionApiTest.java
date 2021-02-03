/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

import org.elypia.elypiai.mojang.models.MinecraftProfile;
import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class MojangSessionApiTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static MojangSessionApi mojangSessionApi;

    @BeforeEach
    public void beforeEach() {
        mojangSessionApi = new MojangSessionApi(serverExtension.getRequestUrl());
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow(() -> new MojangSessionApi());
    }

    @WebServerTest("get-profile-skin-cape.json")
    public void testGetMinecraftProfile() {
        UUID uuid = MinecraftUtils.trimmedUuidToUuid("069a79f444e94726a5befca90e38aaf5");
        MinecraftProfile minecraftProfile = mojangSessionApi.getMinecraftProfile(uuid).blockingGet();

        final String expected = "Notch";
        final String actual = minecraftProfile.getName();

        assertEquals(expected, actual);
    }

    @WebServerTest("get-blocked-servers.txt")
    public void testBlockedServerList() {
        List<String> blockedServers = mojangSessionApi.getBlockedServerList().blockingGet();

        final String expected = "72fd29f430c91c583bb7216fe673191dc25a7e18";
        final String actual = blockedServers.get(0);

        assertEquals(expected, actual);
    }
}
