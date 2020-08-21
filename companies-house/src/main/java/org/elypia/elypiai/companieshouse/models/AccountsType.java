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

package org.elypia.elypiai.companieshouse.models;

import com.google.gson.annotations.SerializedName;

/**
 * The types of company accounts.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public enum AccountsType {

    /** Null */
    @SerializedName("null")
    NULL("Null"),

    /** Full */
    @SerializedName("full")
    FULL("Full"),

    /** Small */
    @SerializedName("small")
    SMALL("Small"),

    /** Medium */
    @SerializedName("medium")
    MEDIUM("Medium"),

    /** Group */
    @SerializedName("group")
    GROUP("Group"),

    /** Dormant */
    @SerializedName("dormant")
    DORMANT("Dormant"),

    /** Interim */
    @SerializedName("interim")
    INTERIM("Interim"),

    /** Initial */
    @SerializedName("initial")
    INITIAL("Inital"),

    /** Total Exemption Full */
    @SerializedName("total-exemption-full")
    TOTAL_EXEMPTION_FULL("Total Exemption Full"),

    /** Total Exception Small */
    @SerializedName("total-exemption-small")
    TOTAL_EXEMPTION_SMALL("Total Exemption Small"),

    /** Partial Excemption */
    @SerializedName("partial-exemption")
    PARTIAL_EXEMPTION("Partial Exemption"),

    /** Audit Exemption Subsidiary */
    @SerializedName("audit-exemption-subsidiary")
    AUDIT_EXEMPTION_SUBSIDIARY("Audit Exemption Subsidiary"),

    /** Filing Exemption Subsidiary */
    @SerializedName("filing-exemption-subsidiary")
    FILLING_EXEMPTION_SUBSIDIARY("Filing Exemption Subsidiary"),

    /** Micro Entity */
    @SerializedName("micro-entity")
    MICRO_ENTITY("Micro Entity");

    private final String details;

    AccountsType(final String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
