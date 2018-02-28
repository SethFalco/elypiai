package com.elypia.elypiai.test;

import com.elypia.elypiai.runescape.RuneScape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuneScapeTest {

	// Convert Level to XP

	@Test
	public void runescapeConvertLevel1() {
		long actual = RuneScape.convertLevelToXp(1, false);
		long expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel10() {
		long actual = RuneScape.convertLevelToXp(10, false);
		long expected = 1154;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel20() {
		long actual = RuneScape.convertLevelToXp(20, false);
		long expected = 4470;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel30() {
		long actual = RuneScape.convertLevelToXp(30, false);
		long expected = 13363;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel50() {
		long actual = RuneScape.convertLevelToXp(50, false);
		long expected = 101333;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel70() {
		long actual = RuneScape.convertLevelToXp(70, false);
		long expected = 737627;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel80() {
		long actual = RuneScape.convertLevelToXp(80, false);
		long expected = 1986068;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel99() {
		long actual = RuneScape.convertLevelToXp(99, false);
		long expected = 13034431;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel100() {
		long actual = RuneScape.convertLevelToXp(100, false);
		long expected = 14391160;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel120() {
		long actual = RuneScape.convertLevelToXp(120, false);
		long expected = 104273167;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel126() {
		long actual = RuneScape.convertLevelToXp(126, false);
		long expected = 188884740;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevelMax() {
		long actual = RuneScape.convertLevelToXp(Integer.MAX_VALUE, false);
		long expected = -1;
		assertEquals(expected, actual);
	}

	// XP to Level

	@Test
	public void runescapeConvertXp1() {
		long actual = RuneScape.convertXpToLevel(0, false);
		long expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp10() {
		long actual = RuneScape.convertXpToLevel(1154, false);
		long expected = 10;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp20() {
		long actual = RuneScape.convertXpToLevel(4470, false);
		long expected = 20;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp30() {
		long actual = RuneScape.convertXpToLevel(13363, false);
		long expected = 30;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp50() {
		long actual = RuneScape.convertXpToLevel(101333, false);
		long expected = 50;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp70() {
		long actual = RuneScape.convertXpToLevel(737627, false);
		long expected = 70;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp90() {
		long actual = RuneScape.convertXpToLevel(5346332, false);
		long expected = 90;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp99() {
		long actual = RuneScape.convertXpToLevel(13034431, false);
		long expected = 99;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp100() {
		long actual = RuneScape.convertXpToLevel(14391160, false);
		long expected = 100;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp120() {
		long actual = RuneScape.convertXpToLevel(104273167, false);
		long expected = 120;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp126() {
		long actual = RuneScape.convertXpToLevel(188884740, false);
		long expected = 126;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXpMax() {
		RuneScape.convertXpToLevel(Integer.MAX_VALUE, false);
	}
}
