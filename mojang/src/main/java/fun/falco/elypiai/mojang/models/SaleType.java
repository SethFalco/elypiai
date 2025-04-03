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

package fun.falco.elypiai.mojang.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public enum SaleType {

    @SerializedName("item_sold_minecraft")
    ITEM_SOLD_MINECRAFT,

    @SerializedName("prepaid_card_redeemed_minecraft")
    PREPAID_CARD_REDEEMED_MINECRAFT,

    @SerializedName("item_sold_cobalt")
    ITEM_SOLD_COBALTS,

    @SerializedName("item_sold_scrolls")
    ITEM_SOLD_SCROLLS,

    @SerializedName("prepaid_card_redeemed_cobalt")
    PREPAID_CARD_REDEEMED_COBALT,

    @SerializedName("item_sold_dungeons")
    ITEM_SOLD_DUNGEONS
}
