package com.elypia.elypiai.test;

import com.elypia.elypiai.utils.Regex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexTest {

    @Test
    public void testValidNumbers() {
        assertAll("Test Valid Numbers",
            () -> assertTrue(Regex.NUMBER.matches("0")),
            () -> assertTrue(Regex.NUMBER.matches("1")),
            () -> assertTrue(Regex.NUMBER.matches("-1")),
            () -> assertTrue(Regex.NUMBER.matches("99")),
            () -> assertTrue(Regex.NUMBER.matches("10294")),
            () -> assertTrue(Regex.NUMBER.matches("54367547")),
            () -> assertTrue(Regex.NUMBER.matches("858548648964745")),
            () -> assertTrue(Regex.NUMBER.matches("7457335857868537525")),
            () -> assertTrue(Regex.NUMBER.matches("-456265626")),
            () -> assertTrue(Regex.NUMBER.matches("-894793759759205750157865")),
            () -> assertTrue(Regex.NUMBER.matches("5981790712309578291587957")),
            () -> assertTrue(Regex.NUMBER.matches("-1223.5325235235")),
            () -> assertTrue(Regex.NUMBER.matches("2342525.6346363")),
            () -> assertTrue(Regex.NUMBER.matches(".4234235")),
            () -> assertTrue(Regex.NUMBER.matches("23424.5325525"))
        );
    }

    public void testInvalidNumber() {
        assertAll("Test Invalid Numbers",
            () -> assertFalse(Regex.NUMBER.matches("1000.")),
            () -> assertFalse(Regex.NUMBER.matches("")),
            () -> assertFalse(Regex.NUMBER.matches("    ")),
            () -> assertFalse(Regex.NUMBER.matches("Hello world!")),
            () -> assertFalse(Regex.NUMBER.matches("3993493.p")),
            () -> assertFalse(Regex.NUMBER.matches("a.a")),
            () -> assertFalse(Regex.NUMBER.matches("--123")),
            () -> assertFalse(Regex.NUMBER.matches("12312.23424.53252")),
            () -> assertFalse(Regex.NUMBER.matches("123,,456")),
            () -> assertFalse(Regex.NUMBER.matches("1234,567"))
        );
    }
}
