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

import java.time.Instant;
import java.util.UUID;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MinecraftTextures {

    private Instant timestamp;
    private UUID uuid;
    private String name;
    private boolean isSignatureRequired;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSignatureRequired() {
        return isSignatureRequired;
    }

    public void setSignatureRequired(boolean isSignatureRequired) {
        this.isSignatureRequired = isSignatureRequired;
    }
}
