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

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public class Unit {

    @SerializedName("source")
    private String source;

    @SerializedName("previous_source")
    private String previousSource;

    @SerializedName("target")
    private String target;

    @SerializedName("id_hash")
    private long idHash;

    @SerializedName("content_hash")
    private long contentHash;

    @SerializedName("location")
    private String location;

    @SerializedName("context")
    private String context;

    @SerializedName("note")
    private String note;

    @SerializedName("flags")
    private String flags;

    @SerializedName("fuzzy")
    private boolean isFuzzy;

    @SerializedName("translated")
    private boolean isTranslated;

    @SerializedName("approved")
    private boolean isApproved;

    @SerializedName("position")
    private int position;

    @SerializedName("has_suggestion")
    private boolean hasSuggestion;

    @SerializedName("has_comment")
    private boolean hasComment;

    @SerializedName("has_failing_checks")
    private boolean hasFailingChecks;

    @SerializedName("num_words")
    private int totalWords;

    @SerializedName("priority")
    private int priority;

    @SerializedName("id")
    private int id;

    @SerializedName("web_url")
    private String url;

    public String getSource() {
        return source;
    }

    public String getPreviousSource() {
        return previousSource;
    }

    public String getTarget() {
        return target;
    }

    public long getIdHash() {
        return idHash;
    }

    public long getContentHash() {
        return contentHash;
    }

    public String getLocation() {
        return location;
    }

    public String getContext() {
        return context;
    }

    public String getNote() {
        return note;
    }

    public String getFlags() {
        return flags;
    }

    public boolean isFuzzy() {
        return isFuzzy;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public int getPosition() {
        return position;
    }

    public boolean isHasSuggestion() {
        return hasSuggestion;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public boolean isHasFailingChecks() {
        return hasFailingChecks;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
