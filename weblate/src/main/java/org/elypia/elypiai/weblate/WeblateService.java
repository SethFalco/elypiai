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

package org.elypia.elypiai.weblate;

import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.weblate.models.Change;
import org.elypia.elypiai.weblate.models.Component;
import org.elypia.elypiai.weblate.models.ComponentStatistics;
import org.elypia.elypiai.weblate.models.Language;
import org.elypia.elypiai.weblate.models.LanguageStatistics;
import org.elypia.elypiai.weblate.models.Project;
import org.elypia.elypiai.weblate.models.Results;
import org.elypia.elypiai.weblate.models.Screenshot;
import org.elypia.elypiai.weblate.models.Unit;
import org.elypia.elypiai.weblate.models.WeblateGroup;
import org.elypia.elypiai.weblate.models.WeblateRepository;
import org.elypia.elypiai.weblate.models.WeblateRole;
import org.elypia.elypiai.weblate.models.WeblateUser;
import retrofit2.adapter.rxjava3.Result;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

/**
 * Encapsultes all RESTful endpoints that can be called
 * on the Weblate API.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.2
 */
public interface WeblateService {

    @GET("users")
    Single<Results<WeblateUser>> getUsers();

    @POST("users")
    Single<Object> postUser();

    @GET("users/{username}")
    Single<WeblateUser> getUser(
        @Path("username") String username
    );

    @PUT("users/{username}")
    Single<WeblateUser> putUser(
        @Path("username") String username
    );

    @PATCH("users/{username}")
    Single<WeblateUser> patchUser(
        @Path("username") String username
    );

    @DELETE("users/{username}")
    Single<Object> deleteUser(
        @Path("username") String username
    );

    /**
     * Associate groups with a user.
     *
     * @return
     */
    @POST("users/{username}/groups")
    Single<Object> postUserGroup(
        @Path("username") String username,
        @Field("group_id") int groupId
    );

    @GET("users/{username}/notifications")
    Single<Object> getUserNotifications(
        @Path("username") String username
    );

    @POST("users/{username}/notifications")
    Single<Object> postUserNotifications(
        @Path("username") String username
    );

    @GET("users/{username}/notifications/{subscription_id}")
    Single<Object> getUserNotificationSubsscription(
        @Path("username") String username,
        @Path("subscription_id") String subscriptionId
    );

    @PUT("users/{username}/notifications/{subscription_id}")
    Single<Object> putUserNotificationSubscriptions(
        @Path("username") String username,
        @Path("subscription_id") String subscriptionId
    );

    @PATCH("users/{username}/notifications/{subscription_id}")
    Single<Object> patchUserNotificationSubscription(
        @Path("username") String username,
        @Path("subscription_id") String subscriptionId
    );

    @DELETE("users/{username}/notifications/{subscription_id}")
    Single<Object> deleteUserNotificationSubscription(
        @Path("username") String username,
        @Path("subscription_id") String subscriptionId
    );

    @GET("groups")
    Single<Results<WeblateGroup>> getGroups();

    @POST("groups")
    Single<Object> postGroup();

    @GET("groups/{group_id}")
    Single<WeblateGroup> getGroup(
        @Path("group_id") int groupId
    );

    @PUT("groups/{group_id}")
    Single<Object> putGroup(
        @Path("group_id") int groupId
    );

    @PATCH("groups/{group_id}")
    Single<Object> patchGroup(
        @Path("group_id") int groupId
    );

    @DELETE("groups/{group_id}")
    Single<Object> deleteGroup(
        @Path("group_id") int groupId
    );

    @POST("groups/{group_id}/roles")
    Single<Object> postGroupRoles(
        @Path("group_id") int groupId
    );

    @POST("groups/{group_id}/components")
    Single<Object> postGroupComponents(
        @Path("group_id") int groupId
    );

    @DELETE("groups/{group_id}/components/{component_id}")
    Single<Object> deleteGroupComponent(
        @Path("group_id") int groupId,
        @Path("component_id") int componentId
    );

    @POST("groups/{group_id}/projects")
    Single<Object> postGroupProjects(
        @Path("group_id") int groupId
    );

    @DELETE("groups/{group_id}/projects/{project_id}")
    Single<Object> deleteGroupProject(
        @Path("group_id") int groupId,
        @Path("project_id") String projectId
    );

    @POST("groups/{group_id}/languages")
    Single<Object> postGroupLanguages(
        @Path("group_id") int groupId
    );

    @DELETE("groups/{group_id}/languages/{language_code}")
    Single<Object> deleteGroupLanguage(
        @Path("group_id") int groupId,
        @Path("language_code") String languageCode
    );

    @POST("groups/{group_id}/componentlists")
    Single<Object> postGroupComponentLists(
        @Path("group_id") int groupId
    );

    @DELETE("groups/{group_id}/componentlists/{component_list_id}")
    Single<Object> deleteGroupComponentLists(
        @Path("group_id") int groupId,
        @Path("component_list_id") String componentListId
    );

    @GET("roles")
    Single<Results<WeblateRole>> getRoles();

    @POST("roles")
    Single<Object> postRoles();

    @GET("roles/{role_id}")
    Single<WeblateRole> getRole(
        @Path("role_id") int roleId
    );

    @PUT("roles/{role_id}")
    Single<Object> putRole(
        @Path("role_id") int roleId
    );

    @PATCH("roles/{role_id}")
    Single<Object> patchRole(
        @Path("role_id") int roleId
    );

    @DELETE("roles/{role_id}")
    Single<Object> deleteRole(
        @Path("role_id") int roleId
    );

    @GET("languages")
    Single<Results<Language>> getLanguages();

    @POST("languages")
    Single<Object> postLanguage();

    @GET("languages/{language_code}")
    Single<Language> getLanguage(
        @Path("language_code") String languageCode
    );

    @PUT("roles/{language_code}")
    Single<Object> putLanguage(
        @Path("language_code") String languageCode
    );

    @PATCH("roles/{language_code}")
    Single<Object> patchLanguage(
        @Path("language_code") String languageCode
    );

    @DELETE("roles/{language_code}")
    Single<Object> deleteLanguage(
        @Path("language_code") String languageCode
    );

    @GET("languages/{language_code}/statistics")
    Single<ComponentStatistics> getLanguageStatistics(
        @Path("language_code") String languageCode
    );

    @GET("projects")
    Single<Results<Project>> getProjects();

    @POST("projects")
    Single<Object> postProject();

    @GET("projects/{project_slug}")
    Single<Project> getProject(
        @Path("project_slug") String projectSlug
    );

    @DELETE("projects/{project_slug}")
    Single<Object> deleteProject(
        @Path("project_slug") String projectSlug
    );

    @GET("projects/{project_slug}/changes")
    Single<Results<Change>> getProjectChanges(
        @Path("project_slug") String projectSlug
    );

    @GET("projects/{project_slug}/repository")
    Single<WeblateRepository> getProjectRepository(
        @Path("project_slug") String projectSlug
    );

    @POST("projects/{project_slug}/repository")
    Single<Object> postProjectRepository(
        @Path("project_slug") String projectSlug
    );

    @GET("projects/{project_slug}/components")
    Single<Results<Component>> getProjectComponents(
        @Path("project_slug") String projectSlug
    );

    @POST("projects/{project_slug}/components")
    Single<Object> postProjectComponents(
        @Path("project_slug") String projectSlug
    );

    @GET("projects/{project_slug}/languages")
    Single<List<LanguageStatistics>> getProjectLanguages(
        @Path("project_slug") String projectSlug
    );

    @GET("projects/{project_slug}/statistics")
    Single<ComponentStatistics> getProjectStatistics(
        @Path("project_slug") String projectSlug
    );

    @GET("components")
    Single<Object> getComponents();

    @GET("components/{project_slug}/{component_slug}")
    Single<Object> getComponent(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @PATCH("components/{project_slug}/{component_slug}")
    Single<Object> patchComponent(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @PUT("components/{project_slug}/{component_slug}")
    Single<Object> putComponent(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @DELETE("components/{project_slug}/{component_slug}")
    Single<Object> deleteComponent(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/changes")
    Single<Object> getComponentChanges(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/screenshots")
    Single<Object> getComponentScreenshots(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/lock")
    Single<Object> getComponentLock(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("components/{project_slug}/{component_slug}/lock")
    Single<Object> postComponentLock(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/repository")
    Single<Object> getComponentRepository(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("components/{project_slug}/{component_slug}/repository")
    Single<Object> postComponentRepository(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    /**
     * Returns a literal string of the monolingual base of the component.
     *
     * @param projectSlug Project's slug.
     * @param componentSlug Component's slug.
     * @return Base language file.
     */
    @GET("components/{project_slug}/{component_slug}/monolingual_base")
    Single<Object> getComponentMonolingualBase(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/new_template")
    Single<Object> getComponentNewTemplate(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/translations")
    Single<Object> getComponentTranslations(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("components/{project_slug}/{component_slug}/translations")
    Single<Object> postComponentTranslations(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("components/{project_slug}/{component_slug}/statistics")
    Single<Object> getComponentStatistics(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("translations")
    Single<Object> getTranslations();

    @GET("translations/{project_slug}/{component_slug}/{language_code}")
    Single<Object> getComponentLanguage(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @DELETE("translations/{project_slug}/{component_slug}/{language_code}")
    Single<Object> deleteComponentLanguageStatistics(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("translations/{project_slug}/{component_slug}/{language_code}/changes")
    Single<Object> getComponentLanguageChanges(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("translations/{project_slug}/{component_slug}/{language_code}/units")
    Single<Object> getComponentLanguageUnits(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("translations/{project_slug}/{component_slug}/{language_code}/units")
    Single<Object> postComponentLanguageUnits(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("translations/{project_slug}/{component_slug}/{language_code}/autotranslate")
    Single<Object> postComponentLanguageAutoTranslate(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("translations/{project_slug}/{component_slug}/{language_code}/file")
    Single<Object> getComponentLanguageAutoFile(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("translations/{project_slug}/{component_slug}/{language_code}/file")
    Single<Object> postComponentLanguageAutoFile(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("translations/{project_slug}/{component_slug}/{language_code}/repository")
    Single<Object> getComponentLanguageRepository(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @POST("translations/{project_slug}/{component_slug}/{language_code}/repository")
    Single<Object> postComponentLanguageRepository(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("translations/{project_slug}/{component_slug}/{language_code}/statistics")
    Single<Object> getComponentLanguageStatistics(
        @Path("project_slug") String projectSlug,
        @Path("component_slug") String componentSlug
    );

    @GET("units")
    Single<Results<Unit>> getUnits();

    @GET("units/{unit_id}")
    Single<Unit> getUnit(
        @Path("unit_id") int unitId
    );

    @GET("changes")
    Single<Result<Change>> getChanges();

    @GET("changes/{change_id}")
    Single<Change> getChange(
        @Path("change_id") int changeId
    );

    @GET("screenshots")
    Single<Result<Screenshot>> getScreenshots();

    @GET("screenshots/{screenshot_id}")
    Single<Result<Screenshot>> getScreenshot(
        @Path("screenshot_id") int screenshotId
    );

    @GET("screenshots/{screenshot_id}/file")
    Single<Object> getScreenshotFile(
        @Path("screenshot_id") int screenshotId
    );

    @POST("screenshots/{screenshot_id}/file")
    Single<Object> postScreenshotFile(
        @Path("screenshot_id") int screenshotId
    );

    @POST("screenshots/{screenshot_id}/units")
    Single<Object> postScreenshotUnit(
        @Path("screenshot_id") int screenshotId
    );

    @POST("screenshots")
    Single<Object> postScreenshot();

    @GET("component-lists")
    Single<Results<Object>> getComponentLists();

    @GET("component-lists/{component-list_slug}")
    Single<Object> getComponentList(
        @Path("component-list_slug") String componentListSlug
    );

    @PUT("component-lists/{component-list_slug}")
    Single<Object> putComponentList(
        @Path("component-list_slug") String componentListSlug
    );

    @PATCH("component-lists/{component-list_slug}")
    Single<Object> patchComponentList(
        @Path("component-list_slug") String componentListSlug
    );

    @DELETE("component-lists/{component-list_slug}")
    Single<Object> deleteComponentList(
        @Path("component-list_slug") String componentListSlug
    );

    @POST("component-lists/{component-list_slug}/components")
    Single<Object> postComponentListComponents(
        @Path("component-list_slug") String componentListSlug
    );

    @POST("component-lists/{component-list_slug}/components/{component_slug}")
    Single<Object> postComponentListComponent(
        @Path("component-list_slug") String componentListSlug,
        @Path("component_slug") String componentSlug
    );

    @DELETE("component-lists/{component-list_slug}/components/{component_slug}")
    Single<Object> deleteComponentListComponent(
        @Path("component-list_slug") String componentListSlug,
        @Path("component_slug") String componentSlug
    );
}
