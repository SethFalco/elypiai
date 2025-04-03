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

package org.elypia.elypiai.mojang.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class SalesMetrics {

    /**
     * @see #getTotalSales()
     */
    @SerializedName("total")
    private int totalSales;

    /**
     * @see #getRecentSales()
     */
    @SerializedName("last24h")
    private int recentSales;

    /**
     * @see #getSalesPerSecond()
     */
    @SerializedName("saleVelocityPerSeconds")
    private double salesPerSecond;

    /**
     * @return Total number of sales.
     */
    public int getTotalSales() {
        return totalSales;
    }

    /**
     * @return Number of sales in the past 24 hours.
     */
    public int getRecentSales() {
        return recentSales;
    }

    /**
     * @return Sales velocity per second.
     */
    public double getSalesPerSecond() {
        return salesPerSecond;
    }
}
