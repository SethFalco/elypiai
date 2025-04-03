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

import java.time.Instant;

public class CompanySearchItem extends SearchItem {

    private int companyNumber;
    private int companyStatus;
    private CompanyType companyType;
    private Instant dateOfCessation;
    private Instant dateOfCreation;
    private String externalRegistrationNumber;

    public int getCompanyNumber() {
        return companyNumber;
    }

    public int getCompanyStatus() {
        return companyStatus;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public Instant getDateOfCessation() {
        return dateOfCessation;
    }

    public Instant getDateOfCreation() {
        return dateOfCreation;
    }

    public String getExternalRegistrationNumber() {
        return externalRegistrationNumber;
    }
}
