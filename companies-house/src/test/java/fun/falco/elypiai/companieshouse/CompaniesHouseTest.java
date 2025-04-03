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

package fun.falco.elypiai.companieshouse;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.MonthDay;

import org.elypia.webservertestbed.junit5.WebServerExtension;
import org.elypia.webservertestbed.junit5.WebServerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import fun.falco.elypiai.companieshouse.models.Company;
import fun.falco.elypiai.companieshouse.models.CompanyStatus;
import fun.falco.elypiai.companieshouse.models.CompanyType;
import fun.falco.elypiai.companieshouse.models.Jurisdiction;
import fun.falco.elypiai.companieshouse.models.RegisteredOfficeAddress;
import fun.falco.elypiai.companieshouse.models.StatementDates;
import fun.falco.elypiai.companieshouse.models.Subtype;

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

    @WebServerTest("company-by-id-12203025.json")
    public void parseCompanyById() {
        Company c = companiesHouse.getCompany("12203025").blockingGet();

        assertAll("Ensure Parsing Data Correctly",
            () -> assertEquals("e13d5166688c8aedf29799664995ac5a6fbf90db", c.getEtag()),
            () -> assertEquals(Subtype.COMMUNITY_INTEREST_COMPANY, c.getSubtype()),
            () -> assertEquals(Jurisdiction.ENGLAND_WALES, c.getJurisdiction()),
            () -> assertTrue(c.canFile()),
            () -> assertEquals(CompanyStatus.ACTIVE, c.getCompanyStatus()),
            () -> assertFalse(c.isRegisteredOfficeInDispute()),
            () -> assertEquals(1, c.getSicCodes().size()),
            () -> assertFalse(c.isUndeliverableRegisteredOffice()),
            () -> assertEquals("ELYPIA CIC", c.getCompanyName()),
            () -> assertEquals("12203025", c.getCompanyNumber()),
            () -> assertEquals(CompanyType.PRIVATE_LIMITED_GUARANT_NSC, c.getType()),
            () -> assertEquals(LocalDate.of(2019, 9, 12), c.getCreationDate())
        );
    }

    @WebServerTest("company-by-id-12203025.json")
    public void testParsingCompanyAddress() {
        RegisteredOfficeAddress result = companiesHouse.getCompany("12203025").blockingGet().getRegisteredOfficeAddress();

        assertAll("Ensure Parsing Result Data Correctly",
            () -> assertEquals("International House", result.getAddressLine1()),
            () -> assertEquals("24 Holborn Viaduct", result.getAddressLine2()),
            () -> assertEquals("England", result.getCountry()),
            () -> assertEquals("London", result.getLocality()),
            () -> assertEquals("EC1A 2BN", result.getPostalCode())
        );
    }

    @WebServerTest("company-by-id-12203025.json")
    public void testParsingConfirmationStatement() {
        StatementDates cs = companiesHouse.getCompany("12203025").blockingGet().getConfirmationStatement();

        assertAll("Ensure Parsing Data Correctly",
            () -> assertEquals(LocalDate.of(2020, 9, 11), cs.getNextMadeUpTo()),
            () -> assertEquals(LocalDate.of(2020, 9, 25), cs.getNextDue()),
            () -> assertFalse(cs.isOverdue())
        );
    }

    @WebServerTest("company-by-id-12203025.json")
    public void testParsingCompanyAccountsReferenceDate() {
        MonthDay ard = companiesHouse.getCompany("12203025").blockingGet().getAccounts().getAccountingReferenceDate();
        assertEquals(MonthDay.of(9, 30), ard);
    }

    @WebServerTest("registered-address-12203025.json")
    public void parseRegisteredAddress() {
        RegisteredOfficeAddress result = companiesHouse.getRegisteredOfficeAddress("12203025").blockingGet();

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
