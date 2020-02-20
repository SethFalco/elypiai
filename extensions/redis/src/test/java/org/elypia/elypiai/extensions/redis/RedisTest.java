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

package org.elypia.elypiai.extensions.redis;

import okhttp3.Headers;
import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.test.TestUtils;
import org.elypia.elypiai.osu.*;
import org.elypia.elypiai.osu.data.OsuMode;
import org.junit.jupiter.api.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class RedisTest {

    private static final Headers headers = Headers.of("content-type", "application/json; charset=UTF-8");

    private static MockWebServer server;
    private static Osu osu;

    /** Mock Jedis instance so we don't connect to a real Redis instance. */
    private static Jedis jedis;

    @BeforeAll
    public static void beforeAll() throws IOException {
        server = new MockWebServer();
        server.start();
    }

    @AfterAll
    public static void afterAll() throws IOException {
        server.close();
    }

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        jedis = mock(Jedis.class);
        RedisExtension ext = new RedisExtension(jedis, 5);
        osu = new Osu(new URL("http://localhost:" + server.getPort()), "api key", ext);
    }

    /**
     * Perform a request, then once the result is cached, perform
     * two more requests, it should return the value of the first instead
     * as it's cached, rather than the actual ii and iii.
     *
     * @throws IOException
     */
    @Test
    public void osuUsage() throws IOException {
        String oneBody = TestUtils.read("osu-user_proliental_i.json");

        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(
            List.of()
        );

        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(
            List.of(oneBody, headers.value(0), "h2", String.valueOf(200), "")
        );

        server.enqueue(new MockResponse()
            .setHeaders(headers)
            .setBody(oneBody));

        server.enqueue(new MockResponse()
            .setHeaders(headers)
            .setBody(TestUtils.read("osu-user_proliental_ii.json")));

        Player one = osu.getPlayer(5573411, OsuMode.OSU, 31).completeGet();
        Player two = osu.getPlayer(5573411, OsuMode.OSU, 31).completeGet();

        assertEquals(one, two);
        verify(jedis, times(2)).lrange(anyString(), anyLong(), anyLong());
    }

    /**
     * This is just a normal osu instance with the extension but
     * with the requests hypotheically performed with a distance apart.
     *
     * @throws IOException
     */
    @Test
    public void waitAndFetchDifferent() throws IOException {
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(
            List.of()
        );

        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(
            List.of()
        );

        server.enqueue(new MockResponse()
            .setHeaders(headers)
            .setBody(TestUtils.read("osu-user_proliental_i.json")));
        server.enqueue(new MockResponse()
            .setHeaders(headers)
            .setBody(TestUtils.read("osu-user_proliental_ii.json")));

        Player one = osu.getPlayer(5573411, OsuMode.OSU, 31).completeGet();
        Player two = osu.getPlayer(5573411, OsuMode.OSU, 31).completeGet();

        assertNotEquals(one, two);
        verify(jedis, times(2)).lrange(anyString(), anyLong(), anyLong());
    }
}
