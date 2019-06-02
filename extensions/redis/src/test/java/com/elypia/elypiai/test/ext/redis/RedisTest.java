package com.elypia.elypiai.test.ext.redis;

import com.elypia.elypiai.ext.redis.RedisExtension;
import com.elypia.elypiai.osu.Osu;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class RedisTest {

    private static MockWebServer server;
    private static Osu osu;
    private static RedisExtension ext;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        ext = new RedisExtension("localhost", 6379);
        osu = new Osu(new URL("http://localhost:" + server.getPort()), "api key", ext);
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
        ext.getJedis().disconnect();
    }

    @Test
    public void basicUsage() {

    }
}
