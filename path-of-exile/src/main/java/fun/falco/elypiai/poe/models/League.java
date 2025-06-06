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

import java.time.Instant;
import java.util.List;

import org.elypia.retropia.gson.deserializers.IsoDateTimeTemporalDeserializer;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class League extends CompactLeague {

    @SerializedName("description")
    private String description;

    @SerializedName("registerAt")
    @JsonAdapter(IsoDateTimeTemporalDeserializer.class)
    private Instant registerAt;

    @SerializedName("rules")
    private List<LeagueRule> rules;

    public String getDescription() {
        return description;
    }

    public Instant getRegisterAt() {
        return registerAt;
    }

    public List<LeagueRule> getRules() {
        return rules;
    }
}
