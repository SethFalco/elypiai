package com.elypia.elypiai.test;

import com.elypia.elypiai.yugioh.*;
import com.elypia.elypiai.yugioh.data.*;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class YuGiOhTest {

    private static MockWebServer server;
    private static YuGiOh yugioh;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        yugioh = new YuGiOh("http://localhost:" + server.getPort());
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
    }

    @Test
    public void createNormalInstance() {
        YuGiOh ygo = new YuGiOh();
        assertNotNull(ygo);
    }

    @Test
    public void parseCardData() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"data\":{\"name\":\"Dark Magician\",\"text\":\"The ultimate wizard in terms of attack and defense.\",\"card_type\":\"monster\",\"type\":\"Spellcaster / Normal\",\"family\":\"dark\",\"atk\":2500,\"def\":2100,\"level\":7,\"property\":null}}"));

        MonsterCard card = yugioh.getCard("Dark Magician").complete().asMonster();
        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Dark Magician", card.getName()),
            () -> assertEquals("The ultimate wizard in terms of attack and defense.", card.getText()),
            () -> assertEquals(CardType.MONSTER, card.getType()),
            () -> assertTrue(card.getMonsterTypes().contains(MonsterType.NORMAL)),
            () -> assertEquals(MonsterAttribute.SPELLCASTER, card.getAttribute()),
            () -> assertEquals(MonsterFamily.DARK, card.getFamily()),
            () -> assertEquals(2500, card.getAttack()),
            () -> assertEquals(2100, card.getDefense()),
            () -> assertEquals(7, card.getLevel()),
            () -> assertTrue(card.is(CardType.MONSTER))
        );
    }

    @Test
    public void parseDarkMagianGirl() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"data\":{\"name\":\"Dark Magician Girl\",\"text\":\"Gains 300 ATK for every \\\"Dark Magician\\\" or \\\"Magician of Black Chaos\\\" in the GY.\",\"card_type\":\"monster\",\"type\":\"Spellcaster / Effect\",\"family\":\"dark\",\"atk\":2000,\"def\":1700,\"level\":6,\"property\":null}}"));

        MonsterCard card = yugioh.getCard("Dark Magician Girl").complete().asMonster();
        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Dark Magician Girl", card.getName()),
            () -> assertEquals("Gains 300 ATK for every \"Dark Magician\" or \"Magician of Black Chaos\" in the GY.", card.getText()),
            () -> assertEquals(CardType.MONSTER, card.getType()),
            () -> assertEquals(MonsterAttribute.SPELLCASTER, card.getAttribute()),
            () -> assertTrue(card.getMonsterTypes().contains(MonsterType.EFFECT)),
            () -> assertEquals(MonsterFamily.DARK, card.getFamily()),
            () -> assertEquals(2000, card.getAttack()),
            () -> assertEquals(1700, card.getDefense()),
            () -> assertEquals(6, card.getLevel()),
            () -> assertTrue(card.is(CardType.MONSTER))
        );
    }

    @Test
    public void parsePotOfGreed() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"data\":{\"name\":\"Pot of Greed\",\"text\":\"Draw 2 cards.\",\"card_type\":\"spell\",\"type\":null,\"family\":null,\"atk\":null,\"def\":null,\"level\":null,\"property\":\"Normal\"}}"));

        SpellCard card = yugioh.getCard("Pot of Greed").complete().asSpell();
        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Pot of Greed", card.getName()),
            () -> assertEquals("Draw 2 cards.", card.getText()),
            () -> assertEquals(CardType.SPELL, card.getType()),
            () -> assertEquals(SpellProperty.NORMAL, card.getProperty()),
            () -> assertTrue(card.is(CardType.SPELL))
        );
    }

    @Test
    public void parseMirrorForce() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"data\":{\"name\":\"Mirror Force\",\"text\":\"When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.\",\"card_type\":\"trap\",\"type\":null,\"family\":null,\"atk\":null,\"def\":null,\"level\":null,\"property\":\"Normal\"}}"));

        TrapCard card = yugioh.getCard("Mirror Force").complete().asTrap();
        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Mirror Force", card.getName()),
            () -> assertEquals("When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", card.getText()),
            () -> assertEquals(CardType.TRAP, card.getType()),
            () -> assertEquals(TrapProperty.NORMAL, card.getProperty()),
            () -> assertTrue(card.is(CardType.TRAP))
        );
    }

    @Test
    public void parseInvalidCard() throws IOException {
        server.enqueue(new MockResponse().setBody("{\"status\":\"fail\",\"message\":\"No cards matching this name were found in our database.\"}"));

        TradingCard card = yugioh.getCard("CardThatDoesn'tExist").complete();
        assertNull(card);
    }

    @Test
    public void parseEnum() {
        assertNull(MonsterAttribute.get("testing nothing"));
        assertNull(MonsterType.get("testing nothing"));
    }
}
