package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.common.core.*;
import com.elypia.elypiai.common.core.ext.WrapperExtension;
import com.elypia.elypiai.common.gson.GsonService;
import org.slf4j.*;
import retrofit2.*;

import java.net.*;

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
