package com.elypia.elypiai.osu;

import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.osu.data.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OsuTest {

    private static MockWebServer server;
    private static Osu osu;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        osu = new Osu(new URL("http://localhost:" + server.getPort()), "api key");
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void testOsu() {
        Osu osu = new Osu("api key");
        assertNotNull(osu);
        assertEquals("api key", osu.getApiKey());
    }

    @Test
    public void testOsuPlayer() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("player_nathan-on-osu")));
        Player user = osu.getPlayer("nathan on osu").completeGet();

        assertAll("Check values of osu! player.",
            () -> assertEquals(124493, user.getId()),
            () -> assertEquals("nathan on osu", user.getUsername()),
            () -> assertEquals(10547750, user.getCount300()),
            () -> assertEquals(407732, user.getCount100()),
            () -> assertEquals(35571, user.getCount50()),
            () -> assertEquals(25860, user.getPlayCount()),
            () -> assertEquals(40101175192L, user.getRankedScore()),
            () -> assertEquals(225554433086L, user.getTotalScore()),
            () -> assertEquals(10, user.getRank()),
            () -> assertEquals(101.986, user.getLevel()),
            () -> assertEquals(14692.3, user.getPp()),
            () -> assertEquals(98.81187438964844, user.getAccuracy()),
            () -> assertEquals(22, user.getCountSS()),
            () -> assertEquals(87, user.getCountSSH()),
            () -> assertEquals(99, user.getCountS()),
            () -> assertEquals(625, user.getCountSH()),
            () -> assertEquals(563, user.getCountA()),
            () -> assertEquals("KR", user.getCountry()),
            () -> assertEquals(2, user.getCountryRank()),
            () -> assertEquals("https://osu.ppy.sh/u/124493", user.getProfileUrl()),
            () -> assertFalse(user.getEvents().isEmpty())
        );
    }

    @Test
    public void testOsuPlayerEvent() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("player_nathan-on-osu")));
        OsuEvent event = osu.getPlayer("nathan on osu").completeGet().getEvents().get(0);

        assertAll("Check values of osu! player first event.",
            () -> assertEquals("nathan on osu has lost first place on xi - Blue Zenith [FOUR DIMENSIONS] (osu!)", event.getMessage()),
            () -> assertEquals(658127, event.getBeatmapId()),
            () -> assertEquals(292301, event.getBeatmapSetId()),
            () -> assertEquals(1559304704000L, event.getDate().getTime()),
            () -> assertEquals(2, event.getEpicFactor())
        );
    }

    @Test
    public void testOsuBeapMap() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("beatmap_675314")));
        BeatMap map = osu.getBeatMaps(675314).completeGet().get(0);

        assertAll("Check values of osu! Beatmap.",
            () -> assertEquals(675314, map.getSetId()),
            () -> assertEquals(1428633, map.getId()),
            () -> assertEquals(MapStatus.RANKED, map.getStatus()),
            () -> assertEquals(88, map.getTotalLength()),
            () -> assertEquals(87, map.getHitLength()),
            () -> assertEquals("Insane", map.getVersion()),
            () -> assertEquals("c7d039de44853caa2ee7a554a22d61bc", map.getFileMd5()),
            () -> assertEquals(OsuMode.OSU, map.getMode()),
            ()-> assertEquals(1506777413000L, map.getSubmissionDate().getTime()),
            () -> assertEquals(1521754807000L, map.getApprovedDate().getTime()),
            () -> assertEquals(1520937899000L, map.getLastUpdate().getTime()),
            () -> assertEquals("TRUE", map.getArtist()),
            () -> assertEquals("BUTTERFLY EFFECTOR (TV Size)", map.getTitle()),
            () -> assertEquals("kunka", map.getCreator()),
            () -> assertEquals(1741295, map.getCreatorId()),
            () -> assertEquals(141, map.getBPM()),
            () -> assertEquals("ひなろじ ～from Luck & Logic～", map.getSource()),
            () -> assertEquals("opening hinaroji hinalogic from luck & logic kunkakunkakunkakunka", map.getTags()),
            () -> assertEquals(MapGenre.ANIME, map.getGenre()),
            () -> assertEquals(MapLanguage.JAPANESE, map.getLanguage()),
            () -> assertEquals(53, map.getFavouriteCount()),
            () -> assertEquals(43632, map.getPlayCount()),
            () -> assertEquals(3987, map.getPassCount()),
            () -> assertEquals(546, map.getMaxCombo()),
            () -> assertNotNull(map.getDifficulty())
        );
    }

    @Test
    public void testOsuMapDifficulty() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("beatmap_675314")));
        MapDifficulty diff = osu.getBeatMaps(675314).completeGet().get(0).getDifficulty();

        assertAll("Check values of osu! beatmap difficulty values.",
            () -> assertEquals(8.5, diff.getApproachRate()),
            () -> assertEquals(4.499068260192871, diff.getDisplay()),
            () -> assertEquals(6, diff.getHealthDrain()),
            () -> assertEquals(7, diff.getOverall()),
            () -> assertEquals(4, diff.getSize())
        );
    }

    @Test
    public void testRecentPlay() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("recent_yasha")));
        List<RecentPlay> plays = osu.getRecentPlays("Yasha").completeGet();

        assertEquals(10, plays.size());

        RecentPlay play = plays.get(0);

        assertAll("Check values of osu! recent play.",
            () -> assertEquals(1750033, play.getBeatMapId()),
            () -> assertEquals(79069, play.getScore()),
            () -> assertEquals(64, play.getMaxCombo()),
            () -> assertEquals(1, play.getCount50()),
            () -> assertEquals(8, play.getCount100()),
            () -> assertEquals(35, play.getCount300()),
            () -> assertEquals(1, play.getCountMiss()),
            () -> assertEquals(1, play.getCountKatu()),
            () -> assertEquals(5, play.getCountGeki()),
            () -> assertFalse(play.isPerfect()),
            () -> assertEquals(5938285, play.getUserId()),
            () -> assertEquals("F", play.getRank()),
            () -> assertEquals(1559337639000L, play.getDate().getTime()),
            () -> assertFalse(play.getMods().isEmpty())
        );
    }

    @Test
    public void testMatchData() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("match_random")));

        Match match = osu.getMatch(52270952).completeGet();
        assertAll("Check values of osu! match are correct.",
            () -> assertEquals(52270952, match.getMatchId()),
            () -> assertEquals("artside36's game", match.getName()),
            () -> assertEquals(1559389072000L, match.getStartTime().getTime()),
            () -> assertNull(match.getEndTime()),
            () -> assertFalse(match.getGames().isEmpty())
        );
    }

    @Test
    public void testMatchGameData() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("match_random")));
        Game game = osu.getMatch(52270952).completeGet().getGames().get(0);

        assertAll("Check values of osu! match game entity are correct.",
            () -> assertEquals(272751073, game.getGameId()),
            () -> assertEquals(1559389171000L, game.getStartTime().getTime()),
            () -> assertEquals(1559389339000L, game.getEndTime().getTime()),
            () -> assertEquals(315553, game.getBeatmapId()),
            () -> assertEquals(OsuMode.OSU, game.getMode()),
            () -> assertEquals(OsuScoreType.SCORE, game.getScoring()),
            () -> assertEquals(OsuTeamType.HEAD_TO_HEAD, game.getTeamType()),
            () -> assertTrue(game.getMods().isEmpty()),
            () -> assertEquals(2, game.getScores().size())
        );
    }

    @Test
    public void testMatchGameScoreData() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("match_random")));
        GameScore score = osu.getMatch(52270952).completeGet().getGames().get(0).getScores().get(0);

        assertAll("Check values of osu! match game score entity are correct.",
            () -> assertEquals(0, score.getSlot()),
            () -> assertNull(score.getTeam()),
            () -> assertEquals(14582211, score.getUserId()),
            () -> assertEquals(195858, score.getScore()),
            () -> assertEquals(66, score.getMaxCombo()),
            () -> assertEquals(3, score.getCount50()),
            () -> assertEquals(26, score.getCount100()),
            () -> assertEquals(154, score.getCount300()),
            () -> assertEquals(6, score.getCountMiss()),
            () -> assertEquals(48, score.getCountGeki()),
            () -> assertEquals(18, score.getCountKatu()),
            () -> assertFalse(score.isPerfect()),
            () -> assertTrue(score.isPass())
        );
    }
}
