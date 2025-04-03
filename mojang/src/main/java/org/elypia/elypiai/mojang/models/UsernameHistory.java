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

package org.elypia.elypiai.mojang.models;

import java.util.Iterator;
import java.util.List;

public class UsernameHistory implements Iterable<UsernameHistoryItem> {

    private List<UsernameHistoryItem> history;

    public String getFirstName() {
        return history.get(0).getName();
    }

    public String getCurrentName() {
        return history.get(history.size() - 1).getName();
    }

    @Override
    public Iterator<UsernameHistoryItem> iterator() {
        return history.iterator();
    }
}
