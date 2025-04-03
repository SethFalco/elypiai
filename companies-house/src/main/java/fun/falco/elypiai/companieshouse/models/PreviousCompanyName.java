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

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class PreviousCompanyName {

    /**
     * @see #getName()
     */
    private String name;

    /**
     * @see #getCeasedOn()
     */
    private LocalDate ceasedOn;

    /**
     * @see #getEffectiveFrom()
     */
    private LocalDate effectiveFrom;

    /** Previous company name. */
    public String getName() {
        return name;
    }

    /** Date on which the company name ceased. */
    public LocalDate getCeasedOn() {
        return ceasedOn;
    }

    /** Date from which the company name was effective. */
    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }
}
