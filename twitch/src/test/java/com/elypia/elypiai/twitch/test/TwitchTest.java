package com.elypia.elypiai.twitch.test;

import com.elypia.elypiai.common.data.AuthenticationType;
import com.elypia.elypiai.twitch.StreamPaginator;
import com.elypia.elypiai.twitch.Twitch;
import com.elypia.elypiai.twitch.TwitchQuery;
import com.elypia.elypiai.twitch.data.AccountType;
import com.elypia.elypiai.twitch.data.BroadcasterType;
import com.elypia.elypiai.twitch.data.StreamType;
import com.elypia.elypiai.twitch.entity.Stream;
import com.elypia.elypiai.twitch.entity.User;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All tokens, client-ids, and client-secrets are fake.
 */
public class TwitchTest {

    private static MockWebServer server;
    private static Twitch twitch;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        server.enqueue(new MockResponse().setBody("{\"access_token\":\"dsopf1fwefwefwefwefuoox\",\"expires_in\":5306199,\"token_type\":\"bearer\"}"));

        twitch = new Twitch(
            new URL("http://localhost:" + server.getPort()),
            new URL("http://localhost:" + server.getPort()),
            "uo6dggojyb8d6soh92zknwmi5ej1q2",
            "0123456789abcdefghijABCDEFGHIJ",
            AuthenticationType.BEARER
        );
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void badInstance() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(403).setBody("{\"status\":403,\"message\":\"invalid client secret\"}"));

        assertThrows(IllegalStateException.class, () -> {
            new Twitch(
                new URL("http://localhost:" + server.getPort()),
                new URL("http://localhost:" + server.getPort()),
                "uo6dggojyb8d6soh92zknwmi5ej1q2",
                "0123456789abcdefghijABCDEFGHIJ",
                AuthenticationType.BEARER
            );
        });
    }

    @Test
    public void normalInstance() {
        assertAll("Twitch is instantiated correctly.",
            () -> assertNotNull(twitch),
            () -> assertEquals(twitch.getClientId(), "uo6dggojyb8d6soh92zknwmi5ej1q2")
        );
    }

    @Test
    public void invalidTwitch() {
        assertThrows(NullPointerException.class, () -> new Twitch(null, null));
    }

    @Test
    public void parseTwitchUser() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"data\":[{\"id\":\"89672168\",\"login\":\"jenthebluepanda\",\"display_name\":\"JenTheBluePanda\",\"type\":\"\",\"broadcaster_type\":\"affiliate\",\"description\":\"Fun is what matters! ^-^\",\"profile_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/jenthebluepanda-profile_image-5b81ff778cd3ff7b-300x300.png\",\"offline_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/jenthebluepanda-channel_offline_image-81a62b617638fbf5-1920x1080.jpeg\",\"view_count\":3256}]}"));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(89672168);

        User user = twitch.getUsers(query).complete().get(0);
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(twitch, user.getTwitch()),
            () -> assertEquals(89672168, user.getId()),
            () -> assertEquals("jenthebluepanda", user.getUsername()),
            () -> assertEquals("JenTheBluePanda", user.getDisplayName()),
            () -> assertEquals(AccountType.USER, user.getAccountType()),
            () -> assertEquals(BroadcasterType.AFFILIATE, user.getBroadcasterType()),
            () -> assertEquals("Fun is what matters! ^-^", user.getDescription()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/jenthebluepanda-profile_image-5b81ff778cd3ff7b-300x300.png", user.getAvatar()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/jenthebluepanda-channel_offline_image-81a62b617638fbf5-1920x1080.jpeg", user.getOfflineImage()),
            () -> assertEquals(3256, user.getViewCount()),
            () -> assertEquals("https://www.twitch.tv/JenTheBluePanda", user.getUrl())
        );
    }

    @Test
    public void parseMultipleTwitchUser() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"data\":[{\"id\":\"44635243\",\"login\":\"sethsutopia\",\"display_name\":\"SethsUtopia\",\"type\":\"\",\"broadcaster_type\":\"\",\"description\":\"Fun is what matters!\",\"profile_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-profile_image-3c22eac5e615c1fd-300x300.png\",\"offline_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-channel_offline_image-204d7766704160ae-1920x1080.png\",\"view_count\":618},{\"id\":\"89672168\",\"login\":\"jenthebluepanda\",\"display_name\":\"JenTheBluePanda\",\"type\":\"\",\"broadcaster_type\":\"affiliate\",\"description\":\"Fun is what matters! ^-^\",\"profile_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/jenthebluepanda-profile_image-5b81ff778cd3ff7b-300x300.png\",\"offline_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/jenthebluepanda-channel_offline_image-81a62b617638fbf5-1920x1080.jpeg\",\"view_count\":3256}]}"));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(44635243);
        query.addUsername("jenthebluepanda");

        List<User> users = twitch.getUsers(query).complete();
        User user = users.get(0);
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(twitch, user.getTwitch()),
            () -> assertEquals(44635243, user.getId()),
            () -> assertEquals("sethsutopia", user.getUsername()),
            () -> assertEquals("SethsUtopia", user.getDisplayName()),
            () -> assertEquals(AccountType.USER, user.getAccountType()),
            () -> assertEquals(BroadcasterType.NORMAL, user.getBroadcasterType()),
            () -> assertEquals("Fun is what matters!", user.getDescription()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-profile_image-3c22eac5e615c1fd-300x300.png", user.getAvatar()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/sethsutopia-channel_offline_image-204d7766704160ae-1920x1080.png", user.getOfflineImage()),
            () -> assertEquals(618, user.getViewCount()),
            () -> assertEquals("https://www.twitch.tv/SethsUtopia", user.getUrl())
        );
    }

    @Test
    public void parseTwitchStream() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"data\":[{\"id\":\"36483360\",\"login\":\"zizaran\",\"display_name\":\"Zizaran\",\"type\":\"\",\"broadcaster_type\":\"partner\",\"description\":\"Zizaran - Streaming every day, come say hi!\",\"profile_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/zizaran-profile_image-d534b9791c469a48-300x300.png\",\"offline_image_url\":\"https://static-cdn.jtvnw.net/jtv_user_pictures/zizaran-channel_offline_image-f9aa6d94a452cb3f-1920x1080.jpeg\",\"view_count\":16597806}]}"));
        server.enqueue(new MockResponse().setBody("{\"data\":[{\"id\":\"29279769792\",\"user_id\":\"36483360\",\"game_id\":\"29307\",\"community_ids\":[],\"type\":\"live\",\"title\":\"Vaal Race practice! !temple !3.3 !request !rip !leaguestart !filter !tencent !RF !merch\",\"viewer_count\":1893,\"started_at\":\"2018-06-29T11:00:13Z\",\"language\":\"en-gb\",\"thumbnail_url\":\"https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-{width}x{height}.jpg\"}],\"pagination\":{\"cursor\":\"eyJiIjpudWxsLCJhIjp7Ik9mZnNldCI6MX19\"}}"));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(36483360);

        User user = twitch.getUsers(query).complete().get(0);
        Stream stream = user.getStream().next().get(0);
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(36483360, stream.getUserId()),
            () -> assertEquals(29279769792L, stream.getId()),
            () -> assertEquals(29307, stream.getGameId()),
            () -> assertTrue(stream.getCommunityIds().isEmpty()),
            () -> assertEquals(1530270013000L, stream.getStartDate().getTime()),
            () -> assertEquals(StreamType.LIVE, stream.getType()),
            () -> assertEquals("Vaal Race practice! !temple !3.3 !request !rip !leaguestart !filter !tencent !RF !merch", stream.getTitle()),
            () -> assertEquals(1893, stream.getViewerCount()),
            () -> assertEquals("en-gb", stream.getLanguage()),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-1600x800.jpg", stream.getThumbnail(1600, 800)),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-1600x900.jpg", stream.getThumbnail(1600)),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-480x270.jpg", stream.getThumbnail())
        );
    }

    @Test
    public void parsePaginatedResults() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"data\":[{\"id\":\"29292960704\",\"user_id\":\"35096440\",\"game_id\":\"1598\",\"community_ids\":[],\"type\":\"live\",\"title\":\"117 - https://fifteenfortyfive.org/\",\"viewer_count\":29,\"started_at\":\"2018-06-30T11:14:05Z\",\"language\":\"en\",\"thumbnail_url\":\"https://static-cdn.jtvnw.net/previews-ttv/live_user_nerfripp-{width}x{height}.jpg\"},{\"id\":\"29293408880\",\"user_id\":\"68130326\",\"game_id\":\"1598\",\"community_ids\":[],\"type\":\"live\",\"title\":\"Some spyro the dragon then tomb raider\",\"viewer_count\":0,\"started_at\":\"2018-06-30T12:18:11Z\",\"language\":\"en\",\"thumbnail_url\":\"https://static-cdn.jtvnw.net/previews-ttv/live_user_nervationz-{width}x{height}.jpg\"}],\"pagination\":{\"cursor\":\"eyJiIjpudWxsLCJhIjp7Ik9mZnNldCI6Mn19\"}}"));
        server.enqueue(new MockResponse().setBody("{\"data\":[{\"id\":\"29293273408\",\"user_id\":\"234453162\",\"game_id\":\"1598\",\"community_ids\":[],\"type\":\"\",\"title\":\"assassination nation FULL MOVIE ONLINE_FREE_2018\",\"viewer_count\":0,\"started_at\":\"2018-06-30T12:00:14Z\",\"language\":\"en\",\"thumbnail_url\":\"https://static-cdn.jtvnw.net/previews-ttv/live_user_vtlmovie-{width}x{height}.jpg\"},{\"id\":\"29293106016\",\"user_id\":\"162022940\",\"game_id\":\"1598\",\"community_ids\":[\"d419798b-b16c-4030-a7ad-193b0081fba2\",\"ec629d66-f372-45cb-9685-1924e28593dd\"],\"type\":\"live\",\"title\":\"Finishing off the trilogy before reignited comes out!\",\"viewer_count\":0,\"started_at\":\"2018-06-30T11:36:15Z\",\"language\":\"en\",\"thumbnail_url\":\"https://static-cdn.jtvnw.net/previews-ttv/live_user_thefriendliestgamer-{width}x{height}.jpg\"}],\"pagination\":{\"cursor\":\"eyJiIjp7Ik9mZnNldCI6MH0sImEiOnsiT2Zmc2V0Ijo0fX0\"}}"));
        server.enqueue(new MockResponse().setBody("{\"data\":[],\"pagination\":{}}"));

        TwitchQuery query = new TwitchQuery();
        query.addGame(1598);

        List<Stream> allStreams = new ArrayList<>();
        StreamPaginator paginator = twitch.getStreams(query, 2);

        List<Stream> streams;
        while ((streams = paginator.next()) != null) {
            assertEquals(2, streams.size());
            allStreams.addAll(streams);
        }

        assertEquals(4, allStreams.size());
    }

    @Test
    public void notUserException() {
        TwitchQuery query = new TwitchQuery();
        query.addGame(1000);

        assertThrows(IllegalArgumentException.class, () -> twitch.getUsers(query));
    }

    @Test
    public void tooManyUsersException() {
        TwitchQuery query = new TwitchQuery();

        for (int i = 0; i < 101; i++)
            query.addUserId(i);

        assertThrows(IllegalArgumentException.class, () -> twitch.getUsers(query));
    }

    @Test
    public void tooManyStreams() {
        TwitchQuery query = new TwitchQuery();
        assertThrows(IllegalArgumentException.class, () -> twitch.getStreams(query, 101));
    }
}
