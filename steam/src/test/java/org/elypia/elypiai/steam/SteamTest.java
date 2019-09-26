/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.steam;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.test.TestUtils;
import org.elypia.elypiai.steam.data.PersonaState;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
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
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_jen.json")));
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
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_noone.json")));
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
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_seth.json")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("summary_seth-no-session.json")));

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
        server.enqueue(new MockResponse().setBody(TestUtils.read("summary_seth-with-session.json")));
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
        server.enqueue(new MockResponse().setBody(TestUtils.read("summary_seth-with-session.json")));
        GameSession session = steam.getUsers(76561198085657484L).completeGet().get(0).getSession();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(646570, session.getGameId()),
            () -> assertEquals("Slay the Spire", session.getGameStatus())
        );
    }

    @Test
    public void parseLibrary() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("url-to-id_seth.json")));
        server.enqueue(new MockResponse().setBody(TestUtils.read("library_seth.json")));

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
        server.enqueue(new MockResponse().setBody(TestUtils.read("library_toasty.json")));
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

    @Test
    public void testUrlRegexMatches() {
        assertAll("Is finding user correctly.",
            () -> assertEquals("Sethiii", Steam.getUsernameFromUrl("https://steamcommunity.com/id/Sethiii/")),
            () -> assertEquals("Sethiii", Steam.getUsernameFromUrl("https://steamcommunity.com/id/Sethiii")),
            () -> assertEquals("Sethiii", Steam.getUsernameFromUrl("http://steamcommunity.com/id/Sethiii/")),
            () -> assertEquals("Sethiii", Steam.getUsernameFromUrl("http://steamcommunity.com/id/Sethiii")),
            () -> assertEquals("Sethiii", Steam.getUsernameFromUrl("steamcommunity.com/id/Sethiii/")),
            () -> assertEquals("Sethiii", Steam.getUsernameFromUrl("steamcommunity.com/id/Sethiii"))
        );
    }
}
