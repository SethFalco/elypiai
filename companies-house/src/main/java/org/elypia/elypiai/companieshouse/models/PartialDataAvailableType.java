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
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public enum PartialDataAvailableType {

    /** Refer to the Financial Conduct Authority for further information about this company. */
    @SerializedName("full-data-available-from-financial-conduct-authority")
    FINANCIAL_CONDUCT_AUTHORITY,

    /** Refer to the Department of the Economy for further information about this company. */
    @SerializedName("full-data-available-from-department-of-the-economy")
    DEPARTMENT_OF_THE_ECONOMY,

    /** Contact the company directly for further information. */
    @SerializedName("full-data-available-from-the-company")
    THE_COMPANY
}
