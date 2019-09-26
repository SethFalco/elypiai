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

package org.elypia.elypiai.poe;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.test.TestUtils;
import org.elypia.elypiai.poe.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Data in this has been intentionally shortended in order to
 * improve IDE performance.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public class PathOfExileTest {

    private static MockWebServer server;
    private static PathOfExile poe;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        poe = new PathOfExile(new URL("http://localhost:" + server.getPort()));
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void pathOfExile() {
        assertDoesNotThrow((Executable)PathOfExile::new);
    }

    @Test
    public void parsePartialStashTabs() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("public-stash-tabs_single.json")));
        StashTabs stashtabs = poe.getStashTabs().completeGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("2947-5165-4180-5175-1708", stashtabs.getCursor()),
            () -> assertFalse(stashtabs.getStashes().isEmpty())
        );
    }

    @Test
    public void parseSingleStash() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("public-stash-tabs_single.json")));
        Stash stash = poe.getStashTabs(null).completeGet().getStashes().get(0);

        assertAll("Ensure Parsing Single Stash Correctly",
            () -> assertEquals("a9a42a5dbda657f71b077ecd0692acce8d1d29c7dff3437e5ed8708f6cb8838f", stash.getId()),
            () -> assertFalse(stash.isPublic()),
            () -> assertNull(stash.getName()),
            () -> assertNull(stash.getLastCharacterName()),
            () -> assertEquals(StashType.PREMIUM_STASH, stash.getStashType()),
            () -> assertEquals("Standard", stash.getLeague()),
            () -> assertTrue(stash.getItems().isEmpty())
        );
    }

    @Test
    public void parseSingleStashNonNull() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("public-stash-tabs_single.json")));
        Stash stash = poe.getStashTabs(null).completeGet().getStashes().get(3);

        assertAll("Ensure Parsing Single Stash Correctly | With Info",
            () -> assertEquals("6e744b0f76179835e1f681ce81c513ea190cb021b34eaacafe4c3d4f6990395f", stash.getId()),
            () -> assertTrue(stash.isPublic()),
            () -> assertEquals("5a4oK", stash.getAccountName()),
            () -> assertEquals("temniypoputchik_letstry", stash.getLastCharacterName()),
            () -> assertEquals("What i need", stash.getName()),
            () -> assertEquals(StashType.PREMIUM_STASH, stash.getStashType()),
            () -> assertEquals("Hardcore", stash.getLeague()),
            () -> assertFalse(stash.getItems().isEmpty())
        );
    }

    @Test
    public void parseSingleStashItem() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("public-stash-tabs_single.json")));
        StashItem item = poe.getStashTabs().completeGet().getStashes().get(3).getItems().get(0);

        assertAll("Ensure Parsing StashItem Correctly",
            () -> assertFalse(item.isVerified()),
            () -> assertEquals(2, item.getWidth()),
            () -> assertEquals(4, item.getHeight()),
            () -> assertEquals(71, item.getLevel()),
            () -> assertEquals("http://web.poecdn.com/image/Art/2DItems/Weapons/TwoHandWeapons/Bows/SarkhamsReach.png?scale=1&w=2&h=4&v=f333c2e4005ee20a84270731baa5fa6a", item.getIcon()),
            () -> assertEquals("Hardcore", item.getLeague()),
            () -> assertEquals("176b5e6f7af0a5bb4b48d7fdafa47501a179f4ea095815a58c82c4b5244b3cdb", item.getId()),
            () -> assertEquals("Roth's Reach", item.getName()),
            () -> assertEquals("Recurve Bow", item.getTypeLine()),
            () -> assertTrue(item.isIdentified()),
            () -> assertEquals("~price 10 exa", item.getNote()),
            () -> assertEquals(3, item.getFrameType()),
            () -> assertEquals(10, item.getXPos()),
            () -> assertEquals(0, item.getYPos()),
            () -> assertEquals("Stash1", item.getInventoryId())
        );
    }

    @Test
    public void parseMultipleLeagues() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("leagues_default.json")));
        List<League> leagues = poe.getLeagues().completeGet();
        assertFalse(leagues.isEmpty());
    }

    @Test
    public void parseSingleLeague() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("leagues_default.json")));
        League league = poe.getLeagues().completeGet().get(0);

        assertAll("Ensure Single League is Parsed Correctly",
            () -> assertEquals("Standard", league.getId()),
            () -> assertEquals(Realm.PC, league.getRealm()),
            () -> assertEquals("#LeagueStandard", league.getDescription()),
            () -> assertNull(league.getRegisterAt()),
            () -> assertEquals("http://pathofexile.com/forum/view-thread/71278", league.getUrl()),
            () -> assertEquals(1358974800000L, league.getStartDate().getTime()),
            () -> assertNull(league.getEndDate()),
            () -> assertTrue(league.isDelveEvent()),
            () -> assertTrue(league.getRules().isEmpty())
        );
    }

    @Test
    public void parseSingleLeagueWithEndDate() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("leagues_default.json")));
        League league = poe.getLeagues().completeGet().get(4);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("Synthesis", league.getId()),
            () -> assertEquals(Realm.PC, league.getRealm()),
            () -> assertEquals("#LeagueStandardSynthesis", league.getDescription()),
            () -> assertEquals(1552066200000L, league.getRegisterAt().getTime()),
            () -> assertEquals("http://pathofexile.com/forum/view-thread/2452427", league.getUrl()),
            () -> assertEquals(1552075200000L, league.getStartDate().getTime()),
            () -> assertEquals(1559599200000L, league.getEndDate().getTime()),
            () -> assertTrue(league.getRules().isEmpty())
        );
    }

    @Test
    public void parseRulesFromLeague() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("leagues_default.json")));
        LeagueRule rule = poe.getLeagues().completeGet().get(1).getRules().get(0);

        assertAll("League Rules",
            () -> assertEquals("Hardcore", rule.getId()),
            () -> assertEquals("Hardcore", rule.getName()),
            () -> assertEquals("A character killed in Hardcore is moved to its parent league.", rule.getDescription())
        );
    }

    @Test
    public void getSingleRule() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("rule_hardcore.json")));
        LeagueRule rule = poe.getRule("Hardcore").completeGet();

        assertAll("League Rules",
            () -> assertEquals("Hardcore", rule.getId()),
            () -> assertEquals("Hardcore", rule.getName()),
            () -> assertEquals("A character killed in Hardcore is moved to its parent league.", rule.getDescription())
        );
    }

    @Test
    public void getMultipleRules() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("rules.json")));
        List<LeagueRule> rule = poe.getRules().completeGet();

        assertFalse(rule.isEmpty());
    }

    @Test
    public void parseLadderEntry() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard.json")));
        LadderEntry entry = poe.getLeagueLadder("Standard").completeGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(1, entry.getRank()),
            () -> assertFalse(entry.isDead()),
            () -> assertFalse(entry.isOnline()),
            () -> assertNotNull(entry.getExile()),
            () -> assertNotNull(entry.getAccount())
        );
    }

    @Test
    public void parseLadderEntryWithChallenges() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard.json")));
        LadderEntry entry = poe.getLeagueLadder("Standard").completeGet().get(3);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(4, entry.getRank()),
            () -> assertFalse(entry.isDead()),
            () -> assertFalse(entry.isOnline()),
            () -> assertNotNull(entry.getExile()),
            () -> assertNotNull(entry.getAccount())
        );
    }

    @Test
    public void parseExileFromLadderEntryWithChallenges() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard.json")));
        Exile exile = poe.getLeagueLadder("Standard").completeGet().get(3).getExile();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("VaalMulliSpark", exile.getName()),
            () -> assertEquals(100, exile.getLevel()),
            () -> assertNull(exile.getAscendancyClass()),
            () -> assertEquals(AscendancyType.SCION, exile.getAscendancy()),
            () -> assertEquals("7580516c6997a4401ea0363a0cca9e83ae50e475fe437fedc51848f322c98b6a", exile.getId()),
            () -> assertEquals(4250334444L, exile.getExperience())
        );
    }

    @Test
    public void parseAccountFromLadderEntryWithChallenges() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard.json")));
        Account account = poe.getLeagueLadder("Standard").completeGet().get(3).getAccount();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("spinzter", account.getName()),
            () -> assertEquals(Realm.PC, account.getRealm()),
            () -> assertEquals(17, account.getChallenges()),
            () -> assertEquals("gourangaa", account.getTwitch()),
            () -> assertNull(account.getGuild())
        );
    }

    @Test
    public void parseLadderEntryUserWithSubClass() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard.json")));
        Exile exile = poe.getLeagueLadder("Standard").completeGet().get(6).getExile();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("xdukanx", exile.getName()),
            () -> assertEquals(100, exile.getLevel()),
            () -> assertEquals(AscendancyType.MARAUDER, exile.getAscendancy()),
            () -> assertEquals(AscendancyClass.CHIEFTAIN, exile.getAscendancyClass()),
            () -> assertEquals("3fd75b1d62ed688b6fe6facf824a2bc95cf3546eeeb961ab45e3d32f8cb2bba8", exile.getId()),
            () -> assertEquals(4250334444L, exile.getExperience())
        );
    }

    @Test
    public void parseLadderEntryWithGuild() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard-lab-norm-pc.json")));
        Account account = poe.getLeagueLadder("Standard", Realm.PC, 200, 0, LadderType.LABYRINTH).completeGet().get(0).getAccount();

        assertNotNull(account);
        assertNotNull(account.getGuild());
    }

    @Test
    public void parseGuildOfLadderEntryWithGuild() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("ladders_standard-lab-norm-pc.json")));
        Guild guild = poe.getLeagueLadder("Standard", Realm.PC, 200, 0, LadderType.LABYRINTH).completeGet().get(0).getAccount().getGuild();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(7617, guild.getId()),
            () -> assertEquals("DSO", guild.getName()),
            () -> assertEquals("DSO", guild.getTag()),
            () -> assertEquals(1382576540000L, guild.getCreationDate().getTime()),
            () -> assertEquals("Gratz to Dragon on Mirror drop!", guild.getStatus())
        );
    }

    @Test
    public void parsePvpMatches() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("pvp-matches_eupvpseason1.json")));
        List<PvpMatch> matches = poe.getPvpMatches("EUPvPSeason1").completeGet();

        assertNotNull(matches);
        assertFalse(matches.isEmpty());
    }

    @Test
    public void parsePvpMatch() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("pvp-matches_eupvpseason1.json")));
        PvpMatch match = poe.getPvpMatches("EUPvPSeason1").completeGet().get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("EU01-73-STD Swiss", match.getId()),
            () -> assertEquals(Realm.PC, match.getRealm()),
            () -> assertEquals(1420988400000L, match.getStartDate().getTime()),
            () -> assertEquals(1420993440000L, match.getEndDate().getTime()),
            () -> assertEquals("http://pathofexile.com/forum/view-thread/1170447", match.getUrl()),
            () -> assertEquals("Best of Seven Low Level Dueling", match.getDescription()),
            () -> assertFalse(match.isGlickoRatings()),
            () -> assertTrue(match.isPvp()),
            () -> assertEquals(MatchStyle.SWISS, match.getStyle()),
            () -> assertEquals(1420986600000L, match.getRegisterDate().getTime())
        );
    }
}
