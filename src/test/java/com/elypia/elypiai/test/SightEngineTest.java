package com.elypia.elypiai.test;

import com.elypia.elypiai.sightengine.*;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SightEngineTest {

    private static MockWebServer server;
    private static SightEngine se;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        se = new SightEngine("http://localhost:" + server.getPort(), "key", "secret");
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void normalInstance() {
        SightEngine engine = new SightEngine("key", "secret");
        assertEquals("key", engine.getUser());
        assertEquals("secret", engine.getSecret());
    }

    @Test
    public void parseNudityCheck() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"request\":{\"id\":\"req_2H1h0lpYaeb2cgqiw5q49\",\"timestamp\":1521750471.453,\"operations\":1},\"nudity\":{\"raw\":0.00937,\"partial\":0.00265,\"safe\":0.98798},\"media\":{\"id\":\"med_2H1hOoabmOeN6LroBUstH\",\"uri\":\"https://cdn.discordapp.com/attachments/233354622662737920/426400510757699585/unknown.png\"}}"));

        NudityScore nudity = se.detectNudity("https://cdn.discordapp.com/attachments/233354622662737920/426400510757699585/unknown.png").complete();
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(0.00937, nudity.getRaw()),
            () -> assertEquals(0.00265, nudity.getPartial()),
            () -> assertEquals(0.98798, nudity.getSafe()),
            () -> assertTrue(nudity.isSafe()),
            () -> assertTrue(nudity.isSafe(false))
        );
    }

    @Test
    public void parseNsfwImage() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"request\":{\"id\":\"req_2H1SGNHN50lRbZU8IXUQE\",\"timestamp\":1521752645.3063,\"operations\":1},\"nudity\":{\"raw\":0.6209,\"partial\":0.3791,\"safe\":0.00001},\"media\":{\"id\":\"med_2H1ScuvMYGbUeUC6jChvN\",\"uri\":\"https://media.discordapp.net/attachments/247142416564486154/424404113409966100/e84eda5.jpg\"}}"));

        NudityScore nudity = se.detectNudity("https://media.discordapp.net/attachments/247142416564486154/424404113409966100/e84eda5.jpg").complete();
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(0.6209, nudity.getRaw()),
            () -> assertEquals(0.3791, nudity.getPartial()),
            () -> assertEquals(0.00001, nudity.getSafe()),
            () -> assertFalse(nudity.isSafe()),
            () -> assertFalse(nudity.isSafe(false))
        );
    }

    @Test
    public void parsePartialNudity() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"request\":{\"id\":\"req_2H20U7b9oURKex5JQw3Nv\",\"timestamp\":1521753123.4497,\"operations\":1},\"nudity\":{\"raw\":0.23428,\"partial\":0.76505,\"partial_tag\":\"bikini\",\"safe\":0.00067},\"media\":{\"id\":\"med_2H209jFuzpFJDvVBWFD12\",\"uri\":\"https://3.bp.blogspot.com/-JK9xbSEuER4/V1RMAIhxhYI/AAAAAAAAAb8/ZrM39-kOG1E850PWqzGPttwXq_obxHpyACLcB/s1600/tumblr_nthg1n7Iz11uu2t7po1_1280.jpg\"}}"));

        NudityScore nudity = se.detectNudity("https://3.bp.blogspot.com/-JK9xbSEuER4/V1RMAIhxhYI/AAAAAAAAAb8/ZrM39-kOG1E850PWqzGPttwXq_obxHpyACLcB/s1600/tumblr_nthg1n7Iz11uu2t7po1_1280.jpg").complete();
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(0.23428, nudity.getRaw()),
            () -> assertEquals(0.76505, nudity.getPartial()),
            () -> assertEquals(0.00067, nudity.getSafe()),
            () -> assertEquals("bikini", nudity.getPartialTags()),
            () -> assertTrue(nudity.isSafe()),
            () -> assertFalse(nudity.isSafe(false))
        );
    }
}
