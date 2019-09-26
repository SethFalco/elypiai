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

package org.elypia.elypiai.cleverbot.data;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public enum CleverTweak {

    /**
     * 0 is more sensible.
     * 100 is more wacky.
     */
    WACKY(1),

    /**
     * 0 is more shy.
     * 100 is more talkative.
     */
    TALKATIVE(2),

    /**
     * 0 is more self-centred.
     * 100 is more attentive.
     */
    ATTENTIVE(3);

    private static final String TWEAK = "cb_settings_tweak";

    private final int ID;

    CleverTweak(int id) {
        ID = id;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return TWEAK + ID;
    }
}
