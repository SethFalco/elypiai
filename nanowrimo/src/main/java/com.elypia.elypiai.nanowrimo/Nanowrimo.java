package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.common.Elypiai;
import com.elypia.elypiai.common.FriendlyException;
import com.elypia.elypiai.common.RequestService;
import com.elypia.elypiai.common.RestAction;
import com.elypia.elypiai.common.jaxb.adapters.DateAdapter;
import com.elypia.elypiai.nanowrimo.data.NanoError;
import com.elypia.elypiai.nanowrimo.impl.NanowrimoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Nanowrimo {

	private static final Logger logger = LoggerFactory.getLogger(Nanowrimo.class);

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://nanowrimo.org/");
		} catch (MalformedURLException ex) {
			logger.error(Elypiai.MALFORMED, ex);
		}
	}

	private NanowrimoService service;

	public Nanowrimo() {
		this(BASE_URL);
	}

	public Nanowrimo(URL baseUrl) {
		try {
			JAXBContext context = JAXBContext.newInstance(Writer.class);
			context.createMarshaller().setAdapter(new DateAdapter("yyyy-MM-dd"));

			service = new Retrofit.Builder()
				.baseUrl(baseUrl.toString())
				.client(RequestService.getInstance())
				.addConverterFactory(JaxbConverterFactory.create(context))
				.addConverterFactory(ScalarsConverterFactory.create())
				.build()
				.create(NanowrimoService.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public RestAction<Writer> getUser(String name) {
		return getUser(name, false);
    }

	public RestAction<Writer> getUser(String name, boolean history) {
		Objects.requireNonNull(name);

		String requestName = name.replace(" ", "-");
		Call<Writer> call = (history) ? service.getUserHistory(requestName) : service.getUser(requestName);
		RestAction<Writer> action = new RestAction<>(call);

		action.pipe(writer -> {
			NanoError error = writer.getError();

			if (error == null)
				return;

			String message = error.getMessage();

			switch (error) {
				case UNKNOWN: throw new FriendlyException(message, "An unknown error occured.", false);
				case USER_DOES_NOT_EXIST: throw new FriendlyException(message, "There is no user with that name.", true);
				case USER_DOES_NOT_HAVE_A_CURRENT_NOVEL: throw new FriendlyException(message, "This user currently doesn't have a novel.", true);
			}
		});

		return action;
	}
}
