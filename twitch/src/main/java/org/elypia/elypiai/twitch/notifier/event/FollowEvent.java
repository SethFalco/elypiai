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

package org.elypia.elypiai.twitch.notifier.event;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class FollowEvent {

    @SerializedName("from_id")
    private int followerId;

    @SerializedName("from_name")
    private String followerName;

    @SerializedName("to_id")
    private int followeeId;

    @SerializedName("to_name")
    private String followeeName;

    @SerializedName("followed_at")
    private Date date;

    public int getFollowerId() {
        return followerId;
    }

    public String getFollowerName() {
        return followerName;
    }

    public int getFolloweeId() {
        return followeeId;
    }

    public String getFolloweeName() {
        return followeeName;
    }

    public Date getDate() {
        return date;
    }
}
