/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.nanowrimo;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.core.ex.FriendlyException;
import org.elypia.elypiai.common.test.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class NanowrimoTest {

    private static MockWebServer server;
    private static Nanowrimo nano;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        nano = new Nanowrimo(new URL("http://localhost:" + server.getPort()));
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void testNano() {
        assertDoesNotThrow((Executable)Nanowrimo::new);
    }

    @Test
    public void getUser() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("wc_jessicawillow123.xml")));
        Writer user = nano.getUser("JessicaWillow123", false).completeGet();

        assertAll("Try parse NaNoWriMo user.",
            () -> assertEquals("JessicaWillow123", user.getUsername()),
            () -> assertEquals(50117, user.getWordCount()),
            () -> assertEquals(2736048, user.getId()),
            () -> assertTrue(user.isWinner()),
            () -> assertEquals("https://nanowrimo.org/participants/JessicaWillow123", user.getUrl()),
            () -> assertTrue(user.getEntries().isEmpty())
        );
    }

    @Test
    public void getUserHistory() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("wch_jessicawillow123.xml")));
        Writer user = nano.getUser("JessicaWillow123", true).completeGet();

        assertAll("Try parse NaNoWriMo user with History.",
            () -> assertEquals("JessicaWillow123", user.getUsername()),
            () -> assertEquals(50117, user.getWordCount()),
            () -> assertEquals(2736048, user.getId()),
            () -> assertTrue(user.isWinner()),
            () -> assertFalse(user.getEntries().isEmpty())
        );
    }

    @Test
    public void getUserHistoryWordCountEntry() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("wch_jessicawillow123.xml")));
        WordCountEntry entry = nano.getUser("JessicaWillow123", true).completeGet().getEntries().get(0);

        assertAll("Try parse the first WordCountEntry from a NaNoWriMo user with history.",
            () -> assertEquals(2015, entry.getWordcount()),
            () -> assertEquals(1541030400000L, entry.getDate().getTime())
        );
    }

    @Test
    public void userNoNovel() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("wc_no-novel.xml")));

        assertThrows(FriendlyException.class, () ->
            nano.getUser("seth-x3").complete()
        );
    }

    @Test
    public void userNotExists() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("wc_randomuserthatdoesnotexist.xml")));

        assertThrows(FriendlyException.class, () ->
            nano.getUser("randomuserthatdoesnotexist").complete()
        );
    }
}
