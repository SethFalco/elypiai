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

package org.elypia.elypiai.companieshouse.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public enum AccountPublicationTerms {

    /** Accounts publication date supplied by company. */
    @SerializedName("accounts-publication-date-supplied-by-company")
    ACCOUNTS_PUBLICATION_DATE_SUPPLIED_BY_COMPANY,

    /** Accounts publication date does not need to be supplied by company. */
    @SerializedName("accounting-publication-date-does-not-need-to-be-supplied-by-company")
    ACCOUNTING_PUBLICATION_DATE_DOES_NOT_NEED_TO_BE_SUPPLIED_BY_COMPANY,

    /** Accounting reference date allocated by Companies House. */
    @SerializedName("accounting-reference-date-allocated-by-companies-house")
    ACCOUNTING_REFERENCE_DATE_ALLOCATED_BY_COMPANIES_HOUSE
}
