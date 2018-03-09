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

    public void testInvalidNumber() {
        Regex regex = Regex.NUMBER;

        assertAll("Test Invalid Numbers",
            () -> assertFalse(regex.matches("1000.")),
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
