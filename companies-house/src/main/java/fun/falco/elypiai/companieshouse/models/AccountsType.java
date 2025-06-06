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

package fun.falco.elypiai.companieshouse.models;

import com.google.gson.annotations.SerializedName;

/**
 * The types of company accounts.
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public enum AccountsType {

    @SerializedName("null")
    NULL("Null"),

    @SerializedName("full")
    FULL("Full"),

    @SerializedName("small")
    SMALL("Small"),

    @SerializedName("medium")
    MEDIUM("Medium"),

    @SerializedName("group")
    GROUP("Group"),

    @SerializedName("dormant")
    DORMANT("Dormant"),

    @SerializedName("interim")
    INTERIM("Interim"),

    @SerializedName("initial")
    INITIAL("Inital"),

    @SerializedName("total-exemption-full")
    TOTAL_EXEMPTION_FULL("Total Exemption Full"),

    @SerializedName("total-exemption-small")
    TOTAL_EXEMPTION_SMALL("Total Exemption Small"),

    @SerializedName("partial-exemption")
    PARTIAL_EXEMPTION("Partial Exemption"),

    @SerializedName("audit-exemption-subsidiary")
    AUDIT_EXEMPTION_SUBSIDIARY("Audit Exemption Subsidiary"),

    @SerializedName("filing-exemption-subsidiary")
    FILLING_EXEMPTION_SUBSIDIARY("Filing Exemption Subsidiary"),

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
