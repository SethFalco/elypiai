/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.poe.models;

import org.elypia.retropia.gson.deserializers.NestedDeserializer;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Account {

    @SerializedName("name")
    private String name;

    @SerializedName("realm")
    private Realm realm;

    @SerializedName("challenges")
    @JsonAdapter(NestedDeserializer.class)
    private int challenges;

    @SerializedName("guild")
    private Guild guild;

    @SerializedName("twitch")
    @JsonAdapter(NestedDeserializer.class)
    private String twitch;

    public String getName() {
        return name;
    }

    public Realm getRealm() {
        return realm;
    }

    public int getChallenges() {
        return challenges;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getTwitch() {
        return twitch;
    }
}
