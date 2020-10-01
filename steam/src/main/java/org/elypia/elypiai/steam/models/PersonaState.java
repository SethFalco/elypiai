/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.steam.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum PersonaState {

	UNKNOWN,

	@SerializedName("0")
	OFFLINE,

	@SerializedName("1")
	ONLINE,

	@SerializedName("2")
	BUSY,

	@SerializedName("3")
	AWAY,

	@SerializedName("4")
	SNOOZE,

	@SerializedName("5")
	LOOKING_TO_TRADE,

	@SerializedName("6")
	LOOKING_TO_PLAY
}
