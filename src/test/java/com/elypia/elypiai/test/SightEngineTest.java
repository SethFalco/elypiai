package com.elypia.elypiai.test;

import com.elypia.elypiai.sightengine.NudityResponse;
import com.elypia.elypiai.sightengine.SightEngine;
import com.elypia.elypiai.sightengine.SightEngineError;
import com.elypia.elypiai.sightengine.data.SightEngineErrorType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SightEngineTest {

    @Test
    public void sightEngine() {
        SightEngine sightEngine = new SightEngine("user", "secret");
        assertNotNull(sightEngine);
    }

    @Test
    public void parseNudityCheck() {
        String json = "{\"status\":\"success\",\"request\":{\"id\":\"req_2H1h0lpYaeb2cgqiw5q49\",\"timestamp\":1521750471.453,\"operations\":1},\"nudity\":{\"raw\":0.00937,\"partial\":0.00265,\"safe\":0.98798},\"media\":{\"id\":\"med_2H1hOoabmOeN6LroBUstH\",\"uri\":\"https://cdn.discordapp.com/attachments/233354622662737920/426400510757699585/unknown.png\"}}";
        JSONObject object = new JSONObject(json);
        NudityResponse nudity = new NudityResponse(null, object);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(true, nudity.isSuccess()),
            () -> assertEquals("req_2H1h0lpYaeb2cgqiw5q49", nudity.getRequestId()),
            () -> assertEquals(1, nudity.getOperations()),
            () -> assertEquals(0.00937, nudity.getRawNudityScore()),
            () -> assertEquals(0.00265, nudity.getNudityPartialScore()),
            () -> assertEquals(0.98798, nudity.getSafeScore()),
            () -> assertEquals(true, nudity.isSafe()),
            () -> assertEquals(true, nudity.isSafe(false)),
            () -> assertEquals("med_2H1hOoabmOeN6LroBUstH", nudity.getMediaId()),
            () -> assertEquals("https://cdn.discordapp.com/attachments/233354622662737920/426400510757699585/unknown.png", nudity.getMediaUrl()),
            () -> assertEquals(1521750471, nudity.getTimestamp().getEpochSecond()),
            () -> assertEquals(null, nudity.getError())
        );
    }

    @Test
    public void parseNsfwImage() {
        String json = "{\"status\":\"success\",\"request\":{\"id\":\"req_2H1SGNHN50lRbZU8IXUQE\",\"timestamp\":1521752645.3063,\"operations\":1},\"nudity\":{\"raw\":0.6209,\"partial\":0.3791,\"safe\":0.00001},\"media\":{\"id\":\"med_2H1ScuvMYGbUeUC6jChvN\",\"uri\":\"https://media.discordapp.net/attachments/247142416564486154/424404113409966100/e84eda5.jpg\"}}";
        JSONObject object = new JSONObject(json);
        NudityResponse nudity = new NudityResponse(null, object);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(true, nudity.isSuccess()),
            () -> assertEquals("req_2H1SGNHN50lRbZU8IXUQE", nudity.getRequestId()),
            () -> assertEquals(1, nudity.getOperations()),
            () -> assertEquals(0.6209, nudity.getRawNudityScore()),
            () -> assertEquals(0.3791, nudity.getNudityPartialScore()),
            () -> assertEquals(0.00001, nudity.getSafeScore()),
            () -> assertEquals(false, nudity.isSafe()),
            () -> assertEquals(false, nudity.isSafe(false)),
            () -> assertEquals("med_2H1ScuvMYGbUeUC6jChvN", nudity.getMediaId()),
            () -> assertEquals("https://media.discordapp.net/attachments/247142416564486154/424404113409966100/e84eda5.jpg", nudity.getMediaUrl()),
            () -> assertEquals(1521752645, nudity.getTimestamp().getEpochSecond()),
            () -> assertEquals(null, nudity.getError())
        );
    }

    @Test
    public void parsePartialNudity() {
        String json = "{\"status\":\"success\",\"request\":{\"id\":\"req_2H20U7b9oURKex5JQw3Nv\",\"timestamp\":1521753123.4497,\"operations\":1},\"nudity\":{\"raw\":0.23428,\"partial\":0.76505,\"partial_tag\":\"bikini\",\"safe\":0.00067},\"media\":{\"id\":\"med_2H209jFuzpFJDvVBWFD12\",\"uri\":\"https://3.bp.blogspot.com/-JK9xbSEuER4/V1RMAIhxhYI/AAAAAAAAAb8/ZrM39-kOG1E850PWqzGPttwXq_obxHpyACLcB/s1600/tumblr_nthg1n7Iz11uu2t7po1_1280.jpg\"}}";
        JSONObject object = new JSONObject(json);
        NudityResponse nudity = new NudityResponse(null, object);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(true, nudity.isSuccess()),
            () -> assertEquals("req_2H20U7b9oURKex5JQw3Nv", nudity.getRequestId()),
            () -> assertEquals(1, nudity.getOperations()),
            () -> assertEquals(0.23428, nudity.getRawNudityScore()),
            () -> assertEquals(0.76505, nudity.getNudityPartialScore()),
            () -> assertEquals(0.00067, nudity.getSafeScore()),
            () -> assertEquals(true, nudity.isSafe()),
            () -> assertEquals("bikini", nudity.getPartialTag()),
            () -> assertEquals(false, nudity.isSafe(false)),
            () -> assertEquals("med_2H209jFuzpFJDvVBWFD12", nudity.getMediaId()),
            () -> assertEquals("https://3.bp.blogspot.com/-JK9xbSEuER4/V1RMAIhxhYI/AAAAAAAAAb8/ZrM39-kOG1E850PWqzGPttwXq_obxHpyACLcB/s1600/tumblr_nthg1n7Iz11uu2t7po1_1280.jpg", nudity.getMediaUrl()),
            () -> assertEquals(1521753123, nudity.getTimestamp().getEpochSecond()),
            () -> assertEquals(null, nudity.getError())
        );
    }

    @Test
    public void parseError() {
        String json = "{\"status\":\"failure\",\"request\":{\"id\":\"req_2H1wPwIadMPOO2gwpY54f\",\"timestamp\":1521751345.2194,\"operations\":1},\"error\":{\"type\":\"media_error\",\"code\":7,\"message\":\"Could not download media. Could not resolve host name. Please make sure the media's URL is correct.\"}}";
        JSONObject object = new JSONObject(json);

        NudityResponse nudity = new NudityResponse(null, object);
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(null, nudity.getSightEngine()),
            () -> assertEquals(false, nudity.isSuccess()),
            () -> assertEquals("req_2H1wPwIadMPOO2gwpY54f", nudity.getRequestId()),
            () -> assertEquals(1, nudity.getOperations()),
            () -> assertEquals(0, nudity.getRawNudityScore()),
            () -> assertEquals(0, nudity.getNudityPartialScore()),
            () -> assertEquals(1521751345, nudity.getTimestamp().getEpochSecond()),
            () -> assertEquals(0, nudity.getNudityPartialScore())
        );

        SightEngineError error = nudity.getError();
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(null, error.getSightEngine()),
            () -> assertEquals(SightEngineErrorType.MEDIA_ERROR, error.getType()),
            () -> assertEquals(7, error.getCode()),
            () -> assertEquals("Could not download media. Could not resolve host name. Please make sure the media's URL is correct.", error.getMessage())
        );
    }

    @Test
    public void enums() {
        assertNull(SightEngineErrorType.getByName("random name lol"));
    }
}
