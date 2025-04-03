/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.companieshouse;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;

import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.BasicAuthorizationInterceptor;
import org.elypia.retropia.gson.deserializers.TemporalDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fun.falco.elypiai.companieshouse.deserializers.MonthDayDeserializer;
import fun.falco.elypiai.companieshouse.models.Company;
import fun.falco.elypiai.companieshouse.models.RegisteredOfficeAddress;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompaniesHouse {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://api.companieshouse.gov.uk/");
        } catch (MalformedURLException ex) {}
    }

    private final String apiKey;
    private final CompaniesHouseService service;

    /**
     * Creates an instance of the CompaniesHouse API.
     * You can use this to get information on British companies.
     *
     * @param apiKey API key obtained from the Companies House website.
     * @see <a href="https://find-and-update.company-information.service.gov.uk/">Companies House Website</a>
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
            .registerTypeAdapter(MonthDay.class, new MonthDayDeserializer())
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
     * @param companyNumber Company number of the organization.
     * @return Public company profile for the specified company number.
     */
    public Single<Company> getCompany(String companyNumber) {
        Objects.requireNonNull(companyNumber);
        return service.getCompany(companyNumber);
    }

    /**
     * @param companyNumber Company number of the organization.
     * @return Companies registered address if the company exists.
     */
    public Single<RegisteredOfficeAddress> getRegisteredOfficeAddress(String companyNumber) {
        Objects.requireNonNull(companyNumber);
        return service.getCompanyRegisteredOffice(companyNumber);
    }

    /**
     * @return Read-only API key used for authentication requests.
     */
    public String getApiKey() {
        return apiKey;
    }
}
