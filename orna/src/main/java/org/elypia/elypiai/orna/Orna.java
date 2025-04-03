/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.orna;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.elypia.elypiai.orna.entities.Achievment;
import org.elypia.elypiai.orna.entities.Entity;
import org.elypia.elypiai.orna.entities.Item;
import org.elypia.elypiai.orna.entities.Monster;
import org.elypia.elypiai.orna.entities.Npc;
import org.elypia.elypiai.orna.entities.Pet;
import org.elypia.elypiai.orna.entities.Quest;
import org.elypia.elypiai.orna.entities.SkillDetails;
import org.elypia.elypiai.orna.entities.Specialization;
import org.elypia.retropia.core.HttpClientSingleton;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Unofficial API wrapper for Java for Orna Guide.
 *
 * @author seth@elypia.org (Seth Falco)
 * @see <a href="https://orna.guide/">https://orna.guide/</a>
 */
public class Orna {

    /** Endpoint that stores all static resources for Orna Guide. */
    private static final String RESOURCE_URL = "https://orna.guide/static/orna/img/";

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://orna.guide/api/v1/");
        } catch (MalformedURLException ex) {}
    }

    private OrnaService service;

    public Orna() {
        this(baseUrl);
    }

    public Orna(URL baseUrl) {
        this(baseUrl, HttpClientSingleton.getClient());
    }

    public Orna(URL baseUrl, OkHttpClient client) {
        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(OrnaService.class);
    }

    public Single<List<Item>> getItems() {
        return service.getItems();
    }

    public Single<List<Specialization>> getSpecializations() {
        return service.getSpecializations();
    }

    public Single<List<SkillDetails>> getSkills() {
        return service.getSkills();
    }

    public Single<List<Pet>> getPets() {
        return service.getPets();
    }

    public Single<List<Monster>> getMonsters() {
        return service.getMonsters();
    }

    public Single<List<Quest>> getQuests() {
        return service.getQuests();
    }

    public Single<List<Achievment>> getAchievments() {
        return service.getAchievments();
    }

    public Single<List<Npc>> getNpcs() {
        return service.getNpcs();
    }

    /**
     * @param image
     *     Image attribute of a {@link Entity} that has an image associated with
     *     it.
     * @return URL that can display the image for this entity.
     */
    public static String getStaticResource(String image) {
        return RESOURCE_URL + image;
    }
}
