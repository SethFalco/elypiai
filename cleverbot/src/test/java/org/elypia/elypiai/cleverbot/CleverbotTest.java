/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.cleverbot;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.cleverbot.data.CleverTweak;
import org.elypia.retropia.test.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
@ExtendWith(MockResponseExtension.class)
public class CleverbotTest {

    @Response("reply_conversation.json")
    public static MockResponse replyConversation;

    @Response("reply_hello.json")
    public static MockResponse replyHello;

    @Response("reply_wack0-attent100.json")
    public static MockResponse replyWack0Attent100;

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
        server.enqueue(replyHello);
        CleverResponse response = cb.say("Hello, world!").complete();

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
        server.enqueue(replyWack0Attent100);

        Map<CleverTweak, Integer> tweaks = new HashMap<>();
        tweaks.put(CleverTweak.WACKY, 0);
        tweaks.put(CleverTweak.ATTENTIVE, 100);

        CleverResponse response = cb.say("Hello, world!", null, tweaks).complete();

        assertNotNull(response);
    }

    @Test
    public void makeRequestParseMultiHistory() throws IOException {
        server.enqueue(replyConversation);
        CleverResponse response = cb.say("now I'm sad :c", "MXYyCTh2MQlBdldYSVVNOEdDTkQJMUZ2MTU1OTE3MDUzNAk2NHZubyB1IQk2NGlUaGluayBpbSBiZXR0ZXIgdGhhbiB5b3UuCTY1dkhlbGxvLCB3b3JsZCEJNjVpSGVsbG8sIHJvYm90IQk=").complete();

        assertEquals(
            "User: Hello, world!\n" +
            "Cleverbot: Hello, robot!\n" +
            "User: no u!\n" +
            "Cleverbot: Think im better than you.\n" +
            "User: now I'm sad :c.\n" +
            "Cleverbot: Oh. Why is that?", response.getHistoryScript()
        );
    }

    @Test
    public void tweakEnum() {
        CleverTweak tweak = CleverTweak.ATTENTIVE;
        assertEquals(3, tweak.getId());
        assertEquals("cb_settings_tweak3", tweak.getName());
    }
}
