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

package org.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.poe.data.StashType;

import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class Stash {

	@SerializedName("id")
	private String id;

	@SerializedName("public")
	private boolean isPublic;

	@SerializedName("accountName")
	private String accountName;

	@SerializedName("lastCharacterName")
	private String lastCharacterName;

	@SerializedName("stash")
	private String name;

	@SerializedName("stashType")
	private StashType stashType;

	@SerializedName("league")
	private String league;

	@SerializedName("items")
	private List<StashItem> items;

	public String getId() {
		return id;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getLastCharacterName() {
		return lastCharacterName;
	}

	public String getName() {
		return name;
	}

	public StashType getStashType() {
		return stashType;
	}

	public String getLeague() {
		return league;
	}

	public List<StashItem> getItems() {
		return items;
	}
}
