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

package org.elypia.elypiai.poe;

import org.elypia.retropia.core.requests.RestPaginator;

import java.io.IOException;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class StashPaginator implements RestPaginator<Stash> {

    private PathOfExile poe;
    private String cursor;

    public StashPaginator(PathOfExile poe) {
        this(poe, null);
    }

    public StashPaginator(PathOfExile poe, String cursor) {
        this.poe = poe;
        this.cursor = cursor;
    }

    @Override
    public List<Stash> next() throws IOException {
        StashTabs stashtabs = poe.getStashTabs(cursor).complete().get();
        List<Stash> stashes = stashtabs.getStashes();

        if (stashes.isEmpty())
            return null;

        cursor = stashtabs.getCursor();
        return stashes;
    }
}
