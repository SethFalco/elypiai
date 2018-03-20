package com.elypia.elypiai.test;

import com.elypia.elypiai.twitch.TwitchStream;
import com.elypia.elypiai.twitch.TwitchUser;
import com.elypia.elypiai.twitch.data.AccountType;
import com.elypia.elypiai.twitch.data.BroadcasterType;
import com.elypia.elypiai.twitch.data.StreamType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwitchTest {

    @Test
    public void parseTwitchUser() {
        String json = "{\"id\":\"44635243\",\"login\":\"sethsutopia\",\"display_name\":\"SethsUtopia\",\"type\":\"\",\"broadcaster_type\":\"\",\"description\":\"\",\"profile_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-profile_image-3c22eac5e615c1fd-300x300.png\",\"offline_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-channel_offline_image-204d7766704160ae-1920x1080.png\",\"view_count\":615}";
        JSONObject object = new JSONObject(json);
        TwitchUser user = new TwitchUser(null, object);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(44635243, user.getId()),
            () -> assertEquals("sethsutopia", user.getUsername()),
            () -> assertEquals("SethsUtopia", user.getDisplayName()),
            () -> assertEquals(AccountType.USER, user.getAccountType()),
            () -> assertEquals(BroadcasterType.NORMAL, user.getBroadcasterType()),
            () -> assertEquals(null, user.getDescription()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-profile_image-3c22eac5e615c1fd-300x300.png", user.getAvatar()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-channel_offline_image-204d7766704160ae-1920x1080.png", user.getOfflineImage()),
            () -> assertEquals(615, user.getViewCount()),
            () -> assertEquals("https://www.twitch.tv/sethsutopia", user.getUrl())
        );
    }

    @Test
    public void parseTwitchStream() {
        String userJson = "{\"id\":\"44635243\",\"login\":\"sethsutopia\",\"display_name\":\"SethsUtopia\",\"type\":\"\",\"broadcaster_type\":\"\",\"description\":\"\",\"profile_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-profile_image-3c22eac5e615c1fd-300x300.png\",\"offline_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-channel_offline_image-204d7766704160ae-1920x1080.png\",\"view_count\":615}";
        JSONObject userObject = new JSONObject(userJson);
        TwitchUser user = new TwitchUser(null, userObject);

        String json = "{\"id\":\"27996158976\",\"user_id\":\"56331611\",\"game_id\":\"369141\",\"community_ids\":[\"6076341d-d7e4-4b6e-94d4-a4b43472dfb4\",\"b634eb95-4547-4a82-bc97-7884edb291d3\",\"fd0eab99-832a-4d7e-8cc0-04d73deb2e54\"],\"type\":\"live\",\"title\":\"Bajo's back! Sneaky rat time.\",\"viewer_count\":2913,\"started_at\":\"2018-03-19T22:27:22Z\",\"language\":\"en\",\"thumbnail_url\":\"https://static-cdn.jtvnw.net/previews-ttv/live_user_bajostream-{width}x{height}.jpg\"}";
        JSONObject object = new JSONObject(json);
        TwitchStream stream = new TwitchStream(user, object);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(27996158976L, stream.getId()),
            () -> assertEquals(369141, stream.getGameId()),
            () -> assertEquals(StreamType.LIVE, stream.getType()),
            () -> assertEquals("Bajo's back! Sneaky rat time.", stream.getTitle()),
            () -> assertEquals(2913, stream.getViewerCount()),
            () -> assertEquals("en", stream.getLanguage()),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_bajostream-1600x900.jpg", stream.getThumbnail())
        );
    }
}
