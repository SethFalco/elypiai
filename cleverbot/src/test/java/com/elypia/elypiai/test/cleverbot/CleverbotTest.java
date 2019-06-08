package com.elypia.elypiai.test.cleverbot;

import com.elypia.elypiai.cleverbot.CleverResponse;
import com.elypia.elypiai.cleverbot.Cleverbot;
import com.elypia.elypiai.cleverbot.data.CleverTweak;
import com.elypia.elypiai.common.test.TestUtils;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CleverbotTest {

    private static MockWebServer server;
    private static Cleverbot cb;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        cb = new Cleverbot(new URL("http://localhost:" + server.getPort()), "api key");
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void testCleverbot() {
        assertNotNull(cb);
        assertEquals("api key", cb.getApiKey());
    }

    @Test
    public void parseResponse() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("reply_hello.json")));
        CleverResponse response = cb.say("Hello, world!").completeGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("MXYxCTh2MQlBdldYSVVNOEdDTkQJMUZ2MTU1OTE3MDUzNAk2NHZIZWxsbywgd29ybGQhCTY0aUhlbGxvLCByb2JvdCEJ", response.getCs()),
            () -> assertEquals(1, response.getInteractionCount()),
            () -> assertEquals("Hello, world!", response.getInput()),
            () -> assertEquals("Hello, robot!", response.getOutput()),
            () -> assertEquals("WXIUM8GCND", response.getConversationId()),
            () -> assertEquals("Init:4000000,Full:197,Other:197,Row:103,Score:2638,Id:455620452,Len:13,", response.getErrorLine()),
            () -> assertEquals(134, response.getTimeTaken()),
            () -> assertEquals(0, response.getTimeElapsed()),
            () -> assertEquals(1, response.getHistory().size())
        );
    }

    @Test
    public void makeRequestWithTweak() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("reply_wack0-attent100.json")));

        Map<CleverTweak, Integer> tweaks = new HashMap<>();
        tweaks.put(CleverTweak.WACKY, 0);
        tweaks.put(CleverTweak.ATTENTIVE, 100);

        CleverResponse response = cb.say("Hello, world!", null, tweaks).completeGet();

        assertNotNull(response);
    }

    @Test
    public void makeRequestParseMultiHistory() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("reply_conversation.json")));
        CleverResponse response = cb.say("now I'm sad :c", "MXYyCTh2MQlBdldYSVVNOEdDTkQJMUZ2MTU1OTE3MDUzNAk2NHZubyB1IQk2NGlUaGluayBpbSBiZXR0ZXIgdGhhbiB5b3UuCTY1dkhlbGxvLCB3b3JsZCEJNjVpSGVsbG8sIHJvYm90IQk=").completeGet();

        assertEquals(
                "User: Hello, world!\n" +
                "Cleverbot: Hello, robot!\n" +
                "User: no u!\n" +
                "Cleverbot: Think im better than you.\n" +
                "User: now I'm sad :c.\n" +
                "Cleverbot: Oh. Why is that?", response.getHistoryScript());
    }

    @Test
    public void tweakEnum() {
        CleverTweak tweak = CleverTweak.ATTENTIVE;
        assertEquals(3, tweak.getId());
        assertEquals("cb_settings_tweak3", tweak.getName());
    }
}
