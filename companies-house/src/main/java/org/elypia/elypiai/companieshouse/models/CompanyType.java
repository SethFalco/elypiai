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

public enum CompanyType {

    /**
     * Not part of the API, this is returned when a type
     * not known to this wrapper is returned.
     */
    UNKNOWN,

    @SerializedName("private-unlimited")
    PRIVATE_UNLIMITED,

    @SerializedName("ltd")
    LTD,

    @SerializedName("plc")
    PLC,

    @SerializedName("old-public-company")
    OLD_PUBLIC_COMPANY,

    @SerializedName("private-limited-guarant-nsc-limited-exemption")
    PRIVATE_LIMITED_GUARANT_NSC_LIMITED_EXCEPTION,

    @SerializedName("limited-partnership")
    LIMITED_PARTNERSHIP,

    @SerializedName("private-limited-guarant-nsc")
    PRIVATE_LIMITED_GUARANT_NSC,

    @SerializedName("converted-or-closed")
    CONVERTED_OR_CLOSED,

    @SerializedName("private-unlimited-nsc")
    PRIVATE_UNLIMITED_NSC,

    @SerializedName("private-limited-shares-section-30-exemption")
    PRIVATE_LIMITED_SHARES_SEECTION_30_EXEMPTION,

    @SerializedName("protected-cell-company")
    PROTECTED_CELL_COMPANY,

    @SerializedName("assurance-company")
    ASSURANCE_COMPANY,

    @SerializedName("oversea-company")
    OVERSEA_COMPANY,

    @SerializedName("eeig")
    EEIG,

    @SerializedName("icvc-securities")
    ICVC_SECURITIES,

    @SerializedName("icvc-warrant")
    ICVC_WARRANT,

    @SerializedName("icvc-umbrella")
    ICVC_UMBRELLA,

    @SerializedName("industrial-and-provident-society")
    INDUSTRIAL_AND_PROVIDENT_SOCIETY,

    @SerializedName("northern-ireland")
    NOTHERN_IRELAND,

    @SerializedName("northern-ireland-other")
    NOTHERN_IRELAND_OTHER,

    @SerializedName("llp")
    LIP,

    @SerializedName("royal-charter")
    ROYAL_CHARTER,

    @SerializedName("investment-company-with-variable-capital")
    INVESTMENT_COMPANY_WITH_VARIABLE_CAPITAL,

    @SerializedName("unregistered-company")
    UNREGISTERED_COMPANY,

    @SerializedName("llp")
    LLP,

    @SerializedName("other")
    OTHER,

    @SerializedName("european-public-limited-liability-company-se")
    EUROPEAN_PUBLIC_LIMITED_LIABILITY_COMPANY_SE,

    @SerializedName("scottish-partnership")
    SCOTTISH_PARTNERSHIP,

    @SerializedName("charitable-incorporated-organisation")
    CHARITABLE_INCORPORATED_ORGANIZATION,

    @SerializedName("scottish-charitable-incorporated-organisation")
    SCOTTISH_CHARITABLE_INCORPORTATED_ORGANIZATION,

    @SerializedName("further-education-or-sixth-form-college-corporation")
    FURTHER_EDUCATION_OR_SIXTH_FORM_COLLEGE_CORPORATION
}
