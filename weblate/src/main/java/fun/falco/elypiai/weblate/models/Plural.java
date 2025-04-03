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

package fun.falco.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class Plural {

    /**
     * @see #getId()
     */
    @SerializedName("id")
    private int id;

    /**
     * @see #getSource()
     */
    @SerializedName("source")
    private int source;

    /**
     * @see #getNumber()
     */
    @SerializedName("number")
    private int number;

    /**
     * @see #getFormula()
     */
    @SerializedName("formula")
    private String formula;

    /**
     * @see #getType()
     */
    @SerializedName("type")
    private int type;

    public int getId() {
        return id;
    }

    public int getSource() {
        return source;
    }

    public int getNumber() {
        return number;
    }

    public String getFormula() {
        return formula;
    }

    public int getType() {
        return type;
    }
}
