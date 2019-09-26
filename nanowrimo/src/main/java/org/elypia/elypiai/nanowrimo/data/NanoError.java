/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.nanowrimo.data;

import javax.xml.bind.annotation.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@XmlEnum
public enum NanoError {

    UNKNOWN("Unknown"),

    @XmlEnumValue("user does not exist")
    USER_DOES_NOT_EXIST("user does not exist"),

    @XmlEnumValue("user does not have a current novel")
    USER_DOES_NOT_HAVE_A_CURRENT_NOVEL("user does not have a current novel");

    private final String MESSAGE;

    NanoError(String message) {
        MESSAGE = message;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
