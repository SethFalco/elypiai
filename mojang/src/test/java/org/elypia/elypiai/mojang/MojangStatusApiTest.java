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

import org.elypia.elypiai.mojang.models.MojangServer;
import org.elypia.elypiai.mojang.models.ServerStatus;
import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangStatusApiTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static MojangStatusApi mojangStatusApi;

    @BeforeEach
    public void beforeEach() {
        mojangStatusApi = new MojangStatusApi(serverExtension.getRequestUrl());
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow(() -> new MojangStatusApi());
    }

    @WebServerTest("get-mojang-status.json")
    public void testStatus() {
        Map<MojangServer, ServerStatus> statuses = mojangStatusApi.getServerStatuses().blockingGet();

        final ServerStatus expected = ServerStatus.RED;
        final ServerStatus actual = statuses.get(MojangServer.MINECRAFT);

        assertEquals(expected, actual);
    }
}
