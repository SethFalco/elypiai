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

package org.elypia.elypiai.yugioh;

import io.reactivex.rxjava3.core.Maybe;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class YuGiOh {

	private static final Logger logger = LoggerFactory.getLogger(YuGiOh.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
    private static URL baseUrl;

	static {
		try {
			baseUrl = new URL("http://yugiohprices.com/api/");
		} catch (MalformedURLException ex) {
			logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
		}
	}

	private YuGiOhService service;

	public YuGiOh() {
		this(baseUrl);
    }

    public YuGiOh(URL url) {
		service = new Retrofit.Builder()
			.baseUrl(url)
			.client(HttpClientSingleton.getBuilder().build())
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.build()
			.create(YuGiOhService.class);
    }

	/**
	 * Search the YuGiOh prices API for the YuGiOh card requested.
	 * This only returns a single card and the spelling of the card
	 * must be exact, including hythens (-) and spacing.
	 * Can return a monster or spell/trap card.
	 *
	 * @param name The YuGiOh card to search up, must match card name exactly.
	 */
    public Maybe<TradingCard> getCard(String name) {
		return service.getCard(name);
	}
}
