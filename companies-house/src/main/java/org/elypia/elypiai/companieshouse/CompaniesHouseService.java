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

package org.elypia.elypiai.companieshouse;

import org.elypia.elypiai.companieshouse.models.Company;
import org.elypia.elypiai.companieshouse.models.RegisteredOfficeAddress;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author seth@elyia.org (Seth Falco)
 * @since 4.2.0
 */
public interface CompaniesHouseService {

    @GET("search")
    Single<Object> searchAll(
        @Query("q") String query,
        @Query("items_per_page") Integer itemsPerPage,
        @Query("start_index") Integer startIndex
    );

    @GET("search/companies")
    Single<Object> searchCompanies(
        @Query("q") String query,
        @Query("items_per_page") Integer itemsPerPage,
        @Query("start_index") Integer startIndex
    );

    @GET("search/officers")
    Single<Object> searchOfficers(
        @Query("q") String query,
        @Query("items_per_page") Integer itemsPerPage,
        @Query("start_index") Integer startIndex
    );

    @GET("search/disqualified-officers")
    Single<Object> searchDisqualifiedOfficers(
        @Query("q") String query,
        @Query("items_per_page") Integer itemsPerPage,
        @Query("start_index") Integer startIndex
    );

    @GET("company/{company_number}")
    Single<Company> getCompany(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/registered-office-address")
    Single<RegisteredOfficeAddress> getCompanyRegisteredOffice(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/officers")
    Single<Object> getCompanyOfficers(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/filing-history")
    Single<Object> getCompanyFilingHistory(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/filing-history/{transaction_id}")
    Single<Object> getCompanyFilingHistoryTransaction(
        @Path("company_number") String companyNumber,
        @Path("transaction_id") int transactionId
    );

    @GET("company/{company_number}/insolvency")
    Single<Object> getCompanyInsolvency(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/charges")
    Single<Object> getCompanyCharges(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/charges/{charge_id}")
    Single<Object> getCompanyCharge(
        @Path("company_number") String companyNumber,
        @Path("charge_id") int chargeId
    );

    @GET("officers/{officier_id}/appointments")
    Single<Object> getOfficierAppointments(
        @Path("officier_id") int officerId
    );

    @GET("disqualified-officers/natural/{officer_id}")
    Single<Object> getDisqualifedNaturalOfficier(
        @Path("officer_id") int officerId
    );

    @GET("disqualified-officers/corporate/{officer_id}")
    Single<Object> getDisqualifedCorporateOfficer(
        @Path("officer_id") int officerId
    );

    @GET("company/{company_number}/uk-establishments")
    Single<Object> getUkEstablishments(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/persons-with-significant-control")
    Single<Object> getCompanyPscs(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/persons-with-significant-control/individual/{psc_id}")
    Single<Object> getCompanyPsc(
        @Path("company_number") String companyNumber,
        @Path("psc_id") int pscId
    );

    @GET("company/{company_number}/corporate-entity/{psc_id}")
    Single<Object> getCompanyCorporateEntities(
        @Path("company_number") String companyNumber,
        @Path("psc_id") int pscId
    );

    @GET("company/{company_number}/legal-person/{psc_id}")
    Single<Object> getCompanyLegalPersons(
        @Path("company_number") String companyNumber,
        @Path("psc_id") int pscId
    );

    @GET("company/{company_number}/persons-with-significant-control-statements")
    Single<Object> getCompanyControlStatements(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/persons-with-significant-control-statements/{statement_id}")
    Single<Object> getCompanyControlStatements(
        @Path("company_number") String companyNumber,
        @Path("statement_id") int statementId
    );

    @GET("company/{company_number}/persons-with-significant-control/super-secure/{super_secure_id}")
    Single<Object> getCompanySecurePerson(
        @Path("company_number") String companyNumber,
        @Path("super_secure_id") int superSecureId
    );

    @GET("company/{company_number}/registers")
    Single<Object> getCompanyRegisters(
        @Path("company_number") String companyNumber
    );

    @GET("company/{company_number}/exemptions")
    Single<Object> getCompanyExemptions(
        @Path("company_number") String companyNumber
    );
}
