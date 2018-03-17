package com.elypia.elypiai.test;

import com.elypia.elypiai.utils.Regex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexTest {

    @Test
    public void testDiscordBotTokens() {
        Regex regex = Regex.DISCORD_BOT_TOKEN;

        assertAll("Test Valid Discord Bot Tokens",
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYL_dw.SAlofzaD-_9uNx_AJajp13p3ML4")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYL_xw.BsIzAYD_V0wUL9dkuYawU--TLyc")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYL_zg.92i0q4TP41HYUu-XmALcZcdxlWA")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYL_2w.5Lvwen7Oc6DkJibwTLNJtIRcjFc")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYL_4g.w1UC_PtTUFiBRaq5gLlBIBarqpE")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYL_-w.vxstDtBw9yPxDIcoMhwh6s5VMXg")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYMAAw.pCaAI1WcO2DzYNZMvWkB6aH07Mk")),
            () -> assertTrue(regex.matches("MjQxMzk1NzI1ODUyNzM3NTM2.DYMACQ.FGfaBlePAy7wJO48hfMq-hRsmWw")),
            () -> assertTrue(regex.matches("NDIxMzU0MTMxNjY0NTM1NTcz.DYMAKw.FEKd4-CNVp8N7dqBGsKXNCqEGR0")),
            () -> assertTrue(regex.matches("NDIxMzU0MTMxNjY0NTM1NTcz.DYMANw.bvMRNujFigPJX54GKPWf8vsRlxY")),
            () -> assertTrue(regex.matches("NDIxMzU0MTMxNjY0NTM1NTcz.DYMAQQ.hLT02la6ap4IMhmXq4lNBZc6oPs")),
            () -> assertTrue(regex.matches("NDIxMzU0MTMxNjY0NTM1NTcz.DYMASg.sirHnLaqqbN8b6oUywLZ496QrOs")),
            () -> assertTrue(regex.matches("NDIxMzU0MTMxNjY0NTM1NTcz.DYMAaQ.ORNgDYGwxr6bgTqqM1mQKwi2PAc")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMAfA.vlUrNwZc5VFLgxyuWe9e8kNNKrQ")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMAhw.rw-dvWYg1j3D08juIlNxOdxxmAQ")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMAjQ.0ZyVG81e-aiOspV4qdr0SSFYQTg")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMAlQ.w5syXon2UPmEV00mmGtlSG5keFQ")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMAmw.-0tF5lrhgy_2j4boTzdc9E6DuWU")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMAoQ.TEJNny4xCnV6sN2OluQ91cJyc8o")),
            () -> assertTrue(regex.matches("NDIxMzU0NTIxNjQ2ODU4MjUx.DYMApw.QFukR5BEDqvNmVqVI19_ZEFX_Fw"))
        );
    }

    @Test
    public void testSteamApiKeys() {
        Regex regex = Regex.STEAM_API_KEY;

        assertAll("Test Valid Steam API Keys",
            () -> assertTrue(regex.matches("DCA56FE963FE3D2CE23DF7DF30AAA579")),
            () -> assertTrue(regex.matches("821A060BC2096B8CDC223A82E177CD09")),
            () -> assertTrue(regex.matches("37FB0B7399A85F1547180F3B1385BF3A")),
            () -> assertTrue(regex.matches("98CC1C851F39F7E0E218265B8F3C2F40")),
            () -> assertTrue(regex.matches("B168F3455086D0C48AFD2BD94853067B")),
            () -> assertTrue(regex.matches("5904A063F905D60500776269B4DA5473")),
            () -> assertTrue(regex.matches("4A6E6F6C273237E2FDAC51E2437C4B3B")),
            () -> assertTrue(regex.matches("BC61EF783D54C781B6C10827F473B4F5")),
            () -> assertTrue(regex.matches("6D05D8C54F2C01504D01392307958EFF")),
            () -> assertTrue(regex.matches("2225347F769BA331D0C0B0BB4536507B"))
        );
    }

    @Test
    public void testYandexApiKeys() {
        Regex regex = Regex.YANDEX_API_KEY;

        assertAll("Test Valid Yandex API Keys",
            () -> assertTrue(regex.matches("trnsl.1.1.20170220T173803Z.737da74618485447.8b810aeb3bd80c41f13f2cb27d1cae082b3373ef")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T092933Z.8c3a2482975ec1d5.1d5f4530bb85b618d4ed8476ee42c9cc7fc1efe5")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T093008Z.9295f7482689a713.a831b608277ef7b6828b0211520efd27185700ae")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T093019Z.e9f126e78eb7ba85.8ffcd290e1aa7f605a55957071a844875cf13310")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T093027Z.35460d379d5867bf.5afea4f55f76090058a967f4efafb66cb308f04a")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T093035Z.db5b1d61df219a66.0cf9302b1e1a467f9d81ae9aa8f3b4ca146cb9da")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T093044Z.c0790c3816440d3d.9b4b63d4f50cb5b518b55430a0fab6a335595921")),
            () -> assertTrue(regex.matches("trnsl.1.1.20180309T093051Z.1e8942f97cf8c1ce.e24fafa3939670dd8df63cdff08d95dc808c0663"))
        );
    }

    @Test
    public void testOsuApiKeys() {
        Regex regex = Regex.OSU_API_KEY;

        assertAll("Test Valid osu! API Keys",
            () -> assertTrue(regex.matches("0a73573363aed3fb9512e8f7e64812de0aa0eb7f")),
            () -> assertTrue(regex.matches("69c0b6bd9e2265ec9879b1002e1a3402f9c833bb")),
            () -> assertTrue(regex.matches("76fde4f8805605d6e437470e0bf81edd8d61903c")),
            () -> assertTrue(regex.matches("ec47d3fbec2d2009514f10843b8fb436887d5124")),
            () -> assertTrue(regex.matches("acdd3e5c46bbc0aa596f32fe63463d74c7396f66")),
            () -> assertTrue(regex.matches("2fc41254cc8c8393be49c4ef91868ef987ae27f9")),
            () -> assertTrue(regex.matches("2bfa90a77c0d02bc706ba1089a976568bb3fb4b3")),
            () -> assertTrue(regex.matches("bb3ece55184596419b8fdfcf73c83aa16206e098")),
            () -> assertTrue(regex.matches("98782d0adc7e0cd51fa49d37d7017cfa9ee46146")),
            () -> assertTrue(regex.matches("3cc15f79fe96bb03f14a5371c3dec95aa30d1353"))
        );
    }

    @Test
    public void testLeagueOfLegendsApiKeys() {
        Regex regex = Regex.LEAGUE_OF_LEGENDS_API_KEY;

        assertAll("Test Valid League of Legends API Keys",
            () -> assertTrue(regex.matches("RGAPI-6ab4023a-eac1-431c-a3ca-a9be7d369d26")),
            () -> assertTrue(regex.matches("RGAPI-d20458a7-519e-4069-9b49-978a753fd59f")),
            () -> assertTrue(regex.matches("RGAPI-eff6c9f8-b0cb-4c8d-b314-eea6f1acaac1")),
            () -> assertTrue(regex.matches("RGAPI-dd45e928-1107-4708-b3c1-be705f53963e")),
            () -> assertTrue(regex.matches("RGAPI-d392e866-a56c-4d6f-9a87-e4693b54ae0d")),
            () -> assertTrue(regex.matches("RGAPI-334ceebd-1e82-416b-9fc2-e9a3b33abde4")),
            () -> assertTrue(regex.matches("RGAPI-4d63bf38-e273-4d0c-8678-3b30a67eb6e0")),
            () -> assertTrue(regex.matches("RGAPI-03a6d507-d863-4f63-b40f-1de11eb661f7")),
            () -> assertTrue(regex.matches("RGAPI-a5a2fc27-8876-45fd-b49f-466cda33a70c")),
            () -> assertTrue(regex.matches("RGAPI-0cd32620-e821-46b2-b4c3-691bfb2c919c"))
        );
    }

    @Test
    public void testTwitchSecrets() {
        Regex regex = Regex.TWITCH_SECRET;

        assertAll("Test Valid Twitch Secrets Secrets",
            () -> assertTrue(regex.matches("uj03bz9l56n9kylf2p0za1yt8o9m3j")),
            () -> assertTrue(regex.matches("3njgo97a5031gbutxljfn06dmrvoto")),
            () -> assertTrue(regex.matches("6fnqxoga6sgzv3mmh1i29rouv327lo")),
            () -> assertTrue(regex.matches("z3vtl7363jfo5ptpygo9tfmtacv99q")),
            () -> assertTrue(regex.matches("gg0zqbbjqo4s4527dthrv966wo70bv")),
            () -> assertTrue(regex.matches("b35z7t2war7j3u967f2pl1rif340lt")),
            () -> assertTrue(regex.matches("ejrtuvxrg6bb3u5l49wtotwgtnro0m")),
            () -> assertTrue(regex.matches("yoxenrj6kr35q34p8h5lmiaay9vav7")),
            () -> assertTrue(regex.matches("pb7wqfoy7rclngeqmmfn93edv74zqs"))
        );
    }

    @Test
    public void testGoogleApiKeys() {
        Regex regex = Regex.GOOGLE_API_KEY;

        assertAll("Test Valid League of Legends API Keys",
            () -> assertTrue(regex.matches("AIzaSyAbtg-iHXRUFN_b-ElT-07fKemXe__x320")),
            () -> assertTrue(regex.matches("AIzaSyBSXpt4aMDRs-6qu37mazTGqdyXq1yvEFI")),
            () -> assertTrue(regex.matches("AIzaSyBrMeXxJyHPYg1GFei7JtsIYibcN5ghvzU")),
            () -> assertTrue(regex.matches("AIzaSyBfcE5hQoUeWmhPm83Jc0SCBD5LpF7oDHg")),
            () -> assertTrue(regex.matches("AIzaSyCvAZ5FrcMVHbfsY9O8NnjGS0rYRItDFoY")),
            () -> assertTrue(regex.matches("AIzaSyCMcbrpdDnPdp9qNc6dtniMd-Z9NQqNgu8")),
            () -> assertTrue(regex.matches("AIzaSyBH7dUcWI9LGpEfOFFLa_PNJU6ELn7Z71s")),
            () -> assertTrue(regex.matches("AIzaSyAhPqRL68PcSXWbKXU8AKlydufV4wl42Zs")),
            () -> assertTrue(regex.matches("AIzaSyBVLIA-hvr5Til0fybkHEaJwtQtW_ysUNo")),
            () -> assertTrue(regex.matches("AIzaSyAqonbXQfhFTsk_OWdI_csSDHhdnaASI6E")),
            () -> assertTrue(regex.matches("AIzaSyADbhb5UPOCG0DFdLmavZX7HWzE0l_Gjpo")),
            () -> assertTrue(regex.matches("AIzaSyAVSmoTDNIlNFWMNHX2U1gVTgvFphTc72E")),
            () -> assertTrue(regex.matches("AIzaSyBiwY_MwgSpbwy3w3IQzxTRXLrDHPNmfxs")),
            () -> assertTrue(regex.matches("AIzaSyB95drER65QN1tkUrI90P_n5zVr0gzeScA")),
            () -> assertTrue(regex.matches("AIzaSyCrV1SsXJMGbIZN_YFEh0KMCZR391vvUwU"))
        );
    }

    @Test
    public void testAmazonAccessKeys() {
        Regex regex = Regex.AMAZON_ACCESS_KEY;

        assertAll("Amazon Access Keys",
            () -> assertTrue(regex.matches("AKIAJAKAC6MNKA34345A")),
            () -> assertTrue(regex.matches("AKIAJ6MFKEL2E63HBQGQ")),
            () -> assertTrue(regex.matches("AKIAIW53ZILBUOZARGFQ")),
            () -> assertTrue(regex.matches("AKIAIQTK4AQKUWJCHCQA")),
            () -> assertTrue(regex.matches("AKIAIZEVEE4ZUUPWKUQA")),
            () -> assertTrue(regex.matches("AKIAIYGODVOUAGHFYM7Q")),
            () -> assertTrue(regex.matches("AKIAJOKMXSJPT5UYH2XQ")),
            () -> assertTrue(regex.matches("AKIAI7YZE5FVYKTBDSSA")),
            () -> assertTrue(regex.matches("AKIAI5OLY4B42DJGA6UA")),
            () -> assertTrue(regex.matches("AKIAJWVZI5A54RMXPTUA")),
            () -> assertTrue(regex.matches("AKIAJ3GKNLGICKDZILTQ")),
            () -> assertTrue(regex.matches("AKIAJBFIH7E7G4CPTQMQ")),
            () -> assertTrue(regex.matches("AKIAJNT5RQR5CBXOP66Q")),
            () -> assertTrue(regex.matches("AKIAJRKWGI3DJX2VWY4A")),
            () -> assertTrue(regex.matches("AKIAI7LFZSRDSH2JNPZQ")),
            () -> assertTrue(regex.matches("AKIAJNT5RQR5CBXOP66Q")),
            () -> assertTrue(regex.matches("AKIAICVVQ3AGUM3WKEAA")),
            () -> assertTrue(regex.matches("AKIAJHW2K6Y7LCMWIVYA")),
            () -> assertTrue(regex.matches("AKIAIJDSJHM7A4HNR4VQ")),
            () -> assertTrue(regex.matches("AKIAJ6XFV2WPPB5FVOPA")),
            () -> assertTrue(regex.matches("AKIAJJE457H4XYGP5D3Q")),
            () -> assertTrue(regex.matches("AKIAJEHOLY6IOYAUXFRQ")),
            () -> assertTrue(regex.matches("AKIAJDDBIZIANG6XPQHA")),
            () -> assertTrue(regex.matches("AKIAIQRLVBSHMAAO5WAQ")),
            () -> assertTrue(regex.matches("AKIAI7XFKMZOZOBWDJZQ")),
            () -> assertTrue(regex.matches("AKIAIOS4JDIS7B4U3ZXA")),
            () -> assertTrue(regex.matches("AKIAJ57BNNIZXON6EJPQ")),
            () -> assertTrue(regex.matches("AKIAIRZRM5QSJL3YYHRQ")),
            () -> assertTrue(regex.matches("AKIAJBBGMDI6D4BLPLUA")),
            () -> assertTrue(regex.matches("AKIAJQOS5PR4LQY6VVFQ")),
            () -> assertTrue(regex.matches("AKIAIMXKQT5VLBUDC2TA")),
            () -> assertTrue(regex.matches("AKIAIWGBCMPGZZLEB6SQ")),
            () -> assertTrue(regex.matches("AKIAIVSFA7YIPDTQRU5Q")),
            () -> assertTrue(regex.matches("AKIAIZ65HTWXRQNHXCOA")),
            () -> assertTrue(regex.matches("AKIAI6KGFB6XST5W6RWQ")),
            () -> assertTrue(regex.matches("AKIAIW53ZILBUOZARGFQ"))
        );
    }

    @Test
    public void testAmazonSecret() {
        Regex regex = Regex.AMAZON_SECRET;

        assertAll("Amazon Secrets",
            () -> assertTrue(regex.matches("7285o+OxlLfUoBCQOUupX0QX0VBfpx+cS4Q1LZjx")),
            () -> assertTrue(regex.matches("wEVCd5VVmDyuno41B+KUquQQAOJ+In0q5kXMVD+q")),
            () -> assertTrue(regex.matches("111XNyDTSZfTNTUuDRf7HQQeU4HMnNDaY2v7GQul")),
            () -> assertTrue(regex.matches("2l2ucaSfeL3X5qNnA/w1PCK4Tw4EjcUJHireIUVU")),
            () -> assertTrue(regex.matches("GO4tn4RqALfF7pGT/L08EPCWQx8MLJF+nNfUkUX+")),
            () -> assertTrue(regex.matches("jz+FRGuufOCxQLJNGRHOUcKzLe8TZPi5p0CXVDED")),
            () -> assertTrue(regex.matches("B+mk+yPEMztWI/5f2CSBWfvYoxK9z8W5RxIpbWqB")),
            () -> assertTrue(regex.matches("ZcEgMYResiksPFHqSYUxiJzR2b7s+tirYnNEs2LP")),
            () -> assertTrue(regex.matches("vnp+UHXNi3u5X0VZF0T5ybHxoXyDw0um1g8VtGSK")),
            () -> assertTrue(regex.matches("RMKfRaDecjytMEPh/Q2FPOmeESKLCn7EWkbJlPSn")),
            () -> assertTrue(regex.matches("UaeCChxMPl35qSYSq1yJ4hOaCCxuCLu1pG8eDGX7")),
            () -> assertTrue(regex.matches("IdS1zqDGBJyVANwhLpdECjEACxVZ/35FyBYAbhqc")),
            () -> assertTrue(regex.matches("iU8JJdULVUM5yb4vY8wNAkuFdUfiTeLzVHAemTZY")),
            () -> assertTrue(regex.matches("vEeBIKYUaz46wuaLyN90xEMyxtUmeV77B8SR/oGZ")),
            () -> assertTrue(regex.matches("7E7QAM9o7db5IdjQ57p6VXKh/OilkCtJppxIq6eM")),
            () -> assertTrue(regex.matches("7+0sjFOYKrdcwr2VqQUQZ/Zo3g6cDRwOJA3K64gR")),
            () -> assertTrue(regex.matches("PrtKRKu0qJweyVoGJusV7vSA0xiRBU1f3/KOAT1a")),
            () -> assertTrue(regex.matches("K5YHcROgqp5n1NVODGbF88Fk2o8ucmDFGZmtvkeL")),
            () -> assertTrue(regex.matches("0bC1LOUvgRLQ7B9KX/iueRS73TnTH6QWU+HMIsBX")),
            () -> assertTrue(regex.matches("I0bOxq+M1Xz0Cgqao9Uv7QEMVxpVzASWJRz7ELXv")),
            () -> assertTrue(regex.matches("pn6O3DXyhuRoMQ8iGCRqEViZ8XH+ag149SrAuNiP")),
            () -> assertTrue(regex.matches("GinP3fuTJplAjjsqibM+FKcQAM444OP6ZShr5fy8")),
            () -> assertTrue(regex.matches("afbuhr9T0wwY5K3vDbMZK8xTQWZ9y9BVx6FKPxp6")),
            () -> assertTrue(regex.matches("qbIdPaon1S56McAVWluIf6E6UFDryNjhSUFsmYfO")),
            () -> assertTrue(regex.matches("hIUSPi//Fm9VGLXxjL1fMVYSNjUG2UGeWUTCRSf9")),
            () -> assertTrue(regex.matches("OzSgyq0R6UqAoUzyAednLmiGngqYHuGnthFP0JeT")),
            () -> assertTrue(regex.matches("gICANS9xP0L46IrpqDh5+H8JYJF6X3nnCNTq0ize")),
            () -> assertTrue(regex.matches("SzfTNKynKjnMFGMsBAk2BedIrIY8TaeDelAl/orw"))
        );
    }

    @Test
    public void testValidNumbers() {
        Regex regex = Regex.NUMBER;

        assertAll("Test Valid Numbers",
            () -> assertTrue(regex.matches("0")),
            () -> assertTrue(regex.matches("1")),
            () -> assertTrue(regex.matches("-1")),
            () -> assertTrue(regex.matches("99")),
            () -> assertTrue(regex.matches("10294")),
            () -> assertTrue(regex.matches("54367547")),
            () -> assertTrue(regex.matches("858548648964745")),
            () -> assertTrue(regex.matches("7457335857868537525")),
            () -> assertTrue(regex.matches("-456265626")),
            () -> assertTrue(regex.matches("-894793759759205750157865")),
            () -> assertTrue(regex.matches("5981790712309578291587957")),
            () -> assertTrue(regex.matches("-1223.5325235235")),
            () -> assertTrue(regex.matches("2342525.6346363")),
            () -> assertTrue(regex.matches("1002.")),
            () -> assertTrue(regex.matches(".4234235")),
            () -> assertTrue(regex.matches("23424.5325525"))
        );
    }

    @Test
    public void testInvalidNumber() {
        Regex regex = Regex.NUMBER;

        assertAll("Test Invalid Numbers",
            () -> assertFalse(regex.matches("")),
            () -> assertFalse(regex.matches("    ")),
            () -> assertFalse(regex.matches("Hello world!")),
            () -> assertFalse(regex.matches("3993493.p")),
            () -> assertFalse(regex.matches("a.a")),
            () -> assertFalse(regex.matches("--123")),
            () -> assertFalse(regex.matches("12312.23424.53252")),
            () -> assertFalse(regex.matches("123,,456")),
            () -> assertFalse(regex.matches("1234,567"))
        );
    }
}
