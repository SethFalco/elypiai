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

package org.elypia.elypiai.nanowrimo;

import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.ex.FriendlyException;
import org.elypia.elypiai.common.core.ext.WrapperExtension;
import org.elypia.elypiai.nanowrimo.data.NanoError;
import org.slf4j.*;
import retrofit2.*;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.xml.bind.*;
import java.net.*;
import java.util.Objects;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class Nanowrimo extends ApiWrapper {

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

	public Nanowrimo(WrapperExtension... exts) {
		this(BASE_URL, exts);
	}

	public Nanowrimo(URL baseUrl, WrapperExtension... exts) {
		super(exts);

		try {
			JAXBContext context = JAXBContext.newInstance(Writer.class, WordCountEntry.class);

			service = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(RequestService.withExtensionInterceptor(this))
				.addConverterFactory(JaxbConverterFactory.create(context))
				.addConverterFactory(ScalarsConverterFactory.create())
				.build()
				.create(NanowrimoService.class);
		} catch (JAXBException ex) {
			logger.error("Failed to create JAXBContext", ex);
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
