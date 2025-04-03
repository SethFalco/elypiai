/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.osu;

import org.elypia.elypiai.osu.models.BeatMap;
import org.elypia.elypiai.osu.models.Game;
import org.elypia.elypiai.osu.models.GameScore;
import org.elypia.elypiai.osu.models.MapDifficulty;
import org.elypia.elypiai.osu.models.MapGenre;
import org.elypia.elypiai.osu.models.MapLanguage;
import org.elypia.elypiai.osu.models.MapStatus;
import org.elypia.elypiai.osu.models.Match;
import org.elypia.elypiai.osu.models.OsuEvent;
import org.elypia.elypiai.osu.models.OsuMode;
import org.elypia.elypiai.osu.models.OsuScoreType;
import org.elypia.elypiai.osu.models.OsuTeamType;
import org.elypia.elypiai.osu.models.Player;
import org.elypia.elypiai.osu.models.RecentPlay;
import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OsuTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static Osu osu;

    @BeforeEach
    public void beforeEach() {
        osu = new Osu("api key", serverExtension.getRequestUrl());
    }

    @Test
    public void testOsu() {
        Osu osu = new Osu("api key");
        assertNotNull(osu);
        assertEquals("api key", osu.getApiKey());
    }

    @WebServerTest("player-nathan-on-osu.json")
    public void testOsuPlayer() {
        Player user = osu.getPlayer("nathan on osu").blockingGet();

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
            () -> assertEquals("https://osu.ppy.sh/users/124493", user.getProfileUrl()),
            () -> assertFalse(user.getEvents().isEmpty())
        );
    }

    @WebServerTest("player-nathan-on-osu.json")
    public void testOsuPlayerEvent() {
        OsuEvent event = osu.getPlayer("nathan on osu").blockingGet().getEvents().get(0);

        assertAll("Check values of osu! player first event.",
            () -> assertEquals("nathan on osu has lost first place on xi - Blue Zenith [FOUR DIMENSIONS] (osu!)", event.getMessage()),
            () -> assertEquals(658127, event.getBeatmapId()),
            () -> assertEquals(292301, event.getBeatmapSetId()),
            () -> assertEquals(1559304704L, event.getDate().toEpochSecond()),
            () -> assertEquals(2, event.getEpicFactor())
        );
    }

    @WebServerTest("beatmap-675314.json")
    public void testOsuBeapMap() {
        BeatMap map = osu.getBeatMaps(675314).blockingSingle().get(0);

        assertAll("Check values of osu! Beatmap.",
            () -> assertEquals(675314, map.getSetId()),
            () -> assertEquals(1428633, map.getId()),
            () -> assertEquals(MapStatus.RANKED, map.getStatus()),
            () -> assertEquals(88, map.getTotalLength()),
            () -> assertEquals(87, map.getHitLength()),
            () -> assertEquals("Insane", map.getVersion()),
            () -> assertEquals("c7d039de44853caa2ee7a554a22d61bc", map.getFileMd5()),
            () -> assertEquals(OsuMode.OSU, map.getMode()),
            ()-> assertEquals(1506777413L, map.getSubmissionDate().toEpochSecond()),
            () -> assertEquals(1521754807L, map.getApprovedDate().toEpochSecond()),
            () -> assertEquals(1520937899L, map.getLastUpdate().toEpochSecond()),
            () -> assertEquals("TRUE", map.getArtist()),
            () -> assertEquals("BUTTERFLY EFFECTOR (TV Size)", map.getTitle()),
            () -> assertEquals("kunka", map.getCreator()),
            () -> assertEquals(1741295, map.getCreatorId()),
            () -> assertEquals(141, map.getBPM()),
            () -> assertEquals("ひなろじ ～from Luck & Logic～", map.getSource()),
            () -> assertEquals("opening hinaroji hinalogic from luck & logic kunkakunkakunkakunka", map.getTags()),
            () -> assertEquals(MapGenre.ANIME, map.getGenre()),
            () -> assertEquals(MapLanguage.JAPANESE, map.getLanguage()),
            () -> assertEquals(53, map.getFavoriteCount()),
            () -> assertEquals(43632, map.getPlayCount()),
            () -> assertEquals(3987, map.getPassCount()),
            () -> assertEquals(546, map.getMaxCombo()),
            () -> assertNotNull(map.getDifficulty())
        );
    }

    @WebServerTest("beatmap-675314.json")
    public void testOsuMapDifficulty() {
        MapDifficulty diff = osu.getBeatMaps(675314).blockingSingle().get(0).getDifficulty();

        assertAll("Check values of osu! beatmap difficulty values.",
            () -> assertEquals(8.5, diff.getApproachRate()),
            () -> assertEquals(4.499068260192871, diff.getDisplay()),
            () -> assertEquals(6, diff.getHealthDrain()),
            () -> assertEquals(7, diff.getOverall()),
            () -> assertEquals(4, diff.getSize())
        );
    }

    @WebServerTest("recent-yasha.json")
    public void testRecentPlay() {
        List<RecentPlay> plays = osu.getRecentPlays("Yasha").blockingSingle();
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
            () -> assertEquals(1559337639L, play.getDate().toEpochSecond()),
            () -> assertFalse(play.getMods().isEmpty())
        );
    }

    @WebServerTest("match-random.json")
    public void testMatchData() {
        Match match = osu.getMatch(52270952).blockingGet();
        assertAll("Check values of osu! match are correct.",
            () -> assertEquals(52270952, match.getMatchId()),
            () -> assertEquals("artside36's game", match.getName()),
            () -> assertEquals(1559389072L, match.getStartTime().toEpochSecond()),
            () -> assertNull(match.getEndTime()),
            () -> assertFalse(match.getGames().isEmpty())
        );
    }

    @WebServerTest("match-random.json")
    public void testMatchGameData() {
        Game game = osu.getMatch(52270952).blockingGet().getGames().get(0);

        assertAll("Check values of osu! match game entity are correct.",
            () -> assertEquals(272751073, game.getGameId()),
            () -> assertEquals(1559389171L, game.getStartTime().toEpochSecond()),
            () -> assertEquals(1559389339L, game.getEndTime().toEpochSecond()),
            () -> assertEquals(315553, game.getBeatmapId()),
            () -> assertEquals(OsuMode.OSU, game.getMode()),
            () -> assertEquals(OsuScoreType.SCORE, game.getScoring()),
            () -> assertEquals(OsuTeamType.HEAD_TO_HEAD, game.getTeamType()),
            () -> assertTrue(game.getMods().isEmpty()),
            () -> assertEquals(2, game.getScores().size())
        );
    }

    @WebServerTest("match-random.json")
    public void testMatchGameScoreData() {
        GameScore score = osu.getMatch(52270952).blockingGet().getGames().get(0).getScores().get(0);

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
