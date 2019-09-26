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

package org.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class WebhookSubscription {

    /** The topic used in the initial subscription. */
    @SerializedName("topic")
    private String topic;

    /** The callback provided for this subscription. */
    @SerializedName("callback")
    private String callback;

    /** Date and time when this subscription expires. */
    @SerializedName("expires_at")
    private Date expires;

    public String getTopic() {
        return topic;
    }

    public String getCallback() {
        return callback;
    }

    public Date getExpires() {
        return expires;
    }
}
