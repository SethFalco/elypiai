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

package org.elypia.elypiai.weblate;

import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.weblate.models.Statistics;
import retrofit2.http.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public interface WeblateService {

    @GET("users")
    Single<Object> getUsers();

    @GET("users/{username}")
    Single<Object> getUser(
      @Path("username") String username
    );

    @GET("groups")
    Single<Object> getGroups();

    @GET("roles")
    Single<Object> getRoles();

    @GET("projects")
    Single<Object> getProjects();

    @GET("projects/{project_name}/statistics")
    Single<Statistics> getProjectStatistics(
        @Path("project_name") String projectName
    );

    @GET("components")
    Single<Object> getComponents();

    @GET("translations")
    Single<Object> getTranslations();

    @GET("languages")
    Single<Object> getLanguages();

    @GET("languages/{language_name}/statistics")
    Single<Statistics> getLanguageStatistics(
        @Path("language_name") String projectName
    );

    @GET("component-lists")
    Single<Object> getComponentLists();

    @GET("changes")
    Single<Object> getChanges();

    @GET("units")
    Single<Object> getUnits();

    @GET("units/{unit_id}")
    Single<Object> getUnit(
        @Path("unit_id") int unitId
    );

    @GET("screenshots")
    Single<Object> getScreenshots();
}
