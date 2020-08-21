/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

import org.elypia.elypiai.steam.data.*;
import org.elypia.webservertestbed.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The API keys here are fake. c;
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class SteamTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static Steam steam;

    @BeforeEach
    public void beforeEach() {
        steam = new Steam("{STEAM_API_KEY}", serverExtension.getRequestUrl());
    }

    @Test
    public void steamTest() {
        Steam steam = new Steam("{STEAM_API_KEY}");

        assertNotNull(steam);
        assertEquals("{STEAM_API_KEY}", steam.getApiKey());
    }

    @Test
    public void invalidSteam() {
        assertThrows(NullPointerException.class, () -> new Steam(null));
    }

    @WebServerTest("url-to-id_jen.json")
    public void idSearch() {
        SteamSearch search = steam.getIdFromVanityUrl("JenTheBluePanda").blockingGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(76561198077852097L, search.getId()),
            () -> assertEquals(1, search.getStatus()),
            () -> assertNull(search.getMessage()),
            () -> assertTrue(search.isSuccess())
        );
    }

    @WebServerTest("url-to-id_noone.json")
    public void failedIdSearch() {
        SteamSearch search = steam.getIdFromVanityUrl("BLAHBLAHBLAHBLAHBLAHBLAHBLAHBLAHBLAH").blockingGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(0, search.getId()),
            () -> assertEquals(42, search.getStatus()),
            () -> assertEquals("No match", search.getMessage()),
            () -> assertFalse(search.isSuccess())
        );
    }

    @WebServerTest({"url-to-id_seth.json", "summary_seth-no-session.json"})
    public void parseSteamUser() {
        long id = steam.getIdFromVanityUrl("Sethiii").blockingGet().getId();
        SteamUser user = steam.getUsers(id).blockingGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(steam, user.getSteam()),
            () -> assertEquals(76561198085657484L, user.getId()),
            () -> assertEquals(CommunityVisibilityState.PUBLIC, user.getCommunityVisibilityState()),
            () -> assertEquals("Seth", user.getUsername()),
            () -> assertEquals("Seth", user.getRealName()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de.jpg", user.getAvatarLow()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_medium.jpg", user.getAvatarMedium()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_full.jpg", user.getAvatarHigh()),
            () -> assertEquals("https://steamcommunity.com/id/Sethiii/", user.getProfileUrl()),
            () -> assertEquals("BE", user.getCountry()),
            () -> assertEquals(1558993223000L, user.getLastLogOff().toEpochMilli()),
            () -> assertEquals(PersonaState.ONLINE, user.getPersonaState()),
            () -> assertEquals(103582791429521408L, user.getPrimaryClan()),
            () -> assertEquals(1362733966000L, user.getTimeCreated().toEpochMilli()),
            () -> assertTrue(user.hasProfile()),
            () -> assertTrue(user.canComment()),
            () -> assertNull(user.getCurrentlyPlaying())
        );
    }

    @WebServerTest("summary_seth-with-session.json")
    public void parseUserWithSession() {
        SteamUser user = steam.getUsers(76561198085657484L).blockingGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(steam, user.getSteam()),
            () -> assertEquals(76561198085657484L, user.getId()),
            () -> assertEquals(CommunityVisibilityState.PUBLIC, user.getCommunityVisibilityState()),
            () -> assertEquals("Seth", user.getUsername()),
            () -> assertEquals("Seth", user.getRealName()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de.jpg", user.getAvatarLow()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_medium.jpg", user.getAvatarMedium()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_full.jpg", user.getAvatarHigh()),
            () -> assertEquals("https://steamcommunity.com/id/Sethiii/", user.getProfileUrl()),
            () -> assertEquals("BE", user.getCountry()),
            () -> assertEquals(1559119218000L, user.getLastLogOff().toEpochMilli()),
            () -> assertEquals(PersonaState.ONLINE, user.getPersonaState()),
            () -> assertEquals(103582791429521408L, user.getPrimaryClan()),
            () -> assertNull(user.getStateCode()),
            () -> assertEquals(1362733966000L, user.getTimeCreated().toEpochMilli()),
            () -> assertTrue(user.hasProfile()),
            () -> assertTrue(user.canComment()),
            () -> assertEquals(0, user.getCityId()),
            () -> assertNotNull(user.getCurrentlyPlaying())
        );
    }

    @WebServerTest("summary_seth-with-session.json")
    public void parseGameSessionData() {
        GameSession session = steam.getUsers(76561198085657484L).blockingGet().get(0).getCurrentlyPlaying();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(646570, session.getGameId()),
            () -> assertEquals("Slay the Spire", session.getGameStatus())
        );
    }

    @WebServerTest({"url-to-id_seth.json", "library_seth.json"})
    public void parseLibrary() {
        long id = steam.getIdFromVanityUrl("Sethiii").blockingGet().getId();
        List<SteamGame> library = steam.getLibrary(id).blockingGet();
        Collections.sort(library);
        SteamGame game = library.get(0);

        assertEquals(296, library.size());
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

    @WebServerTest("library_toasty.json")
    public void parseLibraryWithRecentPlay() {
        List<SteamGame> library = steam.getLibrary(76561198012145255L).blockingGet();
        Collections.sort(library);
        SteamGame game = library.get(0);

        assertEquals(269, library.size());
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
        assertThrows(IllegalArgumentException.class, () -> steam.getUsers());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "https://steamcommunity.com/id/Sethiii/",
        "https://steamcommunity.com/id/Sethiii",
        "http://steamcommunity.com/id/Sethiii/",
        "http://steamcommunity.com/id/Sethiii",
        "steamcommunity.com/id/Sethiii/",
        "steamcommunity.com/id/Sethiii"
    })
    public void testUrlRegexMatches(final String vanityUrl) {
        assertEquals("Sethiii", Steam.getUsernameFromUrl(vanityUrl));
    }
}
