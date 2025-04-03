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
public class ForeignAccounts {

    /**
     * @see #getAccountPeriodFrom()
     */
    @SerializedName("account_period_from")
    private MonthDay accountPeriodFrom;

    /**
     * @see #getAccountPeriodTo()
     */
    @SerializedName("account_period_to")
    private MonthDay accountPeriodTo;

    /**
     * @see #getMonthsToFileWithin()
     */
    @SerializedName("must_file_within")
    private int monthsToFileWithin;

    /**
     * @return Date account period starts under parent law.
     */
    public MonthDay getAccountPeriodFrom() {
        return accountPeriodFrom;
    }

    /**
     * @return Date account period ends under parent law.
     */
    public MonthDay getAccountPeriodTo() {
        return accountPeriodTo;
    }

    /**
     * @return Time allowed from period end for disclosure of accounts under parent law.
     */
    public int getMonthsToFileWithin() {
        return monthsToFileWithin;
    }
}
