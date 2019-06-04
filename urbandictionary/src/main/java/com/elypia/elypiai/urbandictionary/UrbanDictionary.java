package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.common.core.*;
import com.elypia.elypiai.common.core.ext.WrapperExtension;
import com.elypia.elypiai.common.gson.GsonService;
import org.slf4j.*;
import retrofit2.*;

import java.net.*;

public class UrbanDictionary extends ApiWrapper {

	private static final Logger logger = LoggerFactory.getLogger(UrbanDictionary.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("http://api.urbandictionary.com/");
		} catch (MalformedURLException ex) {
			logger.error("Failed to initialize UrbanDictionary.", ex);
		}
	}

	private UrbanDictionaryService service;

	public UrbanDictionary(WrapperExtension... exts) {
		this(BASE_URL, exts);
	}

	public UrbanDictionary(URL url, WrapperExtension... exts) {
        super(exts);
		service = new Retrofit.Builder()
			.baseUrl(url.toString())
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
	 */
	public RestAction<DefineResult> define(String term) {
		Call<DefineResult> call = service.define(term);
		return new RestAction<>(call);
	}
}
