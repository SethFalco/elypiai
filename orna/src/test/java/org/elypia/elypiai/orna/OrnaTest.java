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

package org.elypia.elypiai.orna;

import org.elypia.elypiai.orna.data.*;
import org.elypia.elypiai.orna.entities.*;
import org.elypia.webservertestbed.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class OrnaTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static Orna orna;

    @BeforeEach
    public void beforeEach() {
        orna = new Orna(serverExtension.getRequestUrl());
    }

    @Test
    public void testOrna() {
        assertDoesNotThrow(() -> new Orna());
    }

    @WebServerTest("all_monsters.json")
    public void testOrnaAllMonsters() {
        List<Monster> monsters = orna.getMonsters().blockingGet();
        Monster monster = monsters.get(0);

        assertAll("Check values of Orna Monster.",
            () -> assertEquals(230, monster.getId()),
            () -> assertEquals("Bandit", monster.getName()),
            () -> assertEquals(1, monster.getTier()),
            () -> assertFalse(monster.isBoss()),
            () -> assertEquals("monsters/bandit.png", monster.getImageUrl()),
            () -> assertEquals("https://orna.guide/static/orna/img/monsters/bandit.png", monster.getFullImageUrl())
        );
    }

    @WebServerTest("all_items.json")
    public void testAllItems() {
        List<Item> items = orna.getItems().blockingGet();
        Item item = items.get(0);

        assertAll("Check values of Orna Item.",
            () -> assertEquals("Abyssal Armor", item.getName()),
            () -> assertEquals(605, item.getId()),
            () -> assertEquals("Heavy armor crafted by Apollyon, the destructive exiled Balor mystic. Apollyon sought to destroy lands to stop the falling, but was exiled for his unorthodox approaches.", item.getDescription()),
            () -> assertEquals(ItemType.ARMOR, item.getType()),
            () -> assertEquals(9, item.getTier()),
            () -> assertTrue(item.isBoss()),
            () -> assertEquals("armor/abyssal_armor.png", item.getImageUrl()),
            () -> assertEquals(Element.ARCANE, item.getElement())
        );
    }
}
