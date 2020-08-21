/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

package org.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

import java.awt.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum GemColor {

	UNKNOWN(null, null),

	@SerializedName("R")
	RED("R", Color.RED),

	@SerializedName("B")
	BLUE("B", Color.BLUE),

	@SerializedName("G")
	GREEN("G", Color.GREEN),

	@SerializedName("W")
	WHITE("W", Color.WHITE);

	private final String NAME;
	private final Color COLOR;

	GemColor(String name, Color color) {
		NAME = name;
		COLOR = color;
	}

	public String getName() {
		return NAME;
	}

	public Color getColor() {
		return COLOR;
	}

	public static GemColor get(String name) {
		for (GemColor color : GemColor.values()) {
			if (color.NAME.equals(name))
				return color;
		}

		return UNKNOWN;
	}
}
