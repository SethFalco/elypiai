package com.elypia.elypiai.test.twitch;

import com.elypia.elypiai.common.core.data.AuthenticationType;
import com.elypia.elypiai.common.test.TestUtils;
import com.elypia.elypiai.test.twitch.impl.TwitchListenerTest;
import com.elypia.elypiai.twitch.Twitch;
import com.elypia.elypiai.twitch.notifier.TwitchNotifier;
import com.elypia.webhooker.WebHooker;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;
import spark.Spark;

import java.io.IOException;
import java.net.URL;

public class TwitchNotifierTest {

    private static MockWebServer server;
    private static WebHooker hooker;
    private static TwitchListenerTest listener;
    private static Twitch twitch;
    private static TwitchNotifier notifier;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        server.enqueue(new MockResponse().setBody(TestUtils.read("token_bearer.json")));

        hooker = new WebHooker("http://localhost:4567/:uuid");
        Spark.awaitInitialization();

        URL authUrl = new URL("http://localhost:" + server.getPort());
        URL baseUrl = new URL("http://localhost:" + server.getPort());
        String clientId = "uo6dggojyb8d6soh92zknwmi5ej1q2";
        String clientSecret = "0123456789abcdefghijABCDEFGHIJ";
        AuthenticationType authType = AuthenticationType.BEARER;
        twitch = new Twitch(authUrl, baseUrl, clientId, clientSecret, authType);

        listener = new TwitchListenerTest();

        notifier = new TwitchNotifier(twitch, hooker);
        notifier.addListeners(listener);
    }

    @AfterEach
    public void afterEach() throws IOException {
        server.close();
        hooker.close();
        Spark.awaitStop();
    }


}
