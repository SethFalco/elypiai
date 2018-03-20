package com.elypia.elypiai.test;

import com.elypia.elypiai.runescape.RuneScape;
import com.elypia.elypiai.runescape.RuneScapeUser;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

public class RuneScapeTest {

	@Test
	public void runescapeTest() {
		RuneScape runescape = new RuneScape();
		assertNotNull(runescape);
	}

	@Test
	public void parseUser() {
		String json = "{\"magic\":1854501,\"questsstarted\":1,\"totalskill\":2236,\"questscomplete\":158,\"questsnotstarted\":74,\"totalxp\":182416713,\"ranged\":26210527,\"activities\":[{\"date\":\"24-Jan-2018 22:00\",\"details\":\"I killed  a mithril dragon.\",\"text\":\"I killed  a mithril dragon.\"},{\"date\":\"14-Jan-2018 15:18\",\"details\":\"I killed 5 boss monsters   called:  Hobgoblin Geomancer and a skeletal horde   in Daemonheim.\",\"text\":\"I killed 5 boss monsters in Daemonheim.\"},{\"date\":\"14-Jan-2018 10:11\",\"details\":\"I levelled my  Dungeoneering skill, I am now level 79.\",\"text\":\"Levelled up Dungeoneering.\"},{\"date\":\"14-Jan-2018 10:11\",\"details\":\"I killed 2 boss monsters   called:  Hobgoblin Geomancer and an unholy cursebearer   in Daemonheim.\",\"text\":\"I killed 2 boss monsters in Daemonheim.\"}],\"skillvalues\":[{\"level\":99,\"xp\":262105274,\"rank\":115102,\"id\":3},{\"level\":99,\"xp\":188255163,\"rank\":94482,\"id\":1},{\"level\":99,\"xp\":164070336,\"rank\":119201,\"id\":4},{\"level\":99,\"xp\":162126659,\"rank\":119160,\"id\":6},{\"level\":99,\"xp\":149315898,\"rank\":120930,\"id\":0},{\"level\":99,\"xp\":147711613,\"rank\":133537,\"id\":2},{\"level\":99,\"xp\":143104232,\"rank\":114469,\"id\":18},{\"level\":99,\"xp\":133689428,\"rank\":143990,\"id\":7},{\"level\":99,\"xp\":131619366,\"rank\":158162,\"id\":11},{\"level\":93,\"xp\":76024676,\"rank\":208316,\"id\":8},{\"level\":58,\"xp\":40046299,\"rank\":135386,\"id\":26},{\"level\":81,\"xp\":23945708,\"rank\":241652,\"id\":13},{\"level\":80,\"xp\":20064567,\"rank\":263963,\"id\":12},{\"level\":80,\"xp\":20063398,\"rank\":214665,\"id\":25},{\"level\":80,\"xp\":19956154,\"rank\":201032,\"id\":22},{\"level\":79,\"xp\":18545019,\"rank\":302017,\"id\":5},{\"level\":79,\"xp\":18402702,\"rank\":261283,\"id\":24},{\"level\":79,\"xp\":18128678,\"rank\":348319,\"id\":9},{\"level\":78,\"xp\":17591387,\"rank\":253465,\"id\":23},{\"level\":77,\"xp\":15036292,\"rank\":240687,\"id\":16},{\"level\":75,\"xp\":13299084,\"rank\":296272,\"id\":15},{\"level\":75,\"xp\":13050002,\"rank\":317852,\"id\":14},{\"level\":72,\"xp\":9270717,\"rank\":421381,\"id\":10},{\"level\":67,\"xp\":5834155,\"rank\":292727,\"id\":17},{\"level\":66,\"xp\":5149478,\"rank\":324983,\"id\":20},{\"level\":63,\"xp\":3899253,\"rank\":361786,\"id\":21},{\"level\":63,\"xp\":3861740,\"rank\":310546,\"id\":19}],\"name\":\"Seth X3\",\"rank\":\"199,303\",\"melee\":100621114,\"combatlevel\":133,\"loggedIn\":\"false\"}";
		JSONObject object = new JSONObject(json);
		RuneScapeUser user = new RuneScapeUser(null, object);

		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals("Seth X3", user.getUsername()),
			() -> assertEquals(199303, user.getRank()),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Seth+X3", user.getLeaderboardUrl()),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Seth+X3&user2=Auri_Nimph", user.getLeaderboardUrl("Auri_Nimph")),
			() -> assertEquals("2,236", user.getTotalLevelString()),
			() -> assertEquals(2236, user.getTotalLevel()),
			() -> assertEquals(182416713, user.getTotalXp()),
			() -> assertEquals(133, user.getCombatLevel()),
			() -> assertEquals(158, user.getQuestsComplete()),
			() -> assertEquals(74, user.getQuestsNotStarted()),
			() -> assertEquals(1, user.getQuestsStarted())
		);
	}

	@Test
	public void levelToXp() {
		assertAll("Level to XP",
			() -> assertEquals(0, RuneScape.convertLevelToXp(1)),
			() -> assertEquals(1154, RuneScape.convertLevelToXp(10)),
			() -> assertEquals(4470, RuneScape.convertLevelToXp(20)),
			() -> assertEquals(13363, RuneScape.convertLevelToXp(30)),
			() -> assertEquals(101333, RuneScape.convertLevelToXp(50)),
			() -> assertEquals(737627, RuneScape.convertLevelToXp(70)),
			() -> assertEquals(1986068, RuneScape.convertLevelToXp(80)),
			() -> assertEquals(13034431, RuneScape.convertLevelToXp(99)),
			() -> assertEquals(14391160, RuneScape.convertLevelToXp(100)),
			() -> assertEquals(104273167, RuneScape.convertLevelToXp(120)),
			() -> assertEquals(188884740, RuneScape.convertLevelToXp(126))
		);
	}

	@Test
	public void xpToLevel() {
		assertAll("XP to Level",
			() -> assertEquals(1, RuneScape.convertXpToLevel(0)),
			() -> assertEquals(10, RuneScape.convertXpToLevel(1154)),
			() -> assertEquals(20, RuneScape.convertXpToLevel(4470)),
			() -> assertEquals(30, RuneScape.convertXpToLevel(13363)),
			() -> assertEquals(50, RuneScape.convertXpToLevel(101333)),
			() -> assertEquals(70, RuneScape.convertXpToLevel(737627)),
			() -> assertEquals(90, RuneScape.convertXpToLevel(5346332)),
			() -> assertEquals(99, RuneScape.convertXpToLevel(13034431)),
			() -> assertEquals(100, RuneScape.convertXpToLevel(14391160)),
			() -> assertEquals(120, RuneScape.convertXpToLevel(104273167)),
			() -> assertEquals(126, RuneScape.convertXpToLevel(188884740))
		);
	}

	@Test
	public void xpToLevelNonPrecise() {
		assertAll("XP to Level with Non-Precise Amounts",
			() -> assertEquals(1, RuneScape.convertXpToLevel(1)),
			() -> assertEquals(1, RuneScape.convertXpToLevel(50)),
			() -> assertEquals(1, RuneScape.convertXpToLevel(82)),
			() -> assertEquals(36, RuneScape.convertXpToLevel(25000)),
			() -> assertEquals(65, RuneScape.convertXpToLevel(471000)),
			() -> assertEquals(98, RuneScape.convertXpToLevel(13034430))
		);
	}

	@Test
	public void levelTooHigh() {
		assertEquals(-1, RuneScape.convertLevelToXp(Integer.MAX_VALUE));
	}

	@Test
	public void illegalLevelArguments() {
		assertAll("Exceptions from Illegal Level Arguments",
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertLevelToXp(0)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertLevelToXp(-1)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertLevelToXp(-99)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertLevelToXp(-126)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertLevelToXp(-999)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertLevelToXp(Integer.MIN_VALUE))
		);
	}

	@Test
	public void illegalXpArguments() {
		assertAll("Exceptions from Illegal XP Arguments",
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertXpToLevel(-1)),
			() -> assertThrows(IllegalArgumentException.class, () ->RuneScape.convertXpToLevel(-1000)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertXpToLevel(-999999)),
			() -> assertThrows(IllegalArgumentException.class, () ->RuneScape.convertXpToLevel(-2141724413)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.convertXpToLevel(Long.MIN_VALUE))
		);
	}

	@Test
	public void xpToLevelLoopTest() {
		assertTimeout(ofSeconds(30), () -> RuneScape.convertXpToLevel(Long.MAX_VALUE));
	}
}
