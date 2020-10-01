/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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
 * Foreign company details.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public class ForeignCompanyDetails {

    /**
     * @see #getAccountingRequirements()
     */
    @SerializedName("accounting_requirement")
    private AccountingRequirements accountingRequirements;

    /**
     * @see #getAccounts()
     */
    @SerializedName("accounts")
    private ForeignAccounts accounts;

    /**
     * @see #getBusinessActivity()
     */
    @SerializedName("business_activity")
    private String businessActivity;

    /**
     * @see #getCompanyType()
     */
    @SerializedName("company_type")
    private String companyType;

    /**
     * @see #getGovernedBy()
     */
    @SerializedName("governed_by")
    private String governedBy;

    /**
     * @see #isCreditFinanceInstitution()
     */
    @SerializedName("is_a_credit_finance_institution")
    private boolean isCreditFinanceInstitution;

    /**
     * @see #getOriginatingRegistry()
     */
    @SerializedName("originating_registry")
    private OriginatingRegistry originatingRegistry;

    /**
     * @see #getRegistrationNumber()
     */
    @SerializedName("registration_number")
    private String registrationNumber;

    /**
     * @return Accounts requirement.
     */
    public AccountingRequirements getAccountingRequirements() {
        return accountingRequirements;
    }

    /**
     * @return Foreign company account information.
     */
    public ForeignAccounts getAccounts() {
        return accounts;
    }

    /**
     * @return Type of business undertaken by the company.
     */
    public String getBusinessActivity() {
        return businessActivity;
    }

    /**
     * @return Legal form of the company in the country of incorporation.
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * @return Law governing the company in country of incorporation.
     */
    public String getGovernedBy() {
        return governedBy;
    }

    /**
     * @return Is it a financial or credit institution.
     */
    public boolean isCreditFinanceInstitution() {
        return isCreditFinanceInstitution;
    }

    /**
     * @return Company origin informations.
     */
    public OriginatingRegistry getOriginatingRegistry() {
        return originatingRegistry;
    }

    /**
     * @return Registration number in company of incorporation.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
