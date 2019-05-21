package com.elypia.elypiai.yugioh.test;

import com.elypia.elypiai.yugioh.MagicCard;
import com.elypia.elypiai.yugioh.Monster;
import com.elypia.elypiai.yugioh.YuGiOh;
import com.elypia.elypiai.yugioh.data.*;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class YuGiOhTest {

    private static YuGiOh yugioh;

    private static MockWebServer server;
    private static InputStream stream;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();

        yugioh = new YuGiOh(new URL("http://localhost:" + server.getPort()));
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();

        if (stream != null)
            stream.close();
    }

    @Test
    public void createNormalInstance() {
        YuGiOh ygo = new YuGiOh();
        assertNotNull(ygo);
    }

    @Test
    public void parseCardData() throws IOException {
        stream = this.getClass().getResourceAsStream("/GET_card_data_0.json");
        server.enqueue(new MockResponse().setBody(new String(stream.readAllBytes())));

        Monster card = (Monster)yugioh.getCard("Dark Magician").complete();
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
        stream = this.getClass().getResourceAsStream("/GET_card_data_1.json");
        server.enqueue(new MockResponse().setBody(new String(stream.readAllBytes())));

        Monster card = (Monster)yugioh.getCard("Dark Magician Girl").complete();
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
        stream = this.getClass().getResourceAsStream("/GET_card_data_2.json");
        server.enqueue(new MockResponse().setBody(new String(stream.readAllBytes())));

        MagicCard card = (MagicCard)yugioh.getCard("Pot of Greed").complete();
        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Pot of Greed", card.getName()),
            () -> assertEquals("Draw 2 cards.", card.getText()),
            () -> assertEquals(CardType.SPELL, card.getType()),
            () -> assertEquals(MagicType.NORMAL, card.getProperty())
        );
    }

    @Test
    public void parseMirrorForce() throws IOException {
        stream = this.getClass().getResourceAsStream("/GET_card_data_3.json");
        server.enqueue(new MockResponse().setBody(new String(stream.readAllBytes())));

        MagicCard card = (MagicCard) yugioh.getCard("Mirror Force").complete();
        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Mirror Force", card.getName()),
            () -> assertEquals("When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", card.getText()),
            () -> assertEquals(CardType.TRAP, card.getType()),
            () -> assertEquals(MagicType.NORMAL, card.getProperty())
        );
    }

    @Test
    public void parseInvalidCard() throws IOException {
        stream = this.getClass().getResourceAsStream("/GET_card_data_4.json");
        server.enqueue(new MockResponse().setBody(new String(stream.readAllBytes())));

        TradingCard card = yugioh.getCard("CardThatDoesn'tExist").complete();
        assertNull(card);
    }

    @Test
    public void parseEnum() {
        assertNull(Race.get("testing nothing"));
        assertNull(MonsterType.get("testing nothing"));
    }
}
