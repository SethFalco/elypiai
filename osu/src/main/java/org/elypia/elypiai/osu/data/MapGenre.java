/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum MapGenre {

	UNKNOWN(-1),

	@SerializedName("0")
	ANY(0),

	@SerializedName("1")
	UNSPECIFIED(1),

	@SerializedName("2")
	VIDEO_GAME(2),

	@SerializedName("3")
	ANIME(3),

	@SerializedName("4")
	ROCK(4),

	@SerializedName("5")
	POP(5),

	@SerializedName("6")
	OTHER(6),

	@SerializedName("7")
	NOVELTY(7),

	@SerializedName("9")
	HIP_HOP(9),

	@SerializedName("10")
	ELECTRONIC(10);

	private final int ID;

	MapGenre(int id) {
		ID = id;
	}

	public int getId() {
		return ID;
	}
}
