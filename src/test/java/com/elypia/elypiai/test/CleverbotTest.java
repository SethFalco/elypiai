package com.elypia.elypiai.test;

import com.elypia.elypiai.cleverbot.*;
import com.elypia.elypiai.cleverbot.data.CleverTweak;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CleverbotTest {

    private static MockWebServer server;
    private static Cleverbot cb;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        cb = new Cleverbot("http://localhost:" + server.getPort(), "api key");
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void testOsu() {
        Cleverbot cb = new Cleverbot("api key");
        assertNotNull(cb);
        assertEquals("api key", cb.getApiKey());
    }

    @Test
    public void parseResponse() throws IOException {
        String json = "{\"cs\":\"MXYxCUF2TUNBV1lLVEEyRgkxRnYxNTMxNDIyODQyCTY0dkhlbGxvLCB3b3JsZCEJNjRpSG93IGFyZSB5b3U/CQ==\",\"interaction_count\":\"1\",\"input\":\"Hello, world!\",\"input_other\":\"\",\"input_label\":\"\",\"predicted_input\":\"\",\"accuracy\":\"\",\"output_label\":\"\",\"output\":\"How are you?\",\"conversation_id\":\"MCAWYKTA2F\",\"errorline\":\"Init:16802173,Full:1170,Other:161,Row:12,Score:2668,Id:66187074,Len:12,\",\"database_version\":\"3233\",\"software_version\":\"3233\",\"time_taken\":\"119\",\"random_number\":\"901\",\"time_second\":\"2\",\"time_minute\":\"14\",\"time_hour\":\"20\",\"time_day_of_week\":\"4\",\"time_day\":\"12\",\"time_month\":\"7\",\"time_year\":\"2018\",\"reaction\":\"\",\"reaction_tone\":\"\",\"emotion\":\"\",\"emotion_tone\":\"\",\"clever_accuracy\":\"26\",\"clever_output\":\"How are you?\",\"clever_match\":\"\",\"CSRES30\":\"\",\"time_elapsed\":\"0\",\"filtered_input\":\"Hello, world!\",\"filtered_input_other\":\"\",\"reaction_degree\":\"\",\"emotion_degree\":\"\",\"reaction_values\":\"\",\"emotion_values\":\"\",\"callback\":\"\",\"interaction_1\":\"Hello, world!\",\"interaction_1_other\":\"How are you?\",\"interaction_2\":\"\",\"interaction_3\":\"\",\"interaction_4\":\"\",\"interaction_5\":\"\",\"interaction_6\":\"\",\"interaction_7\":\"\",\"interaction_8\":\"\",\"interaction_9\":\"\",\"interaction_10\":\"\",\"interaction_11\":\"\",\"interaction_12\":\"\",\"interaction_13\":\"\",\"interaction_14\":\"\",\"interaction_15\":\"\",\"interaction_16\":\"\",\"interaction_17\":\"\",\"interaction_18\":\"\",\"interaction_19\":\"\",\"interaction_20\":\"\",\"interaction_21\":\"\",\"interaction_22\":\"\",\"interaction_23\":\"\",\"interaction_24\":\"\",\"interaction_25\":\"\",\"interaction_26\":\"\",\"interaction_27\":\"\",\"interaction_28\":\"\",\"interaction_29\":\"\",\"interaction_30\":\"\",\"interaction_31\":\"\",\"interaction_32\":\"\",\"interaction_33\":\"\",\"interaction_34\":\"\",\"interaction_35\":\"\",\"interaction_36\":\"\",\"interaction_37\":\"\",\"interaction_38\":\"\",\"interaction_39\":\"\",\"interaction_40\":\"\",\"interaction_41\":\"\",\"interaction_42\":\"\",\"interaction_43\":\"\",\"interaction_44\":\"\",\"interaction_45\":\"\",\"interaction_46\":\"\",\"interaction_47\":\"\",\"interaction_48\":\"\",\"interaction_49\":\"\",\"interaction_50\":\"\"}\t\n";
        server.enqueue(new MockResponse().setBody(json));

        CleverResponse response = cb.say("Hello, world!").complete();
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("MXYxCUF2TUNBV1lLVEEyRgkxRnYxNTMxNDIyODQyCTY0dkhlbGxvLCB3b3JsZCEJNjRpSG93IGFyZSB5b3U/CQ==", response.getCs()),
            () -> assertEquals(1, response.getInteractionCount()),
            () -> assertEquals("Hello, world!", response.getInput()),
            () -> assertEquals("How are you?", response.getOutput()),
            () -> assertEquals("MCAWYKTA2F", response.getConversationId()),
            () -> assertEquals("Init:16802173,Full:1170,Other:161,Row:12,Score:2668,Id:66187074,Len:12,", response.getErrorLine()),
            () -> assertEquals(119, response.getTimeTaken()),
            () -> assertEquals(0, response.getTimeElapsed()),
            () -> assertEquals(1, response.getHistory().size())
        );
    }

    @Test
    public void parseResponseHistory() throws IOException {
        String json = "{\"cs\":\"MXYxCUF2TUNBV1lLVEEyRgkxRnYxNTMxNDIyODQyCTY0dkhlbGxvLCB3b3JsZCEJNjRpSG93IGFyZSB5b3U/CQ==\",\"interaction_count\":\"1\",\"input\":\"Hello, world!\",\"input_other\":\"\",\"input_label\":\"\",\"predicted_input\":\"\",\"accuracy\":\"\",\"output_label\":\"\",\"output\":\"How are you?\",\"conversation_id\":\"MCAWYKTA2F\",\"errorline\":\"Init:16802173,Full:1170,Other:161,Row:12,Score:2668,Id:66187074,Len:12,\",\"database_version\":\"3233\",\"software_version\":\"3233\",\"time_taken\":\"119\",\"random_number\":\"901\",\"time_second\":\"2\",\"time_minute\":\"14\",\"time_hour\":\"20\",\"time_day_of_week\":\"4\",\"time_day\":\"12\",\"time_month\":\"7\",\"time_year\":\"2018\",\"reaction\":\"\",\"reaction_tone\":\"\",\"emotion\":\"\",\"emotion_tone\":\"\",\"clever_accuracy\":\"26\",\"clever_output\":\"How are you?\",\"clever_match\":\"\",\"CSRES30\":\"\",\"time_elapsed\":\"0\",\"filtered_input\":\"Hello, world!\",\"filtered_input_other\":\"\",\"reaction_degree\":\"\",\"emotion_degree\":\"\",\"reaction_values\":\"\",\"emotion_values\":\"\",\"callback\":\"\",\"interaction_1\":\"Hello, world!\",\"interaction_1_other\":\"How are you?\",\"interaction_2\":\"\",\"interaction_3\":\"\",\"interaction_4\":\"\",\"interaction_5\":\"\",\"interaction_6\":\"\",\"interaction_7\":\"\",\"interaction_8\":\"\",\"interaction_9\":\"\",\"interaction_10\":\"\",\"interaction_11\":\"\",\"interaction_12\":\"\",\"interaction_13\":\"\",\"interaction_14\":\"\",\"interaction_15\":\"\",\"interaction_16\":\"\",\"interaction_17\":\"\",\"interaction_18\":\"\",\"interaction_19\":\"\",\"interaction_20\":\"\",\"interaction_21\":\"\",\"interaction_22\":\"\",\"interaction_23\":\"\",\"interaction_24\":\"\",\"interaction_25\":\"\",\"interaction_26\":\"\",\"interaction_27\":\"\",\"interaction_28\":\"\",\"interaction_29\":\"\",\"interaction_30\":\"\",\"interaction_31\":\"\",\"interaction_32\":\"\",\"interaction_33\":\"\",\"interaction_34\":\"\",\"interaction_35\":\"\",\"interaction_36\":\"\",\"interaction_37\":\"\",\"interaction_38\":\"\",\"interaction_39\":\"\",\"interaction_40\":\"\",\"interaction_41\":\"\",\"interaction_42\":\"\",\"interaction_43\":\"\",\"interaction_44\":\"\",\"interaction_45\":\"\",\"interaction_46\":\"\",\"interaction_47\":\"\",\"interaction_48\":\"\",\"interaction_49\":\"\",\"interaction_50\":\"\"}\t\n";
        server.enqueue(new MockResponse().setBody(json));

        String history = cb.say("Hello, world!").complete().getHistoryScript();
        assertEquals("User: Hello, world!\nCleverbot: How are you?", history);
    }

    @Test
    public void makeRequestWithTweak() throws IOException {
        String json = "{\"cs\":\"MXYxCTh2MQlBdldYSjlVWEFXUjEJMUZ2MTUzMTQyNzQxMgk2NHZIZWxsbywgd29ybGQhCTY0aUhvdyBhcmUgeW91Pwk=\",\"interaction_count\":\"1\",\"input\":\"Hello, world!\",\"input_other\":\"\",\"input_label\":\"\",\"predicted_input\":\"\",\"accuracy\":\"\",\"output_label\":\"welcome\",\"output\":\"How are you?\",\"conversation_id\":\"WXJ9UXAWR1\",\"errorline\":\"Init:12496770,Full:577,Other:234,Row:115,Score:2824,Id:263938484,Len:12,\",\"database_version\":\"3167\",\"software_version\":\"3233\",\"time_taken\":\"216\",\"random_number\":\"84\",\"time_second\":\"12\",\"time_minute\":\"30\",\"time_hour\":\"21\",\"time_day_of_week\":\"3\",\"time_day\":\"12\",\"time_month\":\"7\",\"time_year\":\"2018\",\"reaction\":\"\",\"reaction_tone\":\"\",\"emotion\":\"\",\"emotion_tone\":\"\",\"clever_accuracy\":\"28\",\"clever_output\":\"How are you?\",\"clever_match\":\"\",\"CSRES30\":\"\",\"time_elapsed\":\"0\",\"filtered_input\":\"Hello, world!\",\"filtered_input_other\":\"\",\"reaction_degree\":\"\",\"emotion_degree\":\"\",\"reaction_values\":\"\",\"emotion_values\":\"\",\"callback\":\"\",\"interaction_1\":\"Hello, world!\",\"interaction_1_other\":\"How are you?\",\"interaction_2\":\"\",\"interaction_3\":\"\",\"interaction_4\":\"\",\"interaction_5\":\"\",\"interaction_6\":\"\",\"interaction_7\":\"\",\"interaction_8\":\"\",\"interaction_9\":\"\",\"interaction_10\":\"\",\"interaction_11\":\"\",\"interaction_12\":\"\",\"interaction_13\":\"\",\"interaction_14\":\"\",\"interaction_15\":\"\",\"interaction_16\":\"\",\"interaction_17\":\"\",\"interaction_18\":\"\",\"interaction_19\":\"\",\"interaction_20\":\"\",\"interaction_21\":\"\",\"interaction_22\":\"\",\"interaction_23\":\"\",\"interaction_24\":\"\",\"interaction_25\":\"\",\"interaction_26\":\"\",\"interaction_27\":\"\",\"interaction_28\":\"\",\"interaction_29\":\"\",\"interaction_30\":\"\",\"interaction_31\":\"\",\"interaction_32\":\"\",\"interaction_33\":\"\",\"interaction_34\":\"\",\"interaction_35\":\"\",\"interaction_36\":\"\",\"interaction_37\":\"\",\"interaction_38\":\"\",\"interaction_39\":\"\",\"interaction_40\":\"\",\"interaction_41\":\"\",\"interaction_42\":\"\",\"interaction_43\":\"\",\"interaction_44\":\"\",\"interaction_45\":\"\",\"interaction_46\":\"\",\"interaction_47\":\"\",\"interaction_48\":\"\",\"interaction_49\":\"\",\"interaction_50\":\"\"}\t\n";
        server.enqueue(new MockResponse().setBody(json));

        Map<CleverTweak, Integer> tweaks = new HashMap<>();
        tweaks.put(CleverTweak.WACKY, 0);
        tweaks.put(CleverTweak.ATTENTIVE, 100);

        CleverResponse response = cb.say("Hello, world!", null, tweaks).complete();
        assertNotNull(response);
    }

    @Test
    public void makeRequestWithTweakParseMultiHistory() throws IOException {
        String json = "{\"cs\":\"MXYyCTh2MQlBdldYSjlVWEFXUjEJMUZ2MTUzMTQyNzQxMgk2NHZIZWxsbywgd29ybGQhCTY0aUhhaGEgZ29vZGJ5ZS4JNjV2SGVsbG8sIHdvcmxkIQk2NWlIb3cgYXJlIHlvdT8J\",\"interaction_count\":\"2\",\"input\":\"Hello, world!\",\"input_other\":\"\",\"input_label\":\"\",\"predicted_input\":\"\",\"accuracy\":\"\",\"output_label\":\"welcome\",\"output\":\"Haha goodbye.\",\"conversation_id\":\"WXJ9UXAWR1\",\"errorline\":\"Init:3480000,Full:444,Other:250,Row:9,Score:3434,Id:349391679,Len:13,\",\"database_version\":\"3167\",\"software_version\":\"3233\",\"time_taken\":\"173\",\"random_number\":\"747\",\"time_second\":\"19\",\"time_minute\":\"38\",\"time_hour\":\"21\",\"time_day_of_week\":\"3\",\"time_day\":\"12\",\"time_month\":\"7\",\"time_year\":\"2018\",\"reaction\":\"\",\"reaction_tone\":\"\",\"emotion\":\"\",\"emotion_tone\":\"\",\"clever_accuracy\":\"34\",\"clever_output\":\"Haha goodbye.\",\"clever_match\":\"\",\"CSRES30\":\"\",\"time_elapsed\":\"487\",\"filtered_input\":\"Hello, world!\",\"filtered_input_other\":\"\",\"reaction_degree\":\"\",\"emotion_degree\":\"\",\"reaction_values\":\"\",\"emotion_values\":\"\",\"callback\":\"\",\"interaction_1\":\"Hello, world!\",\"interaction_1_other\":\"Haha goodbye.\",\"interaction_2\":\"Hello, world!\",\"interaction_2_other\":\"How are you?\",\"interaction_3\":\"\",\"interaction_4\":\"\",\"interaction_5\":\"\",\"interaction_6\":\"\",\"interaction_7\":\"\",\"interaction_8\":\"\",\"interaction_9\":\"\",\"interaction_10\":\"\",\"interaction_11\":\"\",\"interaction_12\":\"\",\"interaction_13\":\"\",\"interaction_14\":\"\",\"interaction_15\":\"\",\"interaction_16\":\"\",\"interaction_17\":\"\",\"interaction_18\":\"\",\"interaction_19\":\"\",\"interaction_20\":\"\",\"interaction_21\":\"\",\"interaction_22\":\"\",\"interaction_23\":\"\",\"interaction_24\":\"\",\"interaction_25\":\"\",\"interaction_26\":\"\",\"interaction_27\":\"\",\"interaction_28\":\"\",\"interaction_29\":\"\",\"interaction_30\":\"\",\"interaction_31\":\"\",\"interaction_32\":\"\",\"interaction_33\":\"\",\"interaction_34\":\"\",\"interaction_35\":\"\",\"interaction_36\":\"\",\"interaction_37\":\"\",\"interaction_38\":\"\",\"interaction_39\":\"\",\"interaction_40\":\"\",\"interaction_41\":\"\",\"interaction_42\":\"\",\"interaction_43\":\"\",\"interaction_44\":\"\",\"interaction_45\":\"\",\"interaction_46\":\"\",\"interaction_47\":\"\",\"interaction_48\":\"\",\"interaction_49\":\"\",\"interaction_50\":\"\"}\t\n";
        server.enqueue(new MockResponse().setBody(json));

        Map<CleverTweak, Integer> tweaks = new HashMap<>();
        tweaks.put(CleverTweak.WACKY, 0);
        tweaks.put(CleverTweak.ATTENTIVE, 100);

        CleverResponse response = cb.say("Hello, world!", "MXYxCTh2MQlBdldYSjlVWEFXUjEJMUZ2MTUzMTQyNzQxMgk2NHZIZWxsbywgd29ybGQhCTY0aUhvdyBhcmUgeW91Pwk=", tweaks).complete();
        String history = response.getHistoryScript();
        assertEquals("User: Hello, world!\nCleverbot: How are you?\nUser: Hello, world!\nCleverbot: Haha goodbye.", history);
    }

    @Test
    public void tweakEnum() {
        CleverTweak tweak = CleverTweak.ATTENTIVE;

        assertEquals(3, tweak.getId());
        assertEquals("cb_settings_tweak3", tweak.getSettingName());
    }
}
