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

package org.elypia.elypiai.urbandictionary;

import org.elypia.webservertestbed.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class UrbanDictionaryTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static UrbanDictionary ud;

    @BeforeEach
    public void beforeEach() {
        ud = new UrbanDictionary(serverExtension.getRequestUrl());
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow(() -> new UrbanDictionary());
    }

    @WebServerTest("define_jen.json")
    public void parseResults() {
        DefineResult result = ud.getDefinitions("jen").blockingGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(0, result.getSounds().size()),
            () -> assertEquals(7768484, result.getDefinition(false).getDefinitionId()),
            () -> assertEquals(7768484, result.getDefinition().getDefinitionId()),
            () -> assertFalse(result.getDefinitions().isEmpty()),
            () -> assertEquals(0, result.getSounds(5).size())
        );
    }

    @WebServerTest("define_jen.json")
    public void parseDefinition() {
        Definition definition =  ud.getDefinitions("jen").blockingGet().getDefinitions(true).get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("Gorgeous, amazing, perfect everything. The girl who has always been my best friend, the girl who I should've been chasing this [whole time]. I love her. <[333]\r\n\r\n- [Sugarlips]", definition.getDefinitionBody()),
            () -> assertEquals("http://jen.urbanup.com/1859201", definition.getPermaLink()),
            () -> assertEquals(2274, definition.getThumbsUp()),
            () -> assertEquals("mikev²", definition.getAuthor()),
            () -> assertEquals("Jen", definition.getWord()),
            () -> assertEquals(1859201, definition.getDefinitionId()),
            () -> assertNull(definition.getCurrentVote()),
            () -> assertEquals("Jen was always my friend, then [best friend], then [lover], now [my love].", definition.getExample()),
            () -> assertEquals(1161, definition.getThumbsDown())
        );
    }

    @WebServerTest("define_fuck.json")
    public void parseResultsFuck() {
        DefineResult result = ud.getDefinitions("fuck").blockingGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertFalse(result.getSounds().isEmpty()),
            () -> assertFalse(result.getDefinitions().isEmpty()),
            () -> assertEquals(5, result.getSounds(5).size())
        );
    }

    @WebServerTest("define_life.json")
    public void parseDefinitionWithNoExample() {
        Definition definition =  ud.getDefinitions("life").blockingGet().getDefinitions(true).get(0);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("A sexually-transmitted, [terminal disease].", definition.getDefinitionBody()),
            () -> assertEquals("http://life.urbanup.com/139509", definition.getPermaLink()),
            () -> assertEquals(26417, definition.getThumbsUp()),
            () -> assertEquals("Anonymous", definition.getAuthor()),
            () -> assertEquals("life", definition.getWord()),
            () -> assertEquals(139509, definition.getDefinitionId()),
            () -> assertNull(definition.getCurrentVote()),
            () -> assertNull(definition.getExample()),
            () -> assertEquals(1958, definition.getThumbsDown())
        );
    }

    @WebServerTest("define_no-definitions.json")
    public void parseNoResults() {
        DefineResult result = ud.getDefinitions("iohwefiwhofhweohfowief").blockingGet();

        assertAll("Ensure Parsing No Result Data Correctly",
            () -> assertTrue(result.getSounds().isEmpty())
        );
    }

    @WebServerTest("define_defid_139509.json")
    public void testDefineByIdWithResult() {
        Definition definition = ud.getDefinitionById(139509).blockingGet();

        String expected = "A sexually-transmitted, [terminal disease].";
        String actual = definition.getDefinitionBody();

        assertEquals(expected, actual);
    }

    @WebServerTest("define_defid_no-results.json")
    public void testDefineByIdWithNoResults() {
        assertTrue(ud.getDefinitionById(2147000000).isEmpty().blockingGet());
    }
}
