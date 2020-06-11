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

package org.elypia.elypiai.cleverbot;

import com.google.gson.GsonBuilder;
import io.reactivex.rxjava3.core.Single;
import okhttp3.*;
import org.elypia.elypiai.cleverbot.data.CleverTweak;
import org.elypia.elypiai.cleverbot.deserializers.CleverResponseDeserializer;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Cleverbot {

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
			logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
		}
	}

	private final String apiKey;
	private final CleverbotService service;

	/**
	 * @param apiKey API key recieved upon signing up.
	 */
	public Cleverbot(String apiKey) {
		this(baseUrl, apiKey);
	}

	/**
	 * Creates the Cleverbot object in order to make requests
	 * to the cleverbot API, apikey can be obtained from
	 * cleverbot website for free.
	 *
	 * @param baseUrl The URL to send HTTP requests too.
	 * @param apiKey API key recieved upon signing up.
	 * @see <a href="https://www.cleverbot.com/api/">cleverbot</a>
	 */
	public Cleverbot(URL baseUrl, String apiKey) {
		this.apiKey = apiKey;

		OkHttpClient client = HttpClientSingleton.getBuilder()
			.addInterceptor((chain) -> {
				Request request = chain.request();
				HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).addQueryParameter("wrapper", "Elypiai").build();
				request = request.newBuilder().url(url).build();
				return chain.proceed(request);
			})
			.build();

		GsonBuilder builder = new GsonBuilder()
			.registerTypeAdapter(CleverResponse.class, new CleverResponseDeserializer());

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(builder.create()))
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.build()
			.create(CleverbotService.class);
	}

	public Single<CleverResponse> say(String input) {
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
	public Single<CleverResponse> say(String input, String cs) {
		return say(input, cs, new EnumMap<>(CleverTweak.class));
	}

	public Single<CleverResponse> say(String input, String cs, Map<CleverTweak, Integer> tweaks) {
		Objects.requireNonNull(tweaks);

		Integer wacky = tweaks.get(CleverTweak.WACKY);
		Integer talkitive = tweaks.get(CleverTweak.TALKATIVE);
		Integer attentive = tweaks.get(CleverTweak.ATTENTIVE);

		return service.say(input, cs, wacky, talkitive, attentive);
	}

	public String getApiKey() {
		return apiKey;
	}
}
