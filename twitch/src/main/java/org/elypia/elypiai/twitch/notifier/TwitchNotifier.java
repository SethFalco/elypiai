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

package org.elypia.elypiai.twitch.notifier;

import org.elypia.elypiai.common.core.RestAction;
import org.elypia.elypiai.twitch.Twitch;
import org.elypia.elypiai.twitch.notifier.dispatchers.*;
import org.elypia.webhooker.*;
import org.slf4j.*;
import retrofit2.Call;
import spark.Spark;

import java.util.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class TwitchNotifier {

    private static Logger logger = LoggerFactory.getLogger(TwitchNotifier.class);

    /** AuthDispatcher is stateless and needs to be added to all clients, no need to create multiple. */
    private static final AuthDispatcher AUTH_DISPATCHER = new AuthDispatcher();

    /** The parent Twitch instance this notifier can share resources with. */
    private final Twitch twitch;

    /** The global webhooker instance used to receive events. */
    private final WebHooker webhooker;

    private Collection<TwitchListener> listeners;

    /**
     * This will run a webserver that can accept webhooks.
     * In order to do this you will need to ensure you have
     * a system with with a port open for other systems to
     * make requests too.
     *
     * You will need a domain, or public IP address for Twitch
     * to make requests with, and to ensure whatever port you specify
     * is set to allow from all location in your firewall.
     *
     * @param twitch The parent Twitch instance this notifier can share resources with.
     * @param webhooker The global webhooker instance used to receive events.
     */
    public TwitchNotifier(Twitch twitch, WebHooker webhooker) {
        this.twitch = twitch;
        this.webhooker = webhooker;
        listeners = new ArrayList<>();
    }

    public RestAction<Void> subscribe(String topic) {
        return subscribe(topic, 864000);
    }

    public RestAction<Void> subscribe(String topic, int seconds) {
        return subscribe(topic, seconds, null);
    }

    public RestAction<Void> subscribe(String topic, int seconds, String secret) {
        Client client = new Client(AUTH_DISPATCHER);

        if (topic.startsWith("https://api.twitch.tv/helix/users/follows"))
            client.addCallbacks(new FollowDispatcher(this));
        else if (topic.startsWith("https://api.twitch.tv/helix/streams"))
            client.addCallbacks(new StreamDispatcher(this));
        else if (topic.startsWith("https://api.twitch.tv/helix/users"))
            client.addCallbacks(new UserDispatcher(this));
        else if (topic.startsWith("https://api.twitch.tv/helix/analytics/games"))
            client.addCallbacks(new GameAnalyticsDispatcher(this));
        else if (topic.startsWith("https://api.twitch.tv/helix/analytics/extensions"))
            client.addCallbacks(new ExtensionAnalyticsDispatcher(this));
        else
            throw new IllegalArgumentException("Unknown topic provided, unable to create client.");

        String callback = webhooker.getPublicUrl(client);
        webhooker.getController().add(client);
        return update(callback, HubMode.SUBSCRIBE, topic, seconds, secret);
    }

    public RestAction<Void> unsubscribe(String callback, String topic) {
        return update(callback, HubMode.UNSUBSCRIBE, topic, 0, null);
    }

    /**
     * <p>Update a WebHook subscription, this could be to subscribe
     * or unsubscribe a webhook.</p>
     * If {@link HubMode} is {@link HubMode#SUBSCRIBE}, this will
     * require {@link WebHooker} has fully initialized and if
     * not this will block the calling thread until it has done so as
     * Twitch will send a payload immediately when the subscription is made
     * for a challenge which must be completed.
     *
     * @param callback The callback URL from the {@link Client}.
     * @param mode If subscribing or unsubscribing from the event.
     * @param topic The topic URL we're subscribing too.
     * @param seconds How long the subscription should stay alive,
     *                in seconds, up to 10 days.
     * @param secret A client secret for verifying payloads
     *               have come from Twitch.
     */
    public RestAction<Void> update(String callback, HubMode mode, String topic, int seconds, String secret) {
        if (mode == HubMode.SUBSCRIBE)
            Spark.awaitInitialization();

        Call<Void> call = twitch.getService()
            .updateWebhookSubscription(callback, mode.getName(), topic, seconds, secret);

        return new RestAction<>(call);
    }

    public void addListeners(TwitchListener... list) {
        listeners.addAll(Arrays.asList(list));
    }

    public void removeListeners(TwitchListener... list) {
        listeners.removeAll(Arrays.asList(list));
    }

    public WebHooker getWebHooker() {
        return webhooker;
    }

    public Collection<TwitchListener> getListeners() {
        return Collections.unmodifiableCollection(listeners);
    }
}
