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

package fun.falco.elypiai.companieshouse.models;

import java.time.MonthDay;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class Accounts {

    /**
     * @see #getAccountingReferenceDate()
     */
    @SerializedName("accounting_reference_date")
    private MonthDay accountingReferenceDate;

    /**
     * @see #getNextAccounts()
     */
    @SerializedName("next_accounts")
    private NextAccounts nextAccounts;

    /**
     * @see #getPreviousAccounts()
     */
    @SerializedName("previous_accounts")
    private PreviousAccounts previousAccounts;

    /**
     * @return Accounting Reference Date (ARD) of the company.
     */
    public MonthDay getAccountingReferenceDate() {
        return accountingReferenceDate;
    }

    /**
     * @return Next company accounts filed.
     */
    public NextAccounts getNextAccounts() {
        return nextAccounts;
    }

    /**
     * @return Last company accounts filed.
     */
    public PreviousAccounts getPreviousAccounts() {
        return previousAccounts;
    }
}
