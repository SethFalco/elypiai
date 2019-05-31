package com.elypia.elypiai.steam.test;

import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.steam.*;
import com.elypia.elypiai.steam.data.PersonaState;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SteamTest {

    private static MockWebServer server;
    private static Steam steam;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        steam = new Steam(new URL("http://localhost:" + server.getPort()), "DCA56FE963FE3D2CE23DF7DF30AAA579");
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void steamTest() {
        Steam steam = new Steam("DCA56FE963FE3D2CE23DF7DF30AAA579");

        assertNotNull(steam);
        assertEquals("DCA56FE963FE3D2CE23DF7DF30AAA579", steam.getApiKey());
    }

    @Test
    public void invalidSteam() {
        assertThrows(NullPointerException.class, () -> new Steam(null));
    }

    @Test
    public void idSearch() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_jen")));
        SteamSearch search = steam.getIdFromVanityUrl("JenTheBluePanda").completeGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(76561198077852097L, search.getId()),
            () -> assertEquals(1, search.getStatus()),
            () -> assertNull(search.getMessage()),
            () -> assertTrue(search.isSuccess())
        );
    }

    @Test
    public void failedIdSearch() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_noone")));
        SteamSearch search = steam.getIdFromVanityUrl("BLAHBLAHBLAHBLAHBLAHBLAHBLAHBLAHBLAH").completeGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(0, search.getId()),
            () -> assertEquals(42, search.getStatus()),
            () -> assertEquals("No match", search.getMessage()),
            () -> assertFalse(search.isSuccess())
        );
    }

    @Test
    public void parseSteamUser() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_seth")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("summary_seth-no-session")));

        long id = steam.getIdFromVanityUrl("Sethiii").completeGet().getId();
        SteamUser user = steam.getUsers(id).completeGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(steam, user.getSteam()),
            () -> assertEquals(76561198085657484L, user.getId()),
            () -> assertFalse(user.isPrivate()),
            () -> assertEquals("Seth", user.getUsername()),
            () -> assertEquals("Seth", user.getRealName()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de.jpg", user.getAvatarLow()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_medium.jpg", user.getAvatarMedium()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_full.jpg", user.getAvatarHigh()),
            () -> assertEquals("https://steamcommunity.com/id/Sethiii/", user.getProfileUrl()),
            () -> assertEquals("BE", user.getCountry()),
            () -> assertEquals(1558993223, user.getLastLogOff().getTime()),
            () -> assertEquals(PersonaState.ONLINE, user.getPersonaState()),
            () -> assertEquals(103582791429521408L, user.getPrimaryClan()),
            () -> assertEquals(1362733966, user.getTimeCreated().getTime()),
            () -> assertTrue(user.hasProfile()),
            () -> assertTrue(user.canComment()),
            () -> assertNull(user.getSession())
        );
    }

    @Test
    public void parseUserWithSession() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("summary_seth-with-session")));
        SteamUser user = steam.getUsers(76561198085657484L).completeGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(steam, user.getSteam()),
            () -> assertEquals(76561198085657484L, user.getId()),
            () -> assertFalse(user.isPrivate()),
            () -> assertEquals("Seth", user.getUsername()),
            () -> assertEquals("Seth", user.getRealName()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de.jpg", user.getAvatarLow()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_medium.jpg", user.getAvatarMedium()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_full.jpg", user.getAvatarHigh()),
            () -> assertEquals("https://steamcommunity.com/id/Sethiii/", user.getProfileUrl()),
            () -> assertEquals("BE", user.getCountry()),
            () -> assertEquals(1559119218, user.getLastLogOff().getTime()),
            () -> assertEquals(PersonaState.ONLINE, user.getPersonaState()),
            () -> assertEquals(103582791429521408L, user.getPrimaryClan()),
            () -> assertNull(user.getStateCode()),
            () -> assertEquals(1362733966, user.getTimeCreated().getTime()),
            () -> assertTrue(user.hasProfile()),
            () -> assertTrue(user.canComment()),
            () -> assertEquals(0, user.getCityId()),
            () -> assertNotNull(user.getSession())
        );
    }

    @Test
    public void parseGameSessionData() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("summary_seth-with-session")));
        GameSession session = steam.getUsers(76561198085657484L).completeGet().get(0).getSession();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(646570, session.getGameId()),
            () -> assertEquals("Slay the Spire", session.getGameStatus())
        );
    }

    @Test
    public void parseLibrary() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_seth")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("library_seth")));

        long id = steam.getIdFromVanityUrl("Sethiii").completeGet().getId();
        List<SteamGame> library = steam.getLibrary(id).completeGet();
        Collections.sort(library);
        SteamGame game = library.get(0);

        assertEquals(library.size(), 296);
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(646570, game.getId()),
            () -> assertEquals("Slay the Spire", game.getName()),
            () -> assertEquals("http://store.steampowered.com/app/646570", game.getUrl()),
            () -> assertEquals(13, game.getTotalPlaytime()),
            () -> assertEquals(0, game.getRecentPlaytime()),
            () -> assertEquals("http://media.steampowered.com/steamcommunity/public/images/apps/646570/33ea124ea8c03a9ce7012d34c3b348a351612fca.jpg", game.getIconUrl()),
            () -> assertEquals("http://media.steampowered.com/steamcommunity/public/images/apps/646570/6ecbf741b482a476d7d809ba3e3fea028b805b67.jpg", game.getLogoUrl()),
            () -> assertTrue(game.isStatsVisible())
        );
    }

    @Test
    public void parseLibraryWithRecentPlay() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("library_toasty")));
        List<SteamGame> library = steam.getLibrary(76561198012145255L).completeGet();
        Collections.sort(library);
        SteamGame game = library.get(0);

        assertEquals(library.size(), 269);
        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(230410, game.getId()),
            () -> assertEquals("Warframe", game.getName()),
            () -> assertEquals("http://store.steampowered.com/app/230410", game.getUrl()),
            () -> assertEquals(72, game.getTotalPlaytime()),
            () -> assertEquals(47, game.getRecentPlaytime()),
            () -> assertEquals("http://media.steampowered.com/steamcommunity/public/images/apps/230410/22064646470f4c53388ba87774c7ac10f0a91ffa.jpg", game.getIconUrl()),
            () -> assertEquals("http://media.steampowered.com/steamcommunity/public/images/apps/230410/3785f3576cdef5cf20a8b815bdf867154ccbe7d5.jpg", game.getLogoUrl()),
            () -> assertTrue(game.isStatsVisible())
        );
    }

    @Test
    public void noUserException() {
        assertThrows(IllegalArgumentException.class, () -> steam.getUsers().queue());
    }
}
