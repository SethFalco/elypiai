package com.elypia.elypiai.test;

import com.elypia.elypiai.runescape.RuneScape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuneScapeTest {

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
		RuneScape.convertXpToLevel(Long.MAX_VALUE);
	}
}
