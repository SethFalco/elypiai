//package com.elypia.elypiai.test;
//
//import com.elypia.elypiai.cleverbot.*;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CleverbotTest {
//
//    @Test
//    public void parseCleverResponse() {
//        String json = "{\"cs\":\"MXYxCTh2MQlBdldYRTY1SEVDRFcJMUZ2MTUyMTU3MjE5Nwk2NHZIZWxsbyB3b3JsZCEJNjRpSSdtIG5vdCB0aGUgd29ybGQuCQ==\",\"interaction_count\":\"1\",\"input\":\"Hello world!\",\"input_other\":\"\",\"input_label\":\"\",\"predicted_input\":\"\",\"accuracy\":\"\",\"output_label\":\"welcome\",\"output\":\"I'm not the world.\",\"conversation_id\":\"WXE65HECDW\",\"errorline\":\"Init:3840000,Full:623,Other:476,Row:239,Score:2681,Id:208502783,Len:18,\",\"database_version\":\"3167\",\"software_version\":\"3233\",\"time_taken\":\"125\",\"random_number\":\"501\",\"time_second\":\"37\",\"time_minute\":\"56\",\"time_hour\":\"18\",\"time_day_of_week\":\"1\",\"time_day\":\"20\",\"time_month\":\"3\",\"time_year\":\"2018\",\"reaction\":\"\",\"reaction_tone\":\"\",\"emotion\":\"\",\"emotion_tone\":\"\",\"clever_accuracy\":\"26\",\"clever_output\":\"I'm not the world.\",\"clever_match\":\"\",\"CSRES30\":\"\",\"time_elapsed\":\"0\",\"filtered_input\":\"Hello world!\",\"filtered_input_other\":\"\",\"reaction_degree\":\"\",\"emotion_degree\":\"\",\"reaction_values\":\"\",\"emotion_values\":\"\",\"callback\":\"\",\"interaction_1\":\"Hello world!\",\"interaction_1_other\":\"I'm not the world.\",\"interaction_2\":\"\",\"interaction_3\":\"\",\"interaction_4\":\"\",\"interaction_5\":\"\",\"interaction_6\":\"\",\"interaction_7\":\"\",\"interaction_8\":\"\",\"interaction_9\":\"\",\"interaction_10\":\"\",\"interaction_11\":\"\",\"interaction_12\":\"\",\"interaction_13\":\"\",\"interaction_14\":\"\",\"interaction_15\":\"\",\"interaction_16\":\"\",\"interaction_17\":\"\",\"interaction_18\":\"\",\"interaction_19\":\"\",\"interaction_20\":\"\",\"interaction_21\":\"\",\"interaction_22\":\"\",\"interaction_23\":\"\",\"interaction_24\":\"\",\"interaction_25\":\"\",\"interaction_26\":\"\",\"interaction_27\":\"\",\"interaction_28\":\"\",\"interaction_29\":\"\",\"interaction_30\":\"\",\"interaction_31\":\"\",\"interaction_32\":\"\",\"interaction_33\":\"\",\"interaction_34\":\"\",\"interaction_35\":\"\",\"interaction_36\":\"\",\"interaction_37\":\"\",\"interaction_38\":\"\",\"interaction_39\":\"\",\"interaction_40\":\"\",\"interaction_41\":\"\",\"interaction_42\":\"\",\"interaction_43\":\"\",\"interaction_44\":\"\",\"interaction_45\":\"\",\"interaction_46\":\"\",\"interaction_47\":\"\",\"interaction_48\":\"\",\"interaction_49\":\"\",\"interaction_50\":\"\"}";
//        JSONObject object = new JSONObject(json);
//        CleverResponse response = new CleverResponse(null, object);
//
//        assertAll("Ensure Parsing Result Data Correctly",
//            () -> assertEquals("MXYxCTh2MQlBdldYRTY1SEVDRFcJMUZ2MTUyMTU3MjE5Nwk2NHZIZWxsbyB3b3JsZCEJNjRpSSdtIG5vdCB0aGUgd29ybGQuCQ==", response.getCS()),
//            () -> assertEquals(1, response.getInteractionCount()),
//            () -> assertEquals("Hello world!", response.getInput()),
//            () -> assertEquals("I'm not the world.", response.getOutput()),
//            () -> assertEquals("WXE65HECDW", response.getConversationId()),
//            () -> assertEquals(0, response.getLifetime()),
//            () -> assertEquals(1, response.getHistory().size()),
//            () -> assertEquals("User: Hello world!\nCleverbot: I'm not the world.", response.getHistoryScript()),
//            () -> assertEquals("Init:3840000,Full:623,Other:476,Row:239,Score:2681,Id:208502783,Len:18,", response.getErrorLine()),
//            () -> assertEquals(125, response.getTimeTaken())
//        );
//    }
//
//    @Test
//    public void parseScriptResponse() {
//        String json = "{\"cs\":\"MXYxCTh2MQlBdldYRTY1SEVDRFcJMUZ2MTUyMTU3MjE5Nwk2NHZIZWxsbyB3b3JsZCEJNjRpSSdtIG5vdCB0aGUgd29ybGQuCQ==\",\"interaction_count\":\"1\",\"input\":\"Hello world!\",\"input_other\":\"\",\"input_label\":\"\",\"predicted_input\":\"\",\"accuracy\":\"\",\"output_label\":\"welcome\",\"output\":\"I'm not the world.\",\"conversation_id\":\"WXE65HECDW\",\"errorline\":\"Init:3840000,Full:623,Other:476,Row:239,Score:2681,Id:208502783,Len:18,\",\"database_version\":\"3167\",\"software_version\":\"3233\",\"time_taken\":\"125\",\"random_number\":\"501\",\"time_second\":\"37\",\"time_minute\":\"56\",\"time_hour\":\"18\",\"time_day_of_week\":\"1\",\"time_day\":\"20\",\"time_month\":\"3\",\"time_year\":\"2018\",\"reaction\":\"\",\"reaction_tone\":\"\",\"emotion\":\"\",\"emotion_tone\":\"\",\"clever_accuracy\":\"26\",\"clever_output\":\"I'm not the world.\",\"clever_match\":\"\",\"CSRES30\":\"\",\"time_elapsed\":\"0\",\"filtered_input\":\"Hello world!\",\"filtered_input_other\":\"\",\"reaction_degree\":\"\",\"emotion_degree\":\"\",\"reaction_values\":\"\",\"emotion_values\":\"\",\"callback\":\"\",\"interaction_1\":\"Hello world!\",\"interaction_1_other\":\"I'm not the world.\",\"interaction_2\":\"\",\"interaction_3\":\"\",\"interaction_4\":\"\",\"interaction_5\":\"\",\"interaction_6\":\"\",\"interaction_7\":\"\",\"interaction_8\":\"\",\"interaction_9\":\"\",\"interaction_10\":\"\",\"interaction_11\":\"\",\"interaction_12\":\"\",\"interaction_13\":\"\",\"interaction_14\":\"\",\"interaction_15\":\"\",\"interaction_16\":\"\",\"interaction_17\":\"\",\"interaction_18\":\"\",\"interaction_19\":\"\",\"interaction_20\":\"\",\"interaction_21\":\"\",\"interaction_22\":\"\",\"interaction_23\":\"\",\"interaction_24\":\"\",\"interaction_25\":\"\",\"interaction_26\":\"\",\"interaction_27\":\"\",\"interaction_28\":\"\",\"interaction_29\":\"\",\"interaction_30\":\"\",\"interaction_31\":\"\",\"interaction_32\":\"\",\"interaction_33\":\"\",\"interaction_34\":\"\",\"interaction_35\":\"\",\"interaction_36\":\"\",\"interaction_37\":\"\",\"interaction_38\":\"\",\"interaction_39\":\"\",\"interaction_40\":\"\",\"interaction_41\":\"\",\"interaction_42\":\"\",\"interaction_43\":\"\",\"interaction_44\":\"\",\"interaction_45\":\"\",\"interaction_46\":\"\",\"interaction_47\":\"\",\"interaction_48\":\"\",\"interaction_49\":\"\",\"interaction_50\":\"\"}";
//        JSONObject object = new JSONObject(json);
//        CleverResponse response = new CleverResponse(null, object);
//        ScriptResponse script = response.getScriptResponse();
//
//        assertAll("Ensure Parsing Result Data Correctly",
//            () -> assertNull(script.getAccuracy()),
//            () -> assertEquals(26, script.getCleverAccuracy()),
//            () -> assertEquals("I'm not the world.", script.getCleverOutput()),
//            () -> assertNull(script.getCleverMatch())
//        );
//    }
//}
