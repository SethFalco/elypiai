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

package fun.falco.elypiai.cleverbot;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import fun.falco.elypiai.cleverbot.data.CleverTweak;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class CleverbotTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static Cleverbot cb;

    @BeforeEach
    public void beforeEach() {
        cb = new Cleverbot("api key", serverExtension.getRequestUrl());
    }

    @Test
    public void testCleverbot() {
        assertNotNull(cb);
        assertEquals("api key", cb.getApiKey());
    }

    @WebServerTest("reply-hello.json")
    public void parseResponse() {
        CleverResponse response = cb.say("Hello, world!").blockingGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("MXYxCTh2MQlBdldYSVVNOEdDTkQJMUZ2MTU1OTE3MDUzNAk2NHZIZWxsbywgd29ybGQhCTY0aUhlbGxvLCByb2JvdCEJ", response.getCs()),
            () -> assertEquals(1, response.getInteractionCount()),
            () -> assertEquals("Hello, world!", response.getInput()),
            () -> assertEquals("Hello, robot!", response.getOutput()),
            () -> assertEquals("WXIUM8GCND", response.getConversationId()),
            () -> assertEquals("Init:4000000,Full:197,Other:197,Row:103,Score:2638,Id:455620452,Len:13,", response.getErrorLine()),
            () -> assertEquals(134, response.getTimeTaken()),
            () -> assertEquals(0, response.getTimeElapsed()),
            () -> assertEquals(1, response.getInteractions().size())
        );
    }

    @WebServerTest("reply-wack0-attent100.json")
    public void makeRequestWithTweak() {
        Map<CleverTweak, Integer> tweaks = new HashMap<>();
        tweaks.put(CleverTweak.WACKY, 0);
        tweaks.put(CleverTweak.ATTENTIVE, 100);

        CleverResponse response = cb.say("Hello, world!", null, tweaks).blockingGet();

        assertNotNull(response);
    }

    @Test
    public void tweakEnum() {
        CleverTweak tweak = CleverTweak.ATTENTIVE;
        assertEquals(3, tweak.getId());
        assertEquals("cb_settings_tweak3", tweak.getName());
    }
}
