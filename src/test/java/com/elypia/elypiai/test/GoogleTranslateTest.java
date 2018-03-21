package com.elypia.elypiai.test;

import com.elypia.elypiai.utils.Country;
import com.elypia.elypiai.utils.Language;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTranslateTest {

    @Test
    public void countryTest() {
        Country country = Country.FRANCE;
        Language language = Language.ENGLISH;

        assertEquals(country, Country.FRANCE);
        assertEquals(language, Language.ENGLISH);
    }
}
