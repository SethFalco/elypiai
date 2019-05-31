package com.elypia.elypiai.runescape.test;

import com.elypia.elypiai.common.FriendlyException;
import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.runescape.RuneScape;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
        server.enqueue(new MockResponse().setBody(TestUtils.read("profile_private")));

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
