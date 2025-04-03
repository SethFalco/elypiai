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
import org.elypia.elypiai.companieshouse.CompaniesHouse;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class Company {

    /**
     * @see #getEtag()
     */
    @SerializedName("etag")
    private String etag;

    /**
     * @see #getCompanyNumber()
     */
    @SerializedName("company_number")
    private String companyNumber;

    /**
     * @see #getCompanyName()
     */
    @SerializedName("company_name")
    private String companyName;

    /**
     * @see #getPreviousCompanyNames()
     */
    @SerializedName("previous_company_names")
    private Collection<PreviousCompanyName> previousCompanyNames;

    /**
     * @see #isHasBeenLiquidated()
     */
    @SerializedName("has_been_liquidated")
    private boolean hasBeenLiquidated;

    /**
     * @see #getCessationDate()
     */
    @SerializedName("date_of_cessation")
    private LocalDate cessationDate;

    /**
     * @see #getCreationDate()
     */
    @SerializedName("date_of_creation")
    private LocalDate creationDate;

    /**
     * @see #getExternalRegistrationNumber()
     */
    @SerializedName("external_registration_number")
    private String externalRegistrationNumber;

    /**
     * @see #getConfirmationStatement()
     */
    @SerializedName("confirmation_statement")
    private StatementDates confirmationStatement;

    /**
     * @see #getCompanyStatus()
     */
    @SerializedName("company_status")
    private CompanyStatus companyStatus;

    /**
     * @see #canFile()
     */
    @SerializedName("can_file")
    private boolean canFile;

    /**
     * @see #getBranchCompanyDetails()
     */
    @SerializedName("branch_company_details")
    private BranchCompanyDetails branchCompanyDetails;

    /**
     * @see #getAnnualReturn()
     */
    @SerializedName("annual_return")
    private StatementDates annualReturn;

    /**
     * @see #getRegisteredOfficeAddress()
     */
    @SerializedName("registered_office_address")
    private RegisteredOfficeAddress registeredOfficeAddress;

    /**
     * @see #isRegisteredOfficeInDispute()
     */
    @SerializedName("registered_office_is_in_dispute")
    private boolean isRegisteredOfficeInDispute;

    /**
     * @see #getSicCodes()
     */
    @SerializedName("sic_codes")
    private Collection<String> sicCodes;

    /**
     * @see #getSubtype()
     */
    @SerializedName("subtype")
    private Subtype subtype;

    /**
     * @see #getType()
     */
    @SerializedName("type")
    private CompanyType type;

    /**
     * @see #isUndeliverableRegisteredOffice()
     */
    @SerializedName("undeliverable_registered_office_address")
    private boolean isUndeliverableRegisteredOffice;

    /**
     * @see #getPartialDataAvailable()
     */
    @SerializedName("partial_data_available")
    private PartialDataAvailableType partialDataAvailable;

    /**
     * @see #getLastFullMembersListData()
     */
    @SerializedName("last_full_members_list_date")
    private LocalDate lastFullMembersListData;

    /**
     * @see #getJurisdiction()
     */
    @SerializedName("jurisdiction")
    private Jurisdiction jurisdiction;

    /**
     * @see #getAccounts()
     */
    @SerializedName("accounts")
    private Accounts accounts;

    /**
     * @return ETag of the resource.
     */
    public String getEtag() {
        return etag;
    }

    /**
     * <strong>Company number can begin with letters or padded zeros.</strong>
     *
     * @return Number of the company.
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * @return Name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return Previous names of this company.
     */
    public Collection<PreviousCompanyName> getPreviousCompanyNames() {
        return previousCompanyNames;
    }

    /**
     * @return Flag indicating if the company has been liquidated in the past.
     */
    public boolean isHasBeenLiquidated() {
        return hasBeenLiquidated;
    }

    /**
     * @return
     *     Date which the company was converted / closed or dissolved. Refer to
     *     {@link #getCompanyStatus()} to determine which.
     */
    public LocalDate getCessationDate() {
        return cessationDate;
    }

    /**
     * @return Date when the company was created.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @return Number given by an external registration body.
     */
    public String getExternalRegistrationNumber() {
        return externalRegistrationNumber;
    }

    /**
     * @return Confirmation statement information.
     */
    public StatementDates getConfirmationStatement() {
        return confirmationStatement;
    }

    /**
     * @return Status of the company.
     */
    public CompanyStatus getCompanyStatus() {
        return companyStatus;
    }

    /**
     * @return Flag indicating whether this company can file.
     */
    public boolean canFile() {
        return canFile;
    }

    /**
     * @return UK branch of a foreign company.
     */
    public BranchCompanyDetails getBranchCompanyDetails() {
        return branchCompanyDetails;
    }

    /**
     * @return
     *     Annual return information. This member is only returned if a
     *     confirmation statement has not be filed.
     */
    public StatementDates getAnnualReturn() {
        return annualReturn;
    }

    /**
     * Can also be obtained with
     * {@link CompaniesHouse#getRegisteredOfficeAddress(String)}.
     *
     * @return Address of the company's registered office.
     */
    public RegisteredOfficeAddress getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    /**
     * @return Flag indicating registered office address as been replaced.
     */
    public boolean isRegisteredOfficeInDispute() {
        return isRegisteredOfficeInDispute;
    }

    /**
     * SIC stands for Standard Industrial Classification.
     *
     * @return SIC codes for this company.
     * @see <a href="https://resources.companieshouse.gov.uk/sic/">Nature of business: Standard Industrial Classification (SIC) codes</a>
     */
    public Collection<String> getSicCodes() {
        return sicCodes;
    }

    /**
     * @return Subtype of the company.
     */
    public Subtype getSubtype() {
        return subtype;
    }

    /**
     * @return Type of the company.
     */
    public CompanyType getType() {
        return type;
    }

    /**
     * @return Flag indicating whether post can be delivered to the registered office.
     */
    public boolean isUndeliverableRegisteredOffice() {
        return isUndeliverableRegisteredOffice;
    }

    /**
     * @return If Companies House is not the primary source of data for this company.
     */
    public PartialDataAvailableType getPartialDataAvailable() {
        return partialDataAvailable;
    }

    /**
     * @return Date of last full members list update.
     */
    public LocalDate getLastFullMembersListData() {
        return lastFullMembersListData;
    }

    /**
     * @return Jurisdiction specifies the political body responsible for the company.
     */
    public Jurisdiction getJurisdiction() {
        return jurisdiction;
    }

    /**
     * @return Company accounts information.
     */
    public Accounts getAccounts() {
        return accounts;
    }
}
