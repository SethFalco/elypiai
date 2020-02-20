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

package org.elypia.elypiai.orna;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.test.TestUtils;
import org.elypia.elypiai.orna.entities.Monster;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class OrnaTest {

    private static MockWebServer server;
    private static Orna orna;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        orna = new Orna(new URL("http://localhost:" + server.getPort()));
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void testOrna() {
        assertDoesNotThrow(() -> new Orna());
    }

    @Test
    public void testOrnaAllMonsters() throws IOException {
        server.enqueue(new MockResponse().setBody(TestUtils.read("all_monsters.json")));
        List<Monster> monsters = orna.getMonsters().completeGet();
        Monster monster = monsters.get(0);

        assertAll("Check values of osu! player.",
            () -> assertEquals(230, monster.getId()),
            () -> assertEquals("Bandit", monster.getName()),
            () -> assertEquals(1, monster.getTier()),
            () -> assertFalse(monster.isBoss()),
            () -> assertEquals("monsters/bandit.png", monster.getImageUrl()),
            () -> assertEquals("https://orna.guide/static/orna/img/monsters/bandit.png", monster.getFullImageUrl())
        );
    }
}
