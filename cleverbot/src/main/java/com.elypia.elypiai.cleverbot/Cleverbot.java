package com.elypia.elypiai.cleverbot;

import com.elypia.elypiai.cleverbot.data.CleverTweak;
import com.elypia.elypiai.cleverbot.deserializers.CleverResponseDeserializer;
import com.elypia.elypiai.cleverbot.impl.CleverbotService;
import com.elypia.elypiai.common.Elypiai;
import com.elypia.elypiai.common.RequestService;
import com.elypia.elypiai.common.RestAction;
import com.google.gson.GsonBuilder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cleverbot {

	private static final Logger logger = LoggerFactory.getLogger(Cleverbot.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://www.cleverbot.com/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private final String API_KEY;
	private final CleverbotService service;

	public Cleverbot(String apiKey) {
		this(BASE_URL, apiKey);
	}

	/**
	 * Creates the Cleverbot object in order to make requests
	 * to the cleverbot API, apikey can be obtained from
	 * cleverbot website for free.
	 *
	 * @param	apiKey 	API key recieved upon signing up.
	 * @see <a href="https://www.cleverbot.com/api/">cleverbot</a>
	 */
	public Cleverbot(URL baseUrl, String apiKey) {
		API_KEY = apiKey;

		OkHttpClient client = RequestService.getBuilder().addInterceptor((chain) -> {
			Request request = chain.request();
			HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).addQueryParameter("wrapper", "Elypiai").build();
			request = request.newBuilder().url(url).build();
			return chain.proceed(request);
		}).build();

		GsonBuilder builder = new GsonBuilder()
			.registerTypeAdapter(CleverResponse.class, new CleverResponseDeserializer());

		service = new Retrofit.Builder()
			.baseUrl(baseUrl.toString())
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
	 * @param cs		The Cleverbot state from previously.
	 * @param input		The text to send to cleverbot.
	 */
	public RestAction<CleverResponse> say(String input, String cs) {
		return say(input, cs, new HashMap<>());
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
