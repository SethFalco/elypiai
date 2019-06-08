package com.elypia.elypiai.twitch;

import com.elypia.elypiai.common.core.*;
import com.elypia.elypiai.common.core.data.AuthenticationType;
import com.elypia.elypiai.common.core.ext.ExtensionInterceptor;
import com.elypia.elypiai.common.gson.GsonService;
import com.elypia.elypiai.twitch.data.Scope;
import com.elypia.elypiai.twitch.deserializers.TwitchUserDeserializer;
import com.elypia.elypiai.twitch.entity.User;
import com.elypia.elypiai.twitch.service.*;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import org.slf4j.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Twitch extends ApiWrapper {

	private static Logger logger = LoggerFactory.getLogger(Twitch.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://api.twitch.tv/helix/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private static URL AUTH_URL;

	static {
		try {
			AUTH_URL = new URL("https://id.twitch.tv/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private final String CLIENT_ID;
	private final String CLIENT_SECRET;
	private final AuthenticationType AUTH_TYPE;
	private final Scope[] SCOPES;

	private TwitchService service;
	private TwitchAppService appService;
	private AccessToken token;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param clientId API key obtained from Twitch website.
	 */
	public Twitch(String clientId, String clientSecret) throws IOException {
		this(clientId, clientSecret, AuthenticationType.BEARER);
	}

	public Twitch(String clientId, String clientSecret, AuthenticationType grantType) throws IOException {
		this(clientId, clientSecret, grantType, (Scope[])null);
	}

	public Twitch(String clientId, String clientSecret, AuthenticationType grantType, Scope... scopes) throws IOException {
		this(BASE_URL, AUTH_URL, clientId, clientSecret, grantType, scopes);
	}

	public Twitch(URL baseUrl, URL authUrl, String clientID, String clientSecret, AuthenticationType grantType, Scope... scopes) throws IOException {
		Objects.requireNonNull(baseUrl);
		Objects.requireNonNull(authUrl);
		CLIENT_ID = Objects.requireNonNull(clientID);
		CLIENT_SECRET = Objects.requireNonNull(clientSecret);
		AUTH_TYPE = Objects.requireNonNull(grantType);
		SCOPES = scopes;

		initTwitchAppService(authUrl);
		initTwitchService(baseUrl);
	}

	private void initTwitchAppService(URL authUrl) throws IOException {
		appService = new Retrofit.Builder()
			.baseUrl(authUrl)
			.client(RequestService.withExtensionInterceptor(this))
			.addConverterFactory(GsonService.getInstance())
			.build()
			.create(TwitchAppService.class);

		Optional<AccessToken> optToken = getToken().complete();

		this.token = optToken.orElseThrow(() ->
			new IllegalStateException("Client credentials were invalid, refer to Twitch and generate new ones.")
		);
	}

	private void initTwitchService(URL baseUrl) {
		OkHttpClient client = RequestService.getBuilder()
			.addInterceptor((chain) -> {
				var req = chain.request().newBuilder()
					.addHeader("Authorization", "Bearer " + token.getToken())
					.build();

				return chain.proceed(req);
			})
			.addInterceptor(new ExtensionInterceptor(this))
			.build();

		GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(new TypeToken<List<User>>(){}.getType(), new TwitchUserDeserializer(this));

		service = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.build()
			.create(TwitchService.class);
	}

	/**
	 * Use the users Client-Id and Client-Secret
	 * to create an app access token;
	 *
	 * @return A new access token.
	 */
	private RestAction<AccessToken> getToken() {
		Call<AccessToken> call = appService.getToken(
			CLIENT_ID,
			CLIENT_SECRET,
			AUTH_TYPE.getApiName(),
			Scope.forQuery(SCOPES)
		);

		return new RestAction<>(call);
	}

	public RestAction<List<User>> getUsers(TwitchQuery query) {
		if (!query.getGames().isEmpty())
			throw new IllegalArgumentException("Can not query users by game id.");

		if (query.getTotal() > 100)
			throw new IllegalArgumentException("Can not query over 100 entities at once.");

		Call<List<User>> call = service.getUsers(query.getUserIds(), query.getUsernames());
		return new RestAction<>(call);
	}

	public StreamPaginator getStreams(TwitchQuery query, int limit) {
		if (limit > 100)
			throw new IllegalArgumentException("Can not retrieve more than 100 users per page.");

		return new StreamPaginator(this, query, limit);
	}

	public String getClientId() {
		return CLIENT_ID;
	}

	/**
	 * Despite usually keeping service internal, the Twitch API has
	 * several components so there is an exception so those components
	 * can also access the TwitchService.
	 *
	 * @return The service class for making the actual Twitch API calls.
	 */
	public TwitchService getService() {
		return service;
	}
}
