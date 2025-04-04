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

package fun.falco.elypiai.runescape;

import fun.falco.elypiai.runescape.models.Player;
import fun.falco.elypiai.runescape.models.QuestStatuses;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public interface RuneScapeService {

    @GET("profile/profile")
    Maybe<Player> getUser(
        @Query("user") String user
    );

    @GET("quests")
    Single<QuestStatuses> getQuestStatuses(
        @Query("user") String user
    );
}
