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

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

/**
 * Confirmation statement information.
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class StatementDates {

    /**
     * @see #getLastMadeUpTo()
     */
    @SerializedName("last_made_up_to")
    private LocalDate lastMadeUpTo;

    /**
     * @see #getNextDue()
     */
    @SerializedName("next_due")
    private LocalDate nextDue;

    /**
     * @see #getNextMadeUpTo()
     */
    @SerializedName("next_made_up_to")
    private LocalDate nextMadeUpTo;

    /**
     * @see #isOverdue()
     */
    @SerializedName("overdue")
    private boolean isOverdue;

    /**
     * @return Date to which the company last made a confirmation statement.
     */
    public LocalDate getLastMadeUpTo() {
        return lastMadeUpTo;
    }

    /**
     * @return Date by which the next confirmation statement must be received.
     */
    public LocalDate getNextDue() {
        return nextDue;
    }

    /**
     * @return Date to which the company must next make a confirmation statement.
     */
    public LocalDate getNextMadeUpTo() {
        return nextMadeUpTo;
    }

    /**
     * @return Flag indicating if the confirmation statement is overdue.
     */
    public boolean isOverdue() {
        return isOverdue;
    }
}
