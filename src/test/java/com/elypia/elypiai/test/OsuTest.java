package com.elypia.elypiai.test;

import com.elypia.elypiai.osu.OsuUser;
import com.elypia.elypiai.osu.data.OsuMode;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OsuTest {

    @Test
    public void testOsuPlayer() {
        String json = "{\"user_id\":\"4185808\",\"username\":\"SethX3\",\"count300\":\"3226099\",\"count100\":\"877006\",\"count50\":\"149600\",\"playcount\":\"21875\",\"ranked_score\":\"4432289277\",\"total_score\":\"13387268671\",\"pp_rank\":\"74300\",\"level\":\"98.171\",\"pp_raw\":\"2653.35\",\"accuracy\":\"94.21471405029297\",\"count_rank_ss\":\"9\",\"count_rank_ssh\":\"1\",\"count_rank_s\":\"202\",\"count_rank_sh\":\"1\",\"count_rank_a\":\"327\",\"country\":\"GB\",\"pp_country_rank\":\"2376\",\"events\":[]}";
        JSONObject object = new JSONObject(json);

        OsuUser user = new OsuUser(OsuMode.OSU, new JSONObject(json));

        assertAll("Check values of osu! player.",
            () -> assertEquals(4185808, user.getUserID()),
            () -> assertEquals("SethX3", user.getUsername()),
            () -> assertEquals(3226099, user.getCount300()),
            () -> assertEquals(877006, user.getCount100()),
            () -> assertEquals(149600, user.getCount50()),
            () -> assertEquals(21875, user.getPlayCount()),
            () -> assertEquals(4432289277L, user.getRankedScore()),
            () -> assertEquals("4,432,289,277", user.getRankedScorePretty()),
            () -> assertEquals(13387268671L, user.getTotalScore()),
            () -> assertEquals("13,387,268,671", user.getTotalScorePretty()),
            () -> assertEquals(74300, user.getLeaderboardRank()),
            () -> assertEquals(98.171, user.getLevel()),
            () -> assertEquals(2653.35, user.getPP()),
            () -> assertEquals("2,653.35", user.getPpPretty()),
            () -> assertEquals(94.21471405029297, user.getAccuracy()),
            () -> assertEquals("94.21%", user.getAccuracyPretty()),
            () -> assertEquals(9, user.getCountRankSS()),
            () -> assertEquals(202, user.getCountRankS()),
            () -> assertEquals(327, user.getCountRankA()),
            () -> assertEquals("GB", user.getCountry()),
            () -> assertEquals(2376, user.getPPCountryRank())
        );
    }

    @Test
    public void testTaikoPlayer() {
        String json = "{\"user_id\":\"4185808\",\"username\":\"SethX3\",\"count300\":\"15063\",\"count100\":\"5574\",\"count50\":\"0\",\"playcount\":\"162\",\"ranked_score\":\"9484570\",\"total_score\":\"16288190\",\"pp_rank\":\"57265\",\"level\":\"13.7146\",\"pp_raw\":\"618.092\",\"accuracy\":\"94.16777801513672\",\"count_rank_ss\":\"3\",\"count_rank_ssh\":\"0\",\"count_rank_s\":\"21\",\"count_rank_sh\":\"0\",\"count_rank_a\":\"14\",\"country\":\"GB\",\"pp_country_rank\":\"1218\",\"events\":[]}";
        JSONObject object = new JSONObject(json);

        OsuUser user = new OsuUser(OsuMode.TAIKO, new JSONObject(json));
    }

    @Test
    public void testCatchTheBeatPlayer() {
        String json = "{\"user_id\":\"4185808\",\"username\":\"SethX3\",\"count300\":\"2107\",\"count100\":\"704\",\"count50\":\"3945\",\"playcount\":\"17\",\"ranked_score\":\"2126650\",\"total_score\":\"5965390\",\"pp_rank\":\"205717\",\"level\":\"9.89204\",\"pp_raw\":\"36.5134\",\"accuracy\":\"99.18384552001953\",\"count_rank_ss\":\"2\",\"count_rank_ssh\":\"0\",\"count_rank_s\":\"2\",\"count_rank_sh\":\"0\",\"count_rank_a\":\"1\",\"country\":\"GB\",\"pp_country_rank\":\"3331\",\"events\":[]}";
        JSONObject object = new JSONObject(json);

        OsuUser user = new OsuUser(OsuMode.CATCH_THE_BEAT, new JSONObject(json));
    }

    @Test
    public void testManiaPlayer() {
        String json = "{\"user_id\":\"4185808\",\"username\":\"SethX3\",\"count300\":\"345059\",\"count100\":\"216845\",\"count50\":\"14336\",\"playcount\":\"1362\",\"ranked_score\":\"201252615\",\"total_score\":\"382471822\",\"pp_rank\":\"135538\",\"level\":\"38.8182\",\"pp_raw\":\"381.165\",\"accuracy\":\"93.89826965332031\",\"count_rank_ss\":\"1\",\"count_rank_ssh\":\"0\",\"count_rank_s\":\"23\",\"count_rank_sh\":\"0\",\"count_rank_a\":\"60\",\"country\":\"GB\",\"pp_country_rank\":\"2041\",\"events\":[]}";
        JSONObject object = new JSONObject(json);

        OsuUser user = new OsuUser(OsuMode.MANIA, new JSONObject(json));
    }
}
