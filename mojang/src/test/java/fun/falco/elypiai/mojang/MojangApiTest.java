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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Instant;
import java.util.UUID;

import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import fun.falco.elypiai.mojang.models.MinecraftUser;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangApiTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static MojangApi mojangApi;

    @BeforeEach
    public void beforeEach() {
        mojangApi = new MojangApi(serverExtension.getRequestUrl());
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow(() -> new MojangStatusApi());
    }

    @WebServerTest("get-uuid.json")
    public void testGetUuid() {
        final MinecraftUser user = mojangApi.getUuid("DefectiveFox").blockingGet();

        assertAll(
            () -> assertEquals("DefectiveFox", user.getName()),
            () -> assertEquals(UUID.fromString("99f2c3f9-a6ff-4106-a278-73645382a9ce"), user.getUuid()),
            () -> assertFalse(user.isDemo()),
            () -> assertFalse(user.isLegacy())
        );
    }

    @WebServerTest("get-uuid-at-time.json")
    public void testGetUuidAtTime() {
        final Instant instant = Instant.ofEpochSecond(1451610061);
        final MinecraftUser user = mojangApi.getUuidAtTime("Seth", instant).blockingGet();

        assertAll(
            () -> assertEquals("iGhoul_", user.getName()),
            () -> assertEquals(UUID.fromString("5d23e1ab-be9f-4dc9-b1f7-67062708623f"), user.getUuid()),
            () -> assertFalse(user.isDemo()),
            () -> assertFalse(user.isLegacy())
        );
    }
}
