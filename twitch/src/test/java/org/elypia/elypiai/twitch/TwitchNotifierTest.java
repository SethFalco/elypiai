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

package org.elypia.elypiai.twitch;

import okhttp3.mockwebserver.*;
import org.elypia.elypiai.common.core.data.AuthenticationType;
import org.elypia.elypiai.common.test.TestUtils;
import org.elypia.elypiai.twitch.entity.*;
import org.elypia.elypiai.twitch.impl.TwitchListenerTest;
import org.elypia.elypiai.twitch.notifier.TwitchNotifier;
import org.elypia.elypiai.twitch.notifier.event.*;
import org.elypia.webhooker.*;
import org.elypia.webhooker.controller.*;
import org.junit.jupiter.api.*;
import org.slf4j.*;
import spark.*;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class TwitchNotifierTest {

    private static final Logger logger = LoggerFactory.getLogger(TwitchNotifierTest.class);

    private static MockWebServer server;
    private static TwitchListenerTest listener;
    private static Twitch twitch;
    private static TwitchNotifier notifier;
    private static WebHooker hooker;

    /** Mock request object to make POST requests on behalf of Twitch. */
    private static Request request;

    /** Mock response object to get response from WebHooker. */
    private static Response response;

    @BeforeEach
    public void beforeEach() throws IOException {
        server = new MockWebServer();
        server.start();
        server.enqueue(new MockResponse().setBody(TestUtils.read("token_bearer.json")));

        int port = server.getPort();
        logger.debug("Running MockWebServer on port {}.", port);

        URL authUrl = new URL("http://localhost:" + port);
        URL baseUrl = new URL("http://localhost:" + port);
        String clientId = "uo6dggojyb8d6soh92zknwmi5ej1q2";
        String clientSecret = "0123456789abcdefghijABCDEFGHIJ";
        AuthenticationType authType = AuthenticationType.BEARER;
        twitch = new Twitch(authUrl, baseUrl, clientId, clientSecret, authType);

        notifier = twitch.createNotifier("http://localhost:4567/:uuid", 4567, new MapClientController());
        Spark.awaitInitialization();

        listener = new TwitchListenerTest();
        notifier.addListeners(listener);

        hooker = notifier.getWebHooker();

        request = mock(Request.class);
        when(request.requestMethod()).then((o) -> "POST");
        when(request.contentType()).then((o) -> "application/json");
        when(request.protocol()).then((o) -> "http");
        when(request.port()).then((o) -> 4567);
        when(request.pathInfo()).then((o) -> "/");
        when(request.params("hub.challenge")).then((o) -> null);

        response = mock(Response.class);
    }

    @AfterEach
    public void afterEach() throws IOException {
        logger.debug("Finished test case and closing all resources.");
        server.close();
        hooker.close();
        Spark.awaitStop();
    }

    @Test
    public void getUserUpdates() {
        String topic = "https://api.twitch.tv/helix/users/follows?first=1&from_id=1336";
        server.enqueue(new MockResponse().setBody("").setResponseCode(202));
        notifier.subscribe(topic, 360).queue();

        ClientController controller = hooker.getController();
        assertNotNull(controller);

        Iterator<Client> iter = controller.iterator();
        assertNotNull(iter);

        Client client = iter.next();
        assertNotNull(client);

        when(request.body()).then((o) -> TestUtils.read("webhook_follow_example.json"));
        when(request.url()).then((o) -> hooker.getPublicUrl(client));
        when(request.params("uuid")).then((o) -> client.getUuid().toString());
        hooker.getRoute().handle(request, response);

        FollowEvent event = listener.getFollowEvent();
        assertNotNull(event);

        assertAll("Verify that we handle the Follow Update Correctly",
            () -> assertEquals(1336, event.getFollowerId()),
            () -> assertEquals("ebi", event.getFollowerName()),
            () -> assertEquals(1337, event.getFolloweeId()),
            () -> assertEquals("oliver0823nagy", event.getFolloweeName()),
            () -> assertEquals(1503442524000L, event.getDate().getTime())
        );
    }

    @Test
    public void getStreamOfflineUpdate() {
        String topic = "https://api.twitch.tv/helix/streams?user_id=5678";
        server.enqueue(new MockResponse().setBody("").setResponseCode(202));
        notifier.subscribe(topic, 360).queue();

        ClientController controller = hooker.getController();
        assertNotNull(controller);

        Iterator<Client> iter = controller.iterator();
        assertNotNull(iter);

        Client client = iter.next();
        assertNotNull(client);

        when(request.body()).then((o) -> TestUtils.read("webhook_stream_offline_example.json"));
        when(request.url()).then((o) -> hooker.getPublicUrl(client));
        when(request.params("uuid")).then((o) -> client.getUuid().toString());
        hooker.getRoute().handle(request, response);

        StreamUpdateEvent event = listener.getStreamEvent();
        assertNotNull(event);

        assertAll("Verify that we handle the Stream Offline Update Correctly",
            () -> assertNull(event.getTwitchStream()),
            () -> assertFalse(event.isLive())
        );
    }

    @Test
    public void getStreamnlineUpdate() {
        String topic = "https://api.twitch.tv/helix/streams?user_id=5678";
        server.enqueue(new MockResponse().setBody("").setResponseCode(202));
        notifier.subscribe(topic, 360).queue();

        ClientController controller = hooker.getController();
        assertNotNull(controller);

        Iterator<Client> iter = controller.iterator();
        assertNotNull(iter);

        Client client = iter.next();
        assertNotNull(client);

        when(request.body()).then((o) -> TestUtils.read("webhook_stream_online_example.json"));
        when(request.url()).then((o) -> hooker.getPublicUrl(client));
        when(request.params("uuid")).then((o) -> client.getUuid().toString());
        hooker.getRoute().handle(request, response);

        StreamUpdateEvent event = listener.getStreamEvent();
        assertNotNull(event);

        TwitchStream twitchStream = event.getTwitchStream();
        assertNotNull(event.getTwitchStream());

        assertAll("Verify that we handle the Stream Online Update Correctly",
            () -> assertEquals(123456789L, twitchStream.getId()),
            () -> assertEquals(5678, twitchStream.getUserId()),
            () -> assertEquals("wjdtkdqhs", twitchStream.getUsername())
        );
    }

    @Test
    public void getUserUpdate() {
        String topic = "https://api.twitch.tv/helix/users?id=1234";
        server.enqueue(new MockResponse().setBody("").setResponseCode(202));
        notifier.subscribe(topic, 360).queue();

        ClientController controller = hooker.getController();
        assertNotNull(controller);

        Iterator<Client> iter = controller.iterator();
        assertNotNull(iter);

        Client client = iter.next();
        assertNotNull(client);

        when(request.body()).then((o) -> TestUtils.read("webhook_user_example.json"));
        when(request.url()).then((o) -> hooker.getPublicUrl(client));
        when(request.params("uuid")).then((o) -> client.getUuid().toString());
        hooker.getRoute().handle(request, response);

        UserUpdatedEvent event = listener.getUserEvent();
        assertNotNull(event);

        User user = event.getUser();
        assertNotNull(user);

        assertAll("Verify that we handle the User Update Correctly",
            () -> assertEquals(1234, user.getId()),
            () -> assertEquals("1234login", user.getUsername())
        );
    }
}
