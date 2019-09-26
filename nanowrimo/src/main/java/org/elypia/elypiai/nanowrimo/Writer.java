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

package org.elypia.elypiai.nanowrimo;

import org.elypia.elypiai.nanowrimo.data.NanoError;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@XmlRootElement(name = "wchistory")
public class Writer {

	private static final String PROFILE_URL = "https://nanowrimo.org/participants/";

	@XmlElement(name = "uname")
	private String username;

	@XmlElement(name = "user_wordcount")
	private int wordCount;

	@XmlElement(name = "uid")
	private int id;

	@XmlElement(name = "winner")
	private boolean winner;

	@XmlElementWrapper(name = "wordcounts")
	@XmlElement(name = "wcentry")
	private List<WordCountEntry> entries;

	@XmlElement(name = "error")
	private NanoError error;

	public String getUsername() {
		return username;
	}

	public int getWordCount() {
		return wordCount;
	}

	public int getId() {
		return id;
	}

	public boolean isWinner() {
		return winner;
	}

	public String getUrl() {
		return PROFILE_URL + username.replace(" ", "-");
	}

	public List<WordCountEntry> getEntries() {
		if (entries == null)
			entries = new ArrayList<>();

		return entries;
	}

	protected NanoError getError() {
		return error;
	}
}
