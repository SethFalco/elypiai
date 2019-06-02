package com.elypia.elypiai.test.twitch;

import com.elypia.elypiai.common.core.data.AuthenticationType;
import com.elypia.elypiai.common.test.TestUtils;
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
        server.enqueue(new MockResponse().setBody(TestUtils.read("token_bearer.json")));
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
        server.enqueue(new MockResponse()
            .setBody(TestUtils.read("token_invalid.json"))
            .setResponseCode(403)
        );

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
        server.enqueue(new MockResponse().setBody(TestUtils.read("users_jen.json")));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(89672168);

        User user = twitch.getUsers(query).completeGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(twitch, user.getTwitch()),
            () -> assertEquals(89672168, user.getId()),
            () -> assertEquals("jenthebluepanda", user.getUsername()),
            () -> assertEquals("JenTheBluePanda", user.getDisplayName()),
            () -> assertEquals(AccountType.USER, user.getAccountType()),
            () -> assertEquals(BroadcasterType.AFFILIATE, user.getBroadcasterType()),
            () -> assertEquals("Fun is what matters! ^-^", user.getDescription()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/78145658-f3d7-4052-8e82-42bafc7313f6-profile_image-300x300.png", user.getAvatar()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/af3359e6-3eb0-479f-863d-444643fcda6c-channel_offline_image-1920x1080.png", user.getOfflineImage()),
            () -> assertEquals(3342, user.getViewCount()),
            () -> assertEquals("https://www.twitch.tv/JenTheBluePanda", user.getUrl())
        );
    }

    @Test
    public void parseMultipleTwitchUser() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("users_jen_and_seth.json")));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(44635243);
        query.addUsername("jenthebluepanda");

        List<User> users = twitch.getUsers(query).completeGet();
        User user = users.get(1);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(twitch, user.getTwitch()),
            () -> assertEquals(44635243, user.getId()),
            () -> assertEquals("sethiiiii", user.getUsername()),
            () -> assertEquals("Sethiiiii", user.getDisplayName()),
            () -> assertEquals(AccountType.USER, user.getAccountType()),
            () -> assertEquals(BroadcasterType.NORMAL, user.getBroadcasterType()),
            () -> assertEquals("Testing stuffi!!!!!!!", user.getDescription()),
            () -> assertEquals("https://static-cdn.jtvnw.net/jtv_user_pictures/a91170f3-b6b8-458b-bf54-7bee1647c100-profile_image-300x300.png", user.getAvatar()),
            () -> assertNull(user.getOfflineImage()),
            () -> assertEquals(635, user.getViewCount()),
            () -> assertEquals("https://www.twitch.tv/Sethiiiii", user.getUrl())
        );
    }

    @Test
    public void parseTwitchStream() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("users_zizaran.json")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("streams_zizaran.json")));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(36483360);

        User user = twitch.getUsers(query).completeGet().get(0);
        Stream stream = user.getStream().next().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(36483360, stream.getUserId()),
            () -> assertEquals(34289935168L, stream.getId()),
            () -> assertEquals(29307, stream.getGameId()),
            () -> assertTrue(stream.getCommunityIds().isEmpty()),
            () -> assertEquals(1558946993000L, stream.getStartDate().getTime()),
            () -> assertEquals(StreamType.LIVE, stream.getType()),
            () -> assertEquals("Ziz - <METHOD> Pushing Hierophant Ladder! !Legion !Overview !LO !Ama", stream.getTitle()),
            () -> assertEquals(1897, stream.getViewerCount()),
            () -> assertEquals("en", stream.getLanguage()),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-1600x800.jpg", stream.getThumbnail(1600, 800)),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-1600x900.jpg", stream.getThumbnail(1600)),
            () -> assertEquals("https://static-cdn.jtvnw.net/previews-ttv/live_user_zizaran-480x270.jpg", stream.getThumbnail())
        );
    }

    @Test
    public void parsePaginatedResults() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("streams_paged_1.json")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("streams_paged_2.json")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("streams_pages_3.json")));

        TwitchQuery query = new TwitchQuery();
        query.addUserId(19571641, 26261471);
        List<Stream> allStreams = new ArrayList<>();
        StreamPaginator paginator = twitch.getStreams(query, 1);

        List<Stream> streams;

        while ((streams = paginator.next()) != null) {
            assertEquals(1, streams.size());
            allStreams.addAll(streams);
        }

        assertEquals(2, allStreams.size());
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
        assertThrows(IllegalArgumentException.class, () ->
            twitch.getStreams(new TwitchQuery(), 101)
        );
    }
}
