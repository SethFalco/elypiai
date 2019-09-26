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

package org.elypia.elypiai.runescape;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.core.ex.FriendlyException;
import org.elypia.elypiai.common.test.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuneScapeAsyncTest {

    private static MockWebServer server;
    private static RuneScape rs;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        rs = new RuneScape(new URL("http://localhost:" + server.getPort()));
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void parsePrivateUser() throws IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("profile_private.json")));

        CountDownLatch latch = new CountDownLatch(1);

        rs.getUser("Zezima").queue(
            o -> {
                Assert.fail("This should have produced an exception.");
                latch.countDown();
            },
            ex -> {
                assertTrue(ex instanceof FriendlyException);
                latch.countDown();
            }
        );

        latch.await(8, TimeUnit.SECONDS);
    }
}
