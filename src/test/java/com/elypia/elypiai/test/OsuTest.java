package com.elypia.elypiai.test;

import com.elypia.elypiai.osu.BeatMap;
import com.elypia.elypiai.osu.Osu;
import com.elypia.elypiai.osu.OsuUser;
import com.elypia.elypiai.osu.data.MapGenre;
import com.elypia.elypiai.osu.data.MapLanguage;
import com.elypia.elypiai.osu.data.MapStatus;
import com.elypia.elypiai.osu.data.OsuMode;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OsuTest {

    @Test
    public void testOsu() {
        Osu osu = new Osu("api key");
        assertNotNull(osu);
    }

    @Test
    public void testOsuPlayer() {
        String json = "{\"user_id\":\"4185808\",\"username\":\"SethX3\",\"count300\":\"3226099\",\"count100\":\"877006\",\"count50\":\"149600\",\"playcount\":\"21875\",\"ranked_score\":\"4432289277\",\"total_score\":\"13387268671\",\"pp_rank\":\"74300\",\"level\":\"98.171\",\"pp_raw\":\"2653.35\",\"accuracy\":\"94.21471405029297\",\"count_rank_ss\":\"9\",\"count_rank_ssh\":\"1\",\"count_rank_s\":\"202\",\"count_rank_sh\":\"1\",\"count_rank_a\":\"327\",\"country\":\"GB\",\"pp_country_rank\":\"2376\",\"events\":[]}";
        JSONObject object = new JSONObject(json);
        OsuUser user = new OsuUser(OsuMode.OSU, new JSONObject(json));

        assertAll("Check values of osu! player.",
            () -> assertEquals(4185808, user.getId()),
            () -> assertEquals(OsuMode.OSU, user.getGameMode()),
            () -> assertEquals("SethX3", user.getUsername()),
            () -> assertEquals(3226099, user.getCount300()),
            () -> assertEquals(877006, user.getCount100()),
            () -> assertEquals(149600, user.getCount50()),
            () -> assertEquals(21875, user.getPlayCount()),
            () -> assertEquals(4432289277L, user.getRankedScore()),
            () -> assertEquals("4,432,289,277", user.getRankedScoreString()),
            () -> assertEquals(13387268671L, user.getTotalScore()),
            () -> assertEquals("13,387,268,671", user.getTotalScoreString()),
            () -> assertEquals(74300, user.getRank()),
            () -> assertEquals(98.171, user.getLevel()),
            () -> assertEquals(2653.35, user.getPp()),
            () -> assertEquals("2,653.35", user.getPpString()),
            () -> assertEquals(94.21471405029297, user.getAccuracy()),
            () -> assertEquals("94.21%", user.getAccuracyString()),
            () -> assertEquals(9, user.getCountSS()),
            () -> assertEquals(1, user.getCountSSH()),
            () -> assertEquals(202, user.getCountS()),
            () -> assertEquals(1, user.getCountSH()),
            () -> assertEquals(327, user.getCountA()),
            () -> assertEquals("GB", user.getCountry()),
            () -> assertEquals(2376, user.getCountryRank()),
            () -> assertEquals("https://osu.ppy.sh/u/4185808", user.getProfileUrl())
        );
    }

    @Test
    public void testOsuBeapMap() {
        String json = "{\"beatmapset_id\":\"301125\",\"beatmap_id\":\"675314\",\"approved\":\"-2\",\"total_length\":\"88\",\"hit_length\":\"87\",\"version\":\"Easy\",\"file_md5\":\"82b0e82610c192e97266b4f5ef9f0bf5\",\"diff_size\":\"3\",\"diff_overall\":\"2\",\"diff_approach\":\"3\",\"diff_drain\":\"2\",\"mode\":\"0\",\"approved_date\":null,\"last_update\":\"2015-04-07 05:34:34\",\"artist\":\"EXO K\",\"title\":\"Call me baby\",\"creator\":\"mr_missgirl\",\"bpm\":\"120\",\"source\":\"\",\"tags\":\"\",\"genre_id\":\"1\",\"language_id\":\"1\",\"favourite_count\":\"6\",\"playcount\":\"0\",\"passcount\":\"0\",\"max_combo\":\"101\",\"difficultyrating\":\"2.328305244445801\"}";
        JSONObject object = new JSONObject(json);
        BeatMap map = new BeatMap(new JSONObject(json));

        assertAll("Check values of osu! Beatmap.",
            () -> assertEquals(301125, map.getSetId()),
            () -> assertEquals(675314, map.getId()),
            () -> assertEquals(MapStatus.GRAVEYARD, map.getStatus()),
            () -> assertEquals(88, map.getTotalLength()),
            () -> assertEquals(87, map.getHitLength()),
            () -> assertEquals("Easy", map.getVersion()),
            () -> assertEquals("82b0e82610c192e97266b4f5ef9f0bf5", map.getFileMD5()),
            () -> assertEquals(3, map.getCircleSize()),
            () -> assertEquals(2, map.getDifficulty()),
            () -> assertEquals(3, map.getApproachRate()),
            () -> assertEquals(2, map.getHealthDrain()),
            () -> assertEquals(OsuMode.OSU, map.getMode()),
            () -> assertEquals(null, map.getApprovedDate()),
            () -> assertEquals("2015-04-07 05:34:34", map.getLastUpdate()),
            () -> assertEquals("EXO K", map.getArtist()),
            () -> assertEquals("Call me baby", map.getTitle()),
            () -> assertEquals("mr_missgirl", map.getCreator()),
            () -> assertEquals(120, map.getBPM()),
            () -> assertEquals("", map.getSource()),
            () -> assertEquals(0, map.getTags().size()),
            () -> assertEquals(MapGenre.UNSPECIFIED, map.getGenre()),
            () -> assertEquals(MapLanguage.OTHER, map.getLanguage()),
            () -> assertEquals(6, map.getFavCount()),
            () -> assertEquals(0, map.getPlayCount()),
            () -> assertEquals(0, map.getPassCount()),
            () -> assertEquals(101, map.getMaxCombo()),
            () -> assertEquals(2.328305244445801, map.getDiffRating())
        );
    }
}
