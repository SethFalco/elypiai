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

package org.elypia.elypiai.companieshouse.models;

import com.google.gson.annotations.SerializedName;

/**
 * Extra details about the status of the company.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public enum CompanyStatus {

    @SerializedName("active")
    ACTIVE(null),

    @SerializedName("dissolved")
    DISSOLVED(null),

    @SerializedName("converted-closed")
    CONVERTED_OR_CLOSED(null),

    /** Transfer from UK. */
    @SerializedName("transferred-from-uk")
    TRANSFERRED_FROM_UK("Transfer from UK"),

    /** Active proposal to strike off. */
    @SerializedName("active-proposal-to-strike-off")
    ACTIVE_PROPOSAL_TO_STRIKE_OFF("Active proposal to strike off"),

    /** Petition to restore dissolved. */
    @SerializedName("petition-to-restore-dissolved")
    PETITION_TO_RESTORE_DISSOLVED("Petition to restore dissolved"),

    /** Transformed to SE. */
    @SerializedName("transformed-to-se")
    TRANSFORMED_TO_SE("Transformed to SE"),

    /** Converted to PLC. */
    @SerializedName("converted-to-plc")
    CONVERTED_TO_PLC("Converted to PLC");

    private final String details;

    CompanyStatus(final String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
