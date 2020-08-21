/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.3
 */
public class Change {

    /**
     * @see #getUnit()
     */
    @SerializedName("unit")
    private String unit;

    /**
     * @see #getTranslation()
     */
    @SerializedName("translation")
    private String translation;

    /**
     * @see #getComponent()
     */
    @SerializedName("component")
    private String component;

    /**
     * @see #getGlossaryTerm()
     */
    @SerializedName("glossary_term")
    private String glossaryTerm;

    /**
     * @see #getUser()
     */
    @SerializedName("user")
    private String user;

    /**
     * @see #getAuthor()
     */
    @SerializedName("author")
    private String author;

    /**
     * @see #getTimestamp()
     */
    @SerializedName("timestamp")
    private Instant timestamp;

    /**
     * @see #getAction()
     */
    @SerializedName("action")
    private int action;

    /**
     * @see #getActionName()
     */
    @SerializedName("action_name")
    private String actionName;

    /**
     * @see #getTarget()
     */
    @SerializedName("target")
    private String target;

    /**
     * @see #getId()
     */
    @SerializedName("id")
    private int id;

    /**
     * @return Name of a related unit object.
     */
    public String getUnit() {
        return unit;
    }

    public String getTranslation() {
        return translation;
    }

    public String getComponent() {
        return component;
    }

    public String getGlossaryTerm() {
        return glossaryTerm;
    }

    public String getUser() {
        return user;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getAction() {
        return action;
    }

    public String getActionName() {
        return actionName;
    }

    public String getTarget() {
        return target;
    }

    public int getId() {
        return id;
    }
}
