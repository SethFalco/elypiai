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

package org.elypia.elypiai.companieshouse;

import org.elypia.elypiai.companieshouse.models.RegisteredOfficeAddress;
import org.elypia.webservertestbed.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

public class CompaniesHouseTest {

    @RegisterExtension
    public static final WebServerExtension serverExtension = new WebServerExtension();

    private static CompaniesHouse companiesHouse;

    @BeforeEach
    public void beforeEach() {
        companiesHouse = new CompaniesHouse("Fake API Key", serverExtension.getRequestUrl());
    }

    @Test
    public void createNormalInstance() {
        assertDoesNotThrow(() -> new CompaniesHouse("Fake API Key"));
    }

    @WebServerTest("registered_address_12203025.json")
    public void parseResults() {
        RegisteredOfficeAddress result = companiesHouse.getRegisteredOfficeAddress(12203025).blockingGet();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("70682bd923a871e583adf14ef4696e097af1fdb7", result.getEtag()),
            () -> assertEquals("International House", result.getAddressLine1()),
            () -> assertEquals("24 Holborn Viaduct", result.getAddressLine2()),
            () -> assertEquals("England", result.getCountry()),
            () -> assertEquals("London", result.getLocality()),
            () -> assertEquals("EC1A 2BN", result.getPostalCode())
        );
    }
}
