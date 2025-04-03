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

package fun.falco.elypiai.urbandictionary;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class UrbanDictionaryTest {

    private static UrbanDictionary ud;

    @BeforeEach
    public void beforeEach() {
        ud = new UrbanDictionary();
    }

    @Test
    public void parseResults() {
        DefineResult result = ud.getDefinitions("foobar").blockingGet();
        assertAll("Assert that we fetch and pass data without throwing",
            () -> assertNotNull(result.getSounds().size()),
            () -> assertNotNull(result.getDefinition(false).getDefinitionId()),
            () -> assertNotNull(result.getDefinition().getDefinitionId()),
            () -> assertFalse(result.getDefinitions().isEmpty()),
            () -> assertNotNull(result.getSounds(5).size())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"xkcd", "uwu"})
    public void parseDefinition(final String term) {
        Definition definition = ud.getDefinitions(term).blockingGet().getDefinitions(true).get(0);

        assertAll("Assert that required fields in definition are not null",
            () -> assertNotNull(definition.getDefinitionBody()),
            () -> assertNotNull(definition.getPermaLink()),
            () -> assertNotNull(definition.getThumbsUp()),
            () -> assertNotNull(definition.getAuthor()),
            () -> assertNotNull(definition.getWord()),
            () -> assertNotNull(definition.getDefinitionId()),
            () -> assertNotNull(definition.getExample()),
            () -> assertNotNull(definition.getThumbsDown())
        );
    }

    @Test
    public void parseNoResults() {
        DefineResult result = ud.getDefinitions("ifsomeoneaddsadefinitionforthisiwillbecomeveryupset").blockingGet();

        assertAll("Assert that that no results is handled correctly",
            () -> assertFalse(result.hasDefinitions()),
            () -> assertTrue(result.getSounds().isEmpty())
        );
    }

    @Test
    public void testDefineByIdWithResult() {
        Definition definition = ud.getDefinitionById(11483496).blockingGet();

        String expected = "Basic info:\r\n[owo] is an emoticon used in chat rooms similar to [o.o] but the 'w' Is supposed to make it cute.\n\nWhat does it mean?\r\nowo means, [a blank] stare, but in a cute way.\n\nOther similar emote(s).\r\n[OwO] o.o";
        String actual = definition.getDefinitionBody();

        assertEquals(expected, actual);
    }

    @Test
    public void testDefineByIdWithNoResults() {
        assertTrue(ud.getDefinitionById(2147000000).isEmpty().blockingGet());
    }
}
