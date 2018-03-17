package com.elypia.elypiai.test;

import com.elypia.elypiai.yugioh.YuGiOhCard;
import com.elypia.elypiai.yugioh.data.YGOCardType;
import com.elypia.elypiai.yugioh.data.YGOFamily;
import com.elypia.elypiai.yugioh.data.YGOMonsterType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YuGiOhTest {

    @Test
    public void parseCardData() {
        String json = "{\"name\":\"Dark Magician\",\"text\":\"The ultimate wizard in terms of attack and defense.\",\"card_type\":\"monster\",\"type\":\"Spellcaster / Normal\",\"family\":\"dark\",\"atk\":2500,\"def\":2100,\"level\":7,\"property\":null}";
        JSONObject object = new JSONObject(json);
        YuGiOhCard card = new YuGiOhCard(object);

        assertAll("Assert Yu-Gi-Oh! Card Data is Parsed",
            () -> assertEquals("Dark Magician", card.getName()),
            () -> assertEquals("Dark Magician", card.getName()),
            () -> assertEquals("The ultimate wizard in terms of attack and defense.", card.getText()),
            () -> assertEquals(YGOCardType.MONSTER, card.getCardType()),
            () -> assertEquals(YGOMonsterType.SPELLCASTER, card.getTypes()),
            () -> assertEquals(YGOFamily.DARK, card.getFamily()),
            () -> assertEquals(2500, card.getAttack()),
            () -> assertEquals(2100, card.getDefense()),
            () -> assertEquals(7, card.getLevel())
        );
    }
}
