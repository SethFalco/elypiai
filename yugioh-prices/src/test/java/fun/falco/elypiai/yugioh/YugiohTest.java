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

package fun.falco.elypiai.yugioh;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import fun.falco.elypiai.yugioh.data.Attribute;
import fun.falco.elypiai.yugioh.data.CardType;
import fun.falco.elypiai.yugioh.data.MagicType;
import fun.falco.elypiai.yugioh.data.MonsterType;
import fun.falco.elypiai.yugioh.data.Race;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class YugiohTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static Yugioh yugioh;

    @BeforeEach
    public void beforeEach() {
        yugioh = new Yugioh(serverExtension.getRequestUrl());
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow(() -> new Yugioh());
    }

    @WebServerTest("dark-magician.json")
    public void parseCardData() {
        Monster card = (Monster) yugioh.getCard("Dark Magician").blockingGet();

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

    @WebServerTest("dark-magician-girl.json")
    public void parseDarkMagianGirl() {
        Monster card = (Monster) yugioh.getCard("Dark Magician Girl").blockingGet();

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

    @WebServerTest("pot-of-greed.json")
    public void parsePotOfGreed() {
        MagicCard card = (MagicCard) yugioh.getCard("Pot of Greed").blockingGet();

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Pot of Greed", card.getName()),
            () -> assertEquals("Draw 2 cards.", card.getText()),
            () -> assertEquals(CardType.SPELL, card.getType()),
            () -> assertEquals(MagicType.NORMAL, card.getProperty())
        );
    }

    @WebServerTest("mirror-force.json")
    public void parseMirrorForce() {
        MagicCard card = (MagicCard) yugioh.getCard("Mirror Force").blockingGet();

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Mirror Force", card.getName()),
            () -> assertEquals("When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", card.getText()),
            () -> assertEquals(CardType.TRAP, card.getType()),
            () -> assertEquals(MagicType.NORMAL, card.getProperty())
        );
    }

    @WebServerTest("does-not-exist.json")
    public void parseInvalidCard() {
        assertTrue(yugioh.getCard("CardThatDoesn'tExist").isEmpty().blockingGet());
    }

    @Test
    public void parseEnum() {
        assertEquals(Race.UNKNOWN, Race.get("testing nothing"));
        assertEquals(MonsterType.UNKNOWN, MonsterType.get("testing nothing"));
    }
}
