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

package org.elypia.elypiai.yugioh;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.test.TestUtils;
import org.elypia.elypiai.yugioh.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class YuGiOhTest {

    private static MockWebServer server;
    private static YuGiOh yugioh;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        yugioh = new YuGiOh(new URL("http://localhost:" + server.getPort()));
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow((Executable)YuGiOh::new);
    }

    @Test
    public void parseCardData() throws IOException {
        server.enqueue(new MockResponse()
            .setBody(TestUtils.read("dark-magician.json"))
        );
        Monster card = (Monster)yugioh.getCard("Dark Magician").completeGet();

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Dark Magician", card.getName()),
            () -> assertEquals("The ultimate wizard in terms of attack and defense.", card.getText()),
            () -> assertEquals(CardType.MONSTER, card.getType()),
            () -> assertTrue(card.getTypes().contains(MonsterType.NORMAL)),
            () -> assertEquals(Race.SPELLCASTER, card.getRace()),
            () -> assertEquals(Attribute.DARK, card.getAttribute()),
            () -> assertEquals(2500, card.getAttack()),
            () -> assertEquals(2100, card.getDefense()),
            () -> assertEquals(7, card.getLevel())
        );
    }

    @Test
    public void parseDarkMagianGirl() throws IOException {
        server.enqueue(new MockResponse()
            .setBody(TestUtils.read("dark-magician-girl.json"))
        );
        Monster card = (Monster)yugioh.getCard("Dark Magician Girl").completeGet();

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Dark Magician Girl", card.getName()),
            () -> assertEquals("Gains 300 ATK for every \"Dark Magician\" or \"Magician of Black Chaos\" in the GY.", card.getText()),
            () -> assertEquals(CardType.MONSTER, card.getType()),
            () -> assertEquals(Race.SPELLCASTER, card.getRace()),
            () -> assertTrue(card.getTypes().contains(MonsterType.EFFECT)),
            () -> assertEquals(Attribute.DARK, card.getAttribute()),
            () -> assertEquals(2000, card.getAttack()),
            () -> assertEquals(1700, card.getDefense()),
            () -> assertEquals(6, card.getLevel())
        );
    }

    @Test
    public void parsePotOfGreed() throws IOException {
        server.enqueue(new MockResponse()
            .setBody(TestUtils.read("pot-of-greed.json"))
        );
        MagicCard card = (MagicCard)yugioh.getCard("Pot of Greed").completeGet();

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Pot of Greed", card.getName()),
            () -> assertEquals("Draw 2 cards.", card.getText()),
            () -> assertEquals(CardType.SPELL, card.getType()),
            () -> assertEquals(MagicType.NORMAL, card.getProperty())
        );
    }

    @Test
    public void parseMirrorForce() throws IOException {
        server.enqueue(new MockResponse()
            .setBody(TestUtils.read("mirror-force.json"))
        );
        MagicCard card = (MagicCard)yugioh.getCard("Mirror Force").completeGet();

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Mirror Force", card.getName()),
            () -> assertEquals("When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", card.getText()),
            () -> assertEquals(CardType.TRAP, card.getType()),
            () -> assertEquals(MagicType.NORMAL, card.getProperty())
        );
    }

    @Test
    public void parseInvalidCard() throws IOException {
        server.enqueue(new MockResponse()
            .setBody(TestUtils.read("does-not-exist.json"))
        );
        Optional<TradingCard> card = yugioh.getCard("CardThatDoesn'tExist").complete();
        assertTrue(card.isEmpty());
    }

    @Test
    public void parseEnum() {
        assertEquals(Race.UNKNOWN, Race.get("testing nothing"));
        assertEquals(MonsterType.UNKNOWN, MonsterType.get("testing nothing"));
    }
}
