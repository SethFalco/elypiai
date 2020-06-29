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
public class AccountingRequirements {

    /** @see #getForeignAccountType() */
    @SerializedName("foreign_account_type")
    private ForeignAccountType foreignAccountType;

    /** @see #getAccountPublicationTerms() */
    @SerializedName("terms_of_account_publication")
    private AccountPublicationTerms accountPublicationTerms;

    /**
     * @return Type of accounting requirement that applies.
     */
    public ForeignAccountType getForeignAccountType() {
        return foreignAccountType;
    }

    /**
     * @return Describes how the publication date is derived.
     */
    public AccountPublicationTerms getAccountPublicationTerms() {
        return accountPublicationTerms;
    }
}
