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

package fun.falco.elypiai.mojang.models;

import java.util.UUID;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import fun.falco.elypiai.mojang.deserializers.UuidDeserializer;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MinecraftProfile implements Identifiable {

    /**
     * @see #getUuid()
     */
    @JsonAdapter(UuidDeserializer.class)
    @SerializedName("id")
    private UUID uuid;

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #getTextures()
     */
    private MinecraftTextures textures;

    /**
     * @see #isLegacy()
     */
    @SerializedName("legacy")
    private boolean isLegacy;

    /**
     * @return UUID of the user.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return Current display name or username of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Texture information of the user such as skin, or cape.
     */
    public MinecraftTextures getTextures() {
        return textures;
    }

    /**
     * @return If the user hasn't migrated to a Mojang account.
     */
    public boolean isLegacy() {
        return isLegacy;
    }
}
