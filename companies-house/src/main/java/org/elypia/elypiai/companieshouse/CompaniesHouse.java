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

package org.elypia.elypiai.companieshouse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import org.elypia.elypiai.companieshouse.models.RegisteredOfficeAddress;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.BasicAuthorizationInterceptor;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.Objects;

public class CompaniesHouse {

    private static final Logger logger = LoggerFactory.getLogger(CompaniesHouse.class);

    /**
     * The default URL we call too. <br>
     * Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://api.companieshouse.gov.uk/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    private final String apiKey;
    private final CompaniesHouseService service;

    /**
     * Creates an instance of the CompaniesHouse API.
     * You can use this to get information on British companies.
     *
     * @param 	apiKey	The API obtained from the osu! website.
     * @see <a href="https://beta.companieshouse.gov.uk/">Companies House Website</a>
     */
    public CompaniesHouse(String apiKey) {
        this(apiKey, baseUrl);
    }

    public CompaniesHouse(String apiKey, URL baseUrl) {
        this(
            apiKey,
            baseUrl,
            HttpClientSingleton.getBuilder().addInterceptor(new BasicAuthorizationInterceptor(apiKey)).build()
        );
    }

    public CompaniesHouse(String apiKey, URL baseUrl, OkHttpClient client) {
        this.apiKey = Objects.requireNonNull(apiKey);
        Objects.requireNonNull(client);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CompaniesHouseService.class);
    }

    public Single<RegisteredOfficeAddress> getRegisteredOfficeAddress(int companyId) {
        return service.getCompanyRegisteredOffice(companyId);
    }

    public String getApiKey() {
        return apiKey;
    }
}
