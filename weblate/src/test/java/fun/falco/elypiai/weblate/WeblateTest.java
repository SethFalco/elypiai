/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.weblate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import fun.falco.elypiai.weblate.models.LanguageStatistics;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.3
 */
public class WeblateTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static Weblate weblate;

    @BeforeEach
    public void beforeEach() {
        weblate = new Weblate("Fake API Key", serverExtension.getRequestUrl());
    }

    @Test
    public void createUnauthenticatedInstance() {
        assertDoesNotThrow(() -> new Weblate());
    }

    @Test
    public void createAuthenticatedInstance() {
        assertDoesNotThrow(() -> new Weblate("{FAKE_API_KEY}"));
    }

    @WebServerTest("project-languages.json")
    public void testParseProjectLanguages() {
        List<LanguageStatistics> l = weblate.getProjectLanguageStatistics("alexis").blockingGet();
        assertEquals(8, l.size());
    }

    @WebServerTest("project-languages.json")
    public void testParseProjectLanguageStatistic() {
        List<LanguageStatistics> l = weblate.getProjectLanguageStatistics("alexis").blockingGet();
        LanguageStatistics s = l.get(0);

        assertAll("Assert that all properties match.",
            () -> assertEquals("Bosnian", s.getLanguageName()),
            () -> assertEquals("bs", s.getLanguageCode()),
            () -> assertEquals(688, s.getTotalStrings()),
            () -> assertEquals(273, s.getTranslatedStrings()),
            () -> assertEquals(39.6, s.getTranslatedStringsPercentage()),
            () -> assertEquals(3192, s.getTotalWords()),
            () -> assertEquals(1516, s.getTranslatedWords()),
            () -> assertEquals(47.4, s.getTranslatedWordsPercentage()),
            () -> assertEquals(18008, s.getTotalCharacters()),
            () -> assertEquals(8622, s.getTranslatedCharacters()),
            () -> assertEquals(47.8, s.getTranslatedCharactersPercentage())
        );
    }
}
