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

package org.elypia.elypiai.runescape.models;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents player specific stats on quest progression.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 3.0.1
 */
public class QuestStatuses implements Iterable<QuestStatus> {

    @SerializedName("quests")
    private List<QuestStatus> questStatuses;

    @SerializedName("loggedIn")
    private boolean isLoggedIn;

    /**
     * @param status Quests completion status to collate.
     * @return List which contains all quests with this completion status.
     */
    public List<QuestStatus> getByCompletionStatus(CompletionStatus status) {
        return questStatuses.stream()
            .filter((questStatus) -> questStatus.getStatus() == status)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<QuestStatus> getQuestStatuses() {
        return Collections.unmodifiableList(questStatuses);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public Iterator<QuestStatus> iterator() {
        return questStatuses.iterator();
    }
}
