package com.elypia.elypiai.test;

import com.elypia.elypiai.steam.SteamUser;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SteamTest {

    @Test
    public void parseSteamUser() {
        String json = "{\"steamid\":\"76561198085657484\",\"communityvisibilitystate\":3,\"profilestate\":1,\"personaname\":\"Mr. Seth X3\",\"lastlogoff\":1521361398,\"commentpermission\":1,\"profileurl\":\"http://steamcommunity.com/id/SethsUtopia/\",\"avatar\":\"https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de.jpg\",\"avatarmedium\":\"https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_medium.jpg\",\"avatarfull\":\"https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_full.jpg\",\"personastate\":1,\"realname\":\"Seth\",\"primaryclanid\":\"103582791429521408\",\"timecreated\":1362733966,\"personastateflags\":0,\"loccountrycode\":\"GB\",\"locstatecode\":\"A5\",\"loccityid\":16536}";
        JSONObject object = new JSONObject(json);
        SteamUser user = new SteamUser(null, object);

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals(76561198085657484L, user.getId()),
            () -> assertEquals(false, user.isPrivate()),
            () -> assertEquals("Mr. Seth X3", user.getUsername()),
            () -> assertEquals("Seth", user.getRealName()),
            () -> assertEquals("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/da/da2fc3f10df50bc529f3cf3b4898eb186595e7de_full.jpg", user.getAvatar())
        );
    }
}
