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
 * UK branch of a foreign company.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public class BranchCompanyDetails {

    /**
     * @see #getBusinessActivity()
     */
    @SerializedName("business_activity")
    private String businessActivity;

    /**
     * @see #getParentCompanyName()
     */
    @SerializedName("parent_company_name")
    private String parentCompanyName;

    /**
     * @see #getParentCompanyNumber()
     */
    @SerializedName("parent_company_number")
    private String parentCompanyNumber;

    /**
     * @return Type of business undertaken by the UK establishment.
     */
    public String getBusinessActivity() {
        return businessActivity;
    }

    /**
     * @return Parent company name.
     */
    public String getParentCompanyName() {
        return parentCompanyName;
    }

    /**
     * @return Parent company number.
     */
    public String getParentCompanyNumber() {
        return parentCompanyNumber;
    }
}
