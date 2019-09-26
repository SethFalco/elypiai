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

package org.elypia.elypiai.yugioh;

import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.ext.WrapperExtension;
import org.elypia.elypiai.common.gson.GsonService;
import org.slf4j.*;
import retrofit2.*;

import java.net.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class YuGiOh extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(YuGiOh.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
    private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("http://yugiohprices.com/api/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private YuGiOhService service;

	public YuGiOh(WrapperExtension... exts) {
		this(BASE_URL, exts);
    }

    public YuGiOh(URL url, WrapperExtension... exts) {
		super(exts);
		service = new Retrofit.Builder()
			.baseUrl(url)
			.client(RequestService.withExtensionInterceptor(this))
			.addConverterFactory(GsonService.getInstance())
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
    public RestAction<TradingCard> getCard(String name) {
		Call<TradingCard> call = service.getCard(name);
		return new RestAction<>(call);
	}
}
