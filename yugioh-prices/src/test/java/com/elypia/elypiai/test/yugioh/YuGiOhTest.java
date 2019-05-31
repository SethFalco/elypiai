package com.elypia.elypiai.test.yugioh;

import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.yugioh.MagicCard;
import com.elypia.elypiai.yugioh.Monster;
import com.elypia.elypiai.yugioh.TradingCard;
import com.elypia.elypiai.yugioh.YuGiOh;
import com.elypia.elypiai.yugioh.data.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
            .setBody(TestUtils.read("dark-magician"))
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
            .setBody(TestUtils.read("dark-magician-girl"))
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
            .setBody(TestUtils.read("pot-of-greed"))
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
            .setBody(TestUtils.read("mirror-force"))
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
            .setBody(TestUtils.read("does-not-exist"))
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
