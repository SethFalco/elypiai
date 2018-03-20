package com.elypia.elypiai.test;

import com.elypia.elypiai.google.youtube.YouTube;
import com.elypia.elypiai.google.youtube.YouTubeItem;
import com.elypia.elypiai.google.youtube.data.YouTubeType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class YouTubeTest {

    @Test
    public void youtubeTest() {
        YouTube youtube = new YouTube("AIzaSyBSXpt4aMDRs-6qu37mazTGqdyXq1yvEFI");

        assertNotNull(youtube);
        assertEquals("AIzaSyBSXpt4aMDRs-6qu37mazTGqdyXq1yvEFI", youtube.getApiKey());
    }

    @Test
    public void invalidYouTube() {
        assertThrows(NullPointerException.class, () -> new YouTube(null));
    }

    @Test
    public void parseYouTubeItem() {
        String json = "{\"kind\":\"youtube#searchResult\",\"etag\":\"\\\"RmznBCICv9YtgWaaa_nWDIH1_GM/PI263ufezbPvfXmY9PzkA-lyaHw\\\"\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"ueupsBPNkSc\"},\"snippet\":{\"publishedAt\":\"2017-06-19T21:39:04.000Z\",\"channelId\":\"UCJ6td3C9QlPO9O_J5dF4ZzA\",\"title\":\"Uncaged Radio - 24/7 Music Live Stream ✦ Bass Music, Trap, EDM, Gaming\",\"description\":\"Subscribe to our new Monstercat: Instinct channel to become a Founding Follower! http://monster.cat/INSTINCT-2018 Thank you for tuning into Monstercat: Uncaged! * Please review our chat rules...\",\"thumbnails\":{\"default\":{\"url\":\"https://i.ytimg.com/vi/ueupsBPNkSc/default_live.jpg\",\"width\":120,\"height\":90},\"medium\":{\"url\":\"https://i.ytimg.com/vi/ueupsBPNkSc/mqdefault_live.jpg\",\"width\":320,\"height\":180},\"high\":{\"url\":\"https://i.ytimg.com/vi/ueupsBPNkSc/hqdefault_live.jpg\",\"width\":480,\"height\":360}},\"channelTitle\":\"Monstercat: Uncaged\",\"liveBroadcastContent\":\"live\"}}";
        JSONObject object = new JSONObject(json);
        YouTubeItem item = new YouTubeItem(null, object, YouTubeType.VIDEO);

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("ueupsBPNkSc", item.getId()),
            () -> assertEquals("UCJ6td3C9QlPO9O_J5dF4ZzA", item.getChannelId()),
            () -> assertEquals("Uncaged Radio - 24/7 Music Live Stream ✦ Bass Music, Trap, EDM, Gaming", item.getTitle()),
            () -> assertEquals("Subscribe to our new Monstercat: Instinct channel to become a Founding Follower! http://monster.cat/INSTINCT-2018 Thank you for tuning into Monstercat: Uncaged! * Please review our chat rules...", item.getDescription()),
            () -> assertEquals("https://i.ytimg.com/vi/ueupsBPNkSc/hqdefault_live.jpg", item.getHighThumbnail()),
            () -> assertEquals("Monstercat: Uncaged", item.getChannelName()),
            () -> assertEquals(true, item.isStream())
        );
    }
}
