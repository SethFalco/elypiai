/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.runescape;

import org.elypia.elypiai.runescape.data.*;
import org.elypia.retropia.core.exceptions.FriendlyException;
import org.elypia.webservertestbed.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.concurrent.*;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

public class RuneScapeTest {

	@RegisterExtension
	public static final WebServerExtension serverExtension = new WebServerExtension();

	private static RuneScape rs;

	@BeforeEach
	public void beforeEach() {
		rs = new RuneScape(serverExtension.getRequestUrl());
	}

	@Test
	public void normalInstance() {
		RuneScape rs = new RuneScape();
		assertNotNull(rs);
	}

	@WebServerTest("profile_sethii.json")
	public void parseUser() {
		Player user = rs.getUser("Sethii").blockingGet();

		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals("Sethii", user.getUsername()),
			() -> assertEquals(215938, user.getRank()),
			() -> assertEquals("https://services.runescape.com/m=hiscore/compare?user1=Sethii", user.getLeaderboardUrl()),
			() -> assertEquals("https://services.runescape.com/m=hiscore/compare?user1=Sethii&user2=Auri_Nimph", user.getLeaderboardUrl("Auri_Nimph")),
			() -> assertEquals("2,249", user.getTotalLevelFormatted()),
			() -> assertEquals(2249, user.getTotalLevel()),
			() -> assertEquals(187689300, user.getTotalXp()),
			() -> assertEquals("187,689,300", user.getTotalXpFormatted()),
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

	@WebServerTest({"profile_sethii.json", "profile_los.json"})
	public void parseUserLeaderboards() {
		Player user1 = rs.getUser("Sethii").blockingGet();
		Player user2 = rs.getUser("Los").blockingGet();

		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals("https://services.runescape.com/m=hiscore/compare?user1=Sethii", user1.getLeaderboardUrl()),
			() -> assertEquals("https://services.runescape.com/m=hiscore/compare?user1=Sethii&user2=Los", user1.getLeaderboardUrl("Los")),
			() -> assertEquals("https://services.runescape.com/m=hiscore/compare?user1=Sethii&user2=Los", user1.getLeaderboardUrl(user2.getUsername())),
			() -> assertEquals("https://services.runescape.com/m=hiscore/compare?user1=Sethii", user1.getLeaderboardUrl(user1.getUsername()))
		);
	}

	@WebServerTest("profile_sethii.json")
	public void parseUsersSkills() {
		Player user = rs.getUser("Sethii").blockingGet();
		PlayerStat stat = user.getStat(Skill.SLAYER);
		assertAll("Testing if Parsing RuneScape Player Correctly",
			() -> assertEquals(125433, stat.getRank()),
			() -> assertEquals(Skill.SLAYER, stat.getSkill()),
			() -> assertEquals(100, stat.getLevel()),
			() -> assertEquals(100, stat.getVirtualLevel()),
			() -> assertEquals(14793609, stat.getXp())
		);
	}

	@WebServerTest("profile_no-profile.json")
	public void parsePrivateUser() {
        assertThrows(FriendlyException.class, () ->
            rs.getUser("Zezima").blockingGet()
        );
	}

	@WebServerTest("profile_no-profile.json")
	public void userDoesntExist() {
		assertThrows(FriendlyException.class, () ->
			rs.getUser("random user that doesn't exist").blockingGet()
		);
	}

	@WebServerTest("profile_sethii.json")
	public void parseActivity() {
		Activity activity = rs.getUser("Sethii").blockingGet().getActivities().get(0);
		assertAll("Testing if Parsing RuneScape Player Activity Correctly",
			() -> assertEquals(1548363000000L, activity.getDate().getTime()),
			() -> assertEquals("I levelled my  Farming skill, I am now level 66.", activity.getDetails()),
			() -> assertEquals("Levelled up Farming.", activity.getText())
		);
	}

	@WebServerTest("quests_sethii.json")
	public void parseQuest() {
		QuestStatuses quests = rs.getQuestStatuses("Sethii").blockingGet();
		QuestStatus stats = quests.getQuestStatuses().stream().filter(o -> o.getStatus() == CompletionStatus.STARTED).findFirst().get();

		assertAll("Testing if Parsing RuneScape Player Quest Status",
			() -> assertEquals("Abyss (miniquest)", stats.getTitle()),
			() -> assertEquals(Difficulty.INTERMEDIATE, stats.getDifficulty()),
			() -> assertEquals(0, stats.getQuestPoints()),
			() -> assertEquals(CompletionStatus.STARTED, stats.getStatus()),
			() -> assertTrue(stats.isMembers()),
			() -> assertTrue(stats.isUserEligible())
		);
	}

	@WebServerTest("quests_sethii.json")
	public void sortQuests() {
		QuestStatuses quests = rs.getQuestStatuses("Sethii").blockingGet();
		List<QuestStatus> statuses = new ArrayList<>(quests.getQuestStatuses());
		Collections.sort(statuses);

		QuestStatus stats = statuses.get(0);
		assertAll("Testing if sorting quests alphabetically correctly.",
			() -> assertEquals("'Phite Club", stats.getTitle()),
			() -> assertEquals(Difficulty.MASTER, stats.getDifficulty()),
			() -> assertEquals(1, stats.getQuestPoints()),
			() -> assertEquals(CompletionStatus.NOT_STARTED, stats.getStatus()),
			() -> assertTrue(stats.isMembers()),
			() -> assertFalse(stats.isUserEligible())
		);
	}

	/**
	 * This is just a one off asyncronous task to test
	 * if handling friendly exceptions works.
	 *
	 * @throws InterruptedException If the timeout occurs before we get the value.
	 */
	@WebServerTest("profile_private.json")
	public void parsePrivateUserAsync() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);

		rs.getUser("Zezima").subscribe(
			o -> {
				fail("This should have produced an exception.");
				latch.countDown();
			},
			ex -> {
				assertTrue(ex instanceof FriendlyException);
				latch.countDown();
			}
		);

		latch.await(8, TimeUnit.SECONDS);
	}

	@WebServerTest("quests_private.json")
	public void parseNoQuests() {
		assertTrue(rs.getQuestStatuses("Zezima").isEmpty().blockingGet());
	}

	@Test
	public void levelToXp() {
		assertAll("Level to XP",
			() -> assertEquals(0, RuneScape.getXpFromLevel(1)),
			() -> assertEquals(1154, RuneScape.getXpFromLevel(10)),
			() -> assertEquals(4470, RuneScape.getXpFromLevel(20)),
			() -> assertEquals(13363, RuneScape.getXpFromLevel(30)),
			() -> assertEquals(101333, RuneScape.getXpFromLevel(50)),
			() -> assertEquals(737627, RuneScape.getXpFromLevel(70)),
			() -> assertEquals(1986068, RuneScape.getXpFromLevel(80)),
			() -> assertEquals(13034431, RuneScape.getXpFromLevel(99)),
			() -> assertEquals(14391160, RuneScape.getXpFromLevel(100)),
			() -> assertEquals(104273167, RuneScape.getXpFromLevel(120)),
			() -> assertEquals(188884740, RuneScape.getXpFromLevel(126))
		);
	}

	@Test
	public void xpToLevel() {
		assertAll("XP to Level",
			() -> assertEquals(1, RuneScape.getLevelFromXp(0)),
			() -> assertEquals(10, RuneScape.getLevelFromXp(1154)),
			() -> assertEquals(20, RuneScape.getLevelFromXp(4470)),
			() -> assertEquals(30, RuneScape.getLevelFromXp(13363)),
			() -> assertEquals(50, RuneScape.getLevelFromXp(101333)),
			() -> assertEquals(70, RuneScape.getLevelFromXp(737627)),
			() -> assertEquals(90, RuneScape.getLevelFromXp(5346332)),
			() -> assertEquals(99, RuneScape.getLevelFromXp(13034431)),
			() -> assertEquals(100, RuneScape.getLevelFromXp(14391160)),
			() -> assertEquals(120, RuneScape.getLevelFromXp(104273167)),
			() -> assertEquals(126, RuneScape.getLevelFromXp(188884740))
		);
	}

	@Test
	public void xpToLevelNonPrecise() {
		assertAll("XP to Level with Non-Precise Amounts",
			() -> assertEquals(1, RuneScape.getLevelFromXp(1)),
			() -> assertEquals(1, RuneScape.getLevelFromXp(50)),
			() -> assertEquals(1, RuneScape.getLevelFromXp(82)),
			() -> assertEquals(36, RuneScape.getLevelFromXp(25000)),
			() -> assertEquals(65, RuneScape.getLevelFromXp(471000)),
			() -> assertEquals(98, RuneScape.getLevelFromXp(13034430))
		);
	}

	@Test
	public void levelTooHigh() {
		assertEquals(-1, RuneScape.getXpFromLevel(Integer.MAX_VALUE));
	}

	@ParameterizedTest
    @ValueSource(ints = {0, -1, -99, -126, -999, Integer.MIN_VALUE})
	public void illegalLevelArguments(int level) {
        assertThrows(IllegalArgumentException.class, () -> RuneScape.getXpFromLevel(level));
	}

	@ParameterizedTest
    @ValueSource(longs = {-1, -1000, -999999, -2141724413, Long.MIN_VALUE})
	public void illegalXpArguments(long xp) {
        assertThrows(IllegalArgumentException.class, () -> RuneScape.getLevelFromXp(xp));
	}

    /**
     * Ensure we don't infinite loop when calling with large numbers.
     */
	@Test
	public void xpToLevelLoopTest() {
		assertTimeout(ofSeconds(30), () -> RuneScape.getLevelFromXp(Long.MAX_VALUE));
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
