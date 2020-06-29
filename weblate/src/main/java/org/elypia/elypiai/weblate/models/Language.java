/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public class Language {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("plural")
    private Plural plural;

    @SerializedName("aliases")
    private List<String> aliases;

    @SerializedName("direction")
    private TextDirection textDirection;

    @SerializedName("web_url")
    private String webUrl;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Plural getPlural() {
        return plural;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public TextDirection getTextDirection() {
        return textDirection;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
