/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

import com.google.gson.*;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import org.elypia.elypiai.companieshouse.models.*;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.BasicAuthorizationInterceptor;
import org.elypia.retropia.gson.deserializers.TemporalDeserializer;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.time.LocalDate;
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
     * @param apiKey The API key obtained from the Companies House website.
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

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new TemporalDeserializer("yyyy-MM-dd"))
            .create();

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CompaniesHouseService.class);
    }

    /**
     * @param companyNumber The company number of the organization.
     * @return The public company profile for the specified company number.
     */
    public Single<Company> getCompany(String companyNumber) {
        Objects.requireNonNull(companyNumber);
        return service.getCompany(companyNumber);
    }

    /**
     * @param companyNumber The company number of the organization.
     * @return The companies registered address if the company exists.
     */
    public Single<RegisteredOfficeAddress> getRegisteredOfficeAddress(String companyNumber) {
        Objects.requireNonNull(companyNumber);
        return service.getCompanyRegisteredOffice(companyNumber);
    }

    /**
     * @return The read-only API key used for authentication requests.
     */
    public String getApiKey() {
        return apiKey;
    }
}
