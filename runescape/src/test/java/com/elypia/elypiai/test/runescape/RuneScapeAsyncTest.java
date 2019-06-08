package com.elypia.elypiai.test.runescape;

import com.elypia.elypiai.common.core.ex.FriendlyException;
import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.runescape.RuneScape;
import okhttp3.mockwebserver.*;
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
