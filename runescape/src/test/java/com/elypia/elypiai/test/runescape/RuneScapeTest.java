package com.elypia.elypiai.test.runescape;

import com.elypia.elypiai.common.core.ex.FriendlyException;
import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.runescape.*;
import com.elypia.elypiai.runescape.data.*;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

public class RuneScapeTest {

	private static MockWebServer server;
	private static RuneScape rs;

	@BeforeEach
	public void beforeEach() throws IOException {
		server = new MockWebServer();
		server.start();
		rs = new RuneScape(new URL("http://localhost:" + server.getPort()));
	}

	@AfterEach
	public void afterEach() throws IOException {
		server.close();
	}

	@Test
	public void normalInstance() {
		RuneScape rs = new RuneScape();
		assertNotNull(rs);
	}

	@Test
	public void parseUser() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_sethii.json")));

		Player user = rs.getUser("Sethii").completeGet();

		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals("Sethii", user.getUsername()),
			() -> assertEquals(215938, user.getRank()),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Sethii", user.getLeaderboardUrl()),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Sethii&user2=Auri_Nimph", user.getLeaderboardUrl("Auri_Nimph")),
			() -> assertEquals("2,249", user.getTotalLevelString()),
			() -> assertEquals(2249, user.getTotalLevel()),
			() -> assertEquals(187689300, user.getTotalXp()),
			() -> assertEquals("187,689,300", user.getTotalXpString()),
			() -> assertEquals(133, user.getCombatLevel()),
			() -> assertEquals(170, user.getQuestsComplete()),
			() -> assertEquals(119, user.getQuestsNotStarted()),
			() -> assertEquals(2, user.getQuestsStarted()),
			() -> assertFalse(user.isLoggedIn()),
			() -> assertFalse(user.getActivities().isEmpty()),
			() -> assertFalse(user.getStats().isEmpty()),
			() -> assertNull(user.getStat(null))
		);
	}

	@Test
	public void parseUserLeaderboards() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_sethii.json")));
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_los.json")));

		Player user1 = rs.getUser("Sethii").completeGet();
		Player user2 = rs.getUser("Los").completeGet();

		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Sethii", user1.getLeaderboardUrl()),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Sethii&user2=Los", user1.getLeaderboardUrl("Los")),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Sethii&user2=Los", user1.getLeaderboardUrl(user2.getUsername())),
			() -> assertEquals("http://services.runescape.com/m=hiscore/compare?user1=Sethii", user1.getLeaderboardUrl(user1.getUsername()))
		);
	}

	@Test
	public void parseUsersSkills() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_sethii.json")));

		Player user = rs.getUser("Sethii").completeGet();
		PlayerStat stat = user.getStat(Skill.SLAYER);
		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals(125433, stat.getRank()),
			() -> assertEquals(Skill.SLAYER, stat.getSkill()),
			() -> assertEquals(100, stat.getLevel()),
			() -> assertEquals(100, stat.getVirtualLevel()),
			() -> assertEquals(14793609, stat.getXp())
		);
	}

	@Test
	public void parsePrivateUser() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_private.json")));

        assertThrows(FriendlyException.class, () ->
            rs.getUser("Zezima").complete()
        );
	}

	@Test
	public void userDoesntExist() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_no-profile.json")));

		assertThrows(FriendlyException.class, () ->
			rs.getUser("random user that doesn't exist").complete()
		);
	}

	@Test
	public void parseActivity() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("profile_sethii.json")));

		Activity activity = rs.getUser("Sethii").completeGet().getActivities().get(0);
		assertAll("Testing if Parsing RuneScape Player Activity Correctly",
			() -> assertEquals(1548363000000L, activity.getDate().getTime()),
			() -> assertEquals("I levelled my  Farming skill, I am now level 66.", activity.getDetails()),
			() -> assertEquals("Levelled up Farming.", activity.getText())
		);
	}

	@Test
	public void parseQuest() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("quests_sethii.json")));

		List<QuestStats> quests = rs.getQuestStatuses("Sethii").completeGet();
		QuestStats stats = quests.stream().filter(o -> o.getStatus() == CompletionStatus.STARTED).findFirst().get();

		assertAll("Testing if Parsing RuneScape Player Quest Status",
			() -> assertEquals("Abyss (miniquest)", stats.getTitle()),
			() -> assertEquals(Difficulty.INTERMEDIATE, stats.getDifficulty()),
			() -> assertEquals(0, stats.getQuestPoints()),
			() -> assertEquals(CompletionStatus.STARTED, stats.getStatus()),
			() -> assertTrue(stats.isMembers()),
			() -> assertTrue(stats.isUserEligible())
		);
	}

	@Test
	public void sortQuests() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("quests_sethii.json")));

		List<QuestStats> quests = rs.getQuestStatuses("Sethii").completeGet();
		Collections.sort(quests);

		QuestStats stats = quests.get(0);
		assertAll("Testing if sorting quests alphabetically correctly.",
			() -> assertEquals("'Phite Club", stats.getTitle()),
			() -> assertEquals(Difficulty.MASTER, stats.getDifficulty()),
			() -> assertEquals(1, stats.getQuestPoints()),
			() -> assertEquals(CompletionStatus.NOT_STARTED, stats.getStatus()),
			() -> assertTrue(stats.isMembers()),
			() -> assertFalse(stats.isUserEligible())
		);
	}

	@Test
	public void parseNoQuests() throws IOException {
		server.enqueue(new MockResponse().setBody(TestUtils.read("quests_private.json")));

		Optional<List<QuestStats>> quests = rs.getQuestStatuses("Zezima").complete();
		assertTrue(quests.isEmpty());
	}

	@Test
	public void levelToXp() {
		assertAll("Level to XP",
			() -> assertEquals(0, RuneScape.parseLevelAsXp(1)),
			() -> assertEquals(1154, RuneScape.parseLevelAsXp(10)),
			() -> assertEquals(4470, RuneScape.parseLevelAsXp(20)),
			() -> assertEquals(13363, RuneScape.parseLevelAsXp(30)),
			() -> assertEquals(101333, RuneScape.parseLevelAsXp(50)),
			() -> assertEquals(737627, RuneScape.parseLevelAsXp(70)),
			() -> assertEquals(1986068, RuneScape.parseLevelAsXp(80)),
			() -> assertEquals(13034431, RuneScape.parseLevelAsXp(99)),
			() -> assertEquals(14391160, RuneScape.parseLevelAsXp(100)),
			() -> assertEquals(104273167, RuneScape.parseLevelAsXp(120)),
			() -> assertEquals(188884740, RuneScape.parseLevelAsXp(126))
		);
	}

	@Test
	public void xpToLevel() {
		assertAll("XP to Level",
			() -> assertEquals(1, RuneScape.parseXpAsLevel(0)),
			() -> assertEquals(10, RuneScape.parseXpAsLevel(1154)),
			() -> assertEquals(20, RuneScape.parseXpAsLevel(4470)),
			() -> assertEquals(30, RuneScape.parseXpAsLevel(13363)),
			() -> assertEquals(50, RuneScape.parseXpAsLevel(101333)),
			() -> assertEquals(70, RuneScape.parseXpAsLevel(737627)),
			() -> assertEquals(90, RuneScape.parseXpAsLevel(5346332)),
			() -> assertEquals(99, RuneScape.parseXpAsLevel(13034431)),
			() -> assertEquals(100, RuneScape.parseXpAsLevel(14391160)),
			() -> assertEquals(120, RuneScape.parseXpAsLevel(104273167)),
			() -> assertEquals(126, RuneScape.parseXpAsLevel(188884740))
		);
	}

	@Test
	public void xpToLevelNonPrecise() {
		assertAll("XP to Level with Non-Precise Amounts",
			() -> assertEquals(1, RuneScape.parseXpAsLevel(1)),
			() -> assertEquals(1, RuneScape.parseXpAsLevel(50)),
			() -> assertEquals(1, RuneScape.parseXpAsLevel(82)),
			() -> assertEquals(36, RuneScape.parseXpAsLevel(25000)),
			() -> assertEquals(65, RuneScape.parseXpAsLevel(471000)),
			() -> assertEquals(98, RuneScape.parseXpAsLevel(13034430))
		);
	}

	@Test
	public void levelTooHigh() {
		assertEquals(-1, RuneScape.parseLevelAsXp(Integer.MAX_VALUE));
	}

	@Test
	public void illegalLevelArguments() {
		assertAll("Exceptions from Illegal Level Arguments",
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseLevelAsXp(0)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseLevelAsXp(-1)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseLevelAsXp(-99)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseLevelAsXp(-126)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseLevelAsXp(-999)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseLevelAsXp(Integer.MIN_VALUE))
		);
	}

	@Test
	public void illegalXpArguments() {
		assertAll("Exceptions from Illegal XP Arguments",
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseXpAsLevel(-1)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseXpAsLevel(-1000)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseXpAsLevel(-999999)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseXpAsLevel(-2141724413)),
			() -> assertThrows(IllegalArgumentException.class, () -> RuneScape.parseXpAsLevel(Long.MIN_VALUE))
		);
	}

	@Test
	public void xpToLevelLoopTest() {
		assertTimeout(ofSeconds(30), () -> RuneScape.parseXpAsLevel(Long.MAX_VALUE));
	}

	@Test
	public void skill() {
		Skill skill = Skill.SLAYER;
		assertAll("All Skill Values are Valid",
			() -> assertEquals(18, skill.getId()),
			() -> assertEquals("Slayer", skill.getDisplay())
		);
	}

	@Test
	public void questDifficultyId() {
		assertEquals(250, Difficulty.SPECIAL.getId());
	}

	@Test
	public void itemCategoryId() {
		assertEquals(1, ItemCategory.AMMO.getId());
	}

	@Test
	public void equipmentId() {
		assertEquals(13, Equipment.AMMUNITION.getId());
	}
}
