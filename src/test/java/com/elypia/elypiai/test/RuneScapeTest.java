package com.elypia.elypiai.test;

import com.elypia.elypiai.runescape.RuneScape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuneScapeTest {

	//////////////////////////////////////////////////////////
	//					RUNESCAPE LEVEL TO XP				//
	//////////////////////////////////////////////////////////

	@Test
	public void runescapeConvertLevel1() {
		int actual = RuneScape.convertLevelToXp(1, false);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel10() {
		int actual = RuneScape.convertLevelToXp(10, false);
		int expected = 1154;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel20() {
		int actual = RuneScape.convertLevelToXp(20, false);
		int expected = 4470;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel30() {
		int actual = RuneScape.convertLevelToXp(30, false);
		int expected = 13363;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel50() {
		int actual = RuneScape.convertLevelToXp(50, false);
		int expected = 101333;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel70() {
		int actual = RuneScape.convertLevelToXp(70, false);
		int expected = 737627;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel80() {
		int actual = RuneScape.convertLevelToXp(80, false);
		int expected = 1986068;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel99() {
		int actual = RuneScape.convertLevelToXp(99, false);
		int expected = 13034431;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel100() {
		int actual = RuneScape.convertLevelToXp(100, false);
		int expected = 14391160;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel120() {
		int actual = RuneScape.convertLevelToXp(120, false);
		int expected = 104273167;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevel126() {
		int actual = RuneScape.convertLevelToXp(126, false);
		int expected = 188884740;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertLevelMax() {
		// Mainly to check for infinite loops!
		int actual = RuneScape.convertLevelToXp(Integer.MAX_VALUE, false);
		int expected = Integer.MAX_VALUE;
		assertEquals(expected, actual);
	}

	//////////////////////////////////////////////////////////
	//					RUNESCAPE XP TO LEVEL				//
	//////////////////////////////////////////////////////////

	@Test
	public void runescapeConvertXp1() {
		int actual = RuneScape.convertXpToLevel(0, false);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp10() {
		int actual = RuneScape.convertXpToLevel(1154, false);
		int expected = 10;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp20() {
		int actual = RuneScape.convertXpToLevel(4470, false);
		int expected = 20;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp30() {
		int actual = RuneScape.convertXpToLevel(13363, false);
		int expected = 30;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp50() {
		int actual = RuneScape.convertXpToLevel(101333, false);
		int expected = 50;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp70() {
		int actual = RuneScape.convertXpToLevel(737627, false);
		int expected = 70;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp90() {
		int actual = RuneScape.convertXpToLevel(5346332, false);
		int expected = 90;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp99() {
		int actual = RuneScape.convertXpToLevel(13034431, false);
		int expected = 99;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp100() {
		int actual = RuneScape.convertXpToLevel(14391160, false);
		int expected = 100;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp120() {
		int actual = RuneScape.convertXpToLevel(104273167, false);
		int expected = 120;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXp126() {
		int actual = RuneScape.convertXpToLevel(188884740, false);
		int expected = 126;
		assertEquals(expected, actual);
	}

	@Test
	public void runescapeConvertXpMax() {
		// Only checking if infinite loops.
		RuneScape.convertXpToLevel(Integer.MAX_VALUE, false);
	}
}
