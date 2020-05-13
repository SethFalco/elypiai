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

package org.elypia.elypiai.urbandictionary;

import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.ext.WrapperExtension;
import org.elypia.elypiai.common.gson.GsonService;
import org.slf4j.*;
import retrofit2.*;

import java.net.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class UrbanDictionary extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(UrbanDictionary.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL baseUrl;

	static {
		try {
			baseUrl = new URL("http://api.urbandictionary.com/");
		} catch (MalformedURLException ex) {
			logger.error("Failed to initialize UrbanDictionary.", ex);
		}
	}

	private UrbanDictionaryService service;

	public UrbanDictionary() {
		this((WrapperExtension[])null);
	}

	public UrbanDictionary(WrapperExtension... exts) {
		this(baseUrl, exts);
	}

	public UrbanDictionary(URL url, WrapperExtension... exts) {
        super(exts);
		service = new Retrofit.Builder()
			.baseUrl(url)
			.client(RequestService.withExtensionInterceptor(this))
			.addConverterFactory(GsonService.getInstance())
			.build()
			.create(UrbanDictionaryService.class);
	}

	/**
	 * Returns a defintion from Urban Dictionary if result(s)
	 * are found matching exactly the word or phrase provided. <br>
	 * Possible null: Returns null if no definitions are found.
	 *
	 * @param term The word or phrase to be defined.
	 * @return RestAction to request definition of the word.
	 */
	public RestAction<DefineResult> define(String term) {
		Call<DefineResult> call = service.define(term);
		return new RestAction<>(call);
	}
}
