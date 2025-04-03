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

package fun.falco.elypiai.orna;

import java.util.List;

import fun.falco.elypiai.orna.entities.Achievement;
import fun.falco.elypiai.orna.entities.Item;
import fun.falco.elypiai.orna.entities.Monster;
import fun.falco.elypiai.orna.entities.Npc;
import fun.falco.elypiai.orna.entities.Pet;
import fun.falco.elypiai.orna.entities.Quest;
import fun.falco.elypiai.orna.entities.SkillDetails;
import fun.falco.elypiai.orna.entities.Specialization;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public interface OrnaService {

    @POST("item")
    Single<List<Item>> getItems();

    @POST("assess")
    Single<Void> getAssess();

    @POST("class")
    Single<List<Void>> getClasses();

    @POST("specialization")
    Single<List<Specialization>> getSpecializations();

    @POST("skill")
    Single<List<SkillDetails>> getSkills();

    @POST("pet")
    Single<List<Pet>> getPets();

    @POST("monster")
    Single<List<Monster>> getMonsters();

    @POST("quest")
    Single<List<Quest>> getQuests();

    @POST("achievement")
    Single<List<Achievement>> getAchievements();

    @POST("npc")
    Single<List<Npc>> getNpcs();
}
