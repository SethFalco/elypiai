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

package org.elypia.elypiai.cleverbot;

import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.elypia.elypiai.cleverbot.data.CleverTweak;
import org.elypia.elypiai.cleverbot.deserializers.CleverResponseDeserializer;
import org.elypia.elypiai.cleverbot.impl.CleverbotService;
import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.ext.*;
import org.slf4j.*;
import retrofit2.Call;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Cleverbot extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(Cleverbot.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL baseUrl;

	static {
		try {
			baseUrl = new URL("https://www.cleverbot.com/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private final String API_KEY;
	private final CleverbotService service;

	public Cleverbot(String apiKey) {
		this(apiKey, (WrapperExtension[])null);
	}

	/**
	 * @param apiKey API key recieved upon signing up.
	 * @param exts Any extensions to add to this wrapper.
	 */
	public Cleverbot(String apiKey, WrapperExtension... exts) {
		this(baseUrl, apiKey, exts);
	}

	/**
	 * Creates the Cleverbot object in order to make requests
	 * to the cleverbot API, apikey can be obtained from
	 * cleverbot website for free.
	 *
	 * @param baseUrl The URL to send HTTP requests too.
	 * @param apiKey API key recieved upon signing up.
	 * @param exts Any extensions to add to this wrapper.
	 * @see <a href="https://www.cleverbot.com/api/">cleverbot</a>
	 */
	public Cleverbot(URL baseUrl, String apiKey, WrapperExtension... exts) {
		super(exts);
		API_KEY = apiKey;

		OkHttpClient client = RequestService.getBuilder()
			.addInterceptor((chain) -> {
				Request request = chain.request();
				HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).addQueryParameter("wrapper", "Elypiai").build();
				request = request.newBuilder().url(url).build();
				return chain.proceed(request);
			})
			.addInterceptor(new ExtensionInterceptor(this))
			.build();

		GsonBuilder builder = new GsonBuilder()
			.registerTypeAdapter(CleverResponse.class, new CleverResponseDeserializer());

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(builder.create()))
			.build()
			.create(CleverbotService.class);
	}

	public RestAction<CleverResponse> say(String input) {
		return say(input, null);
	}

	/**
	 * Returns the cleverbot response to the given input, however allows
	 * the user to store and pass their CS value themself rather than rely
	 * on the Map used internally in this API, encouraged for longer
	 * conversations you may wish to continue after retarting the application.
	 *
	 * @param cs The Cleverbot state from previously.
	 * @param input	The text to send to cleverbot.
	 * @return A rest action that represents this HTTP request.
	 */
	public RestAction<CleverResponse> say(String input, String cs) {
		return say(input, cs, new EnumMap<>(CleverTweak.class));
	}

	public RestAction<CleverResponse> say(String input, String cs, Map<CleverTweak, Integer> tweaks) {
		Objects.requireNonNull(tweaks);

		Integer wacky = tweaks.get(CleverTweak.WACKY);
		Integer talkitive = tweaks.get(CleverTweak.TALKATIVE);
		Integer attentive = tweaks.get(CleverTweak.ATTENTIVE);

		Call<CleverResponse> call = service.say(input, cs, wacky, talkitive, attentive);
		return new RestAction<>(call);
	}

	public String getApiKey() {
		return API_KEY;
	}
}
