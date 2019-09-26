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

package org.elypia.elypiai.osu;

import com.google.gson.annotations.*;
import org.elypia.elypiai.common.gson.deserializers.BitBooleanDeserializer;
import org.elypia.elypiai.osu.data.OsuTeam;
import org.elypia.elypiai.osu.impl.OsuScore;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class GameScore extends OsuScore {

    @SerializedName("slot")
    private int slot;

    @SerializedName("team")
    private OsuTeam team;

    @SerializedName("pass")
    @JsonAdapter(BitBooleanDeserializer.class)
    private boolean pass;

    public int getSlot() {
        return slot;
    }

    public OsuTeam getTeam() {
        return team;
    }

    public boolean isPass() {
        return pass;
    }
}
