/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.twitch;

import com.google.gson.annotations.JsonAdapter;
import org.elypia.elypiai.twitch.deserializers.PageDeserializer;

import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@JsonAdapter(PageDeserializer.class)
public class TwitchPage<I> {

    private List<I> items;
    private String cursor;

    public TwitchPage(List<I> items, String cursor) {
        this.items = items;
        this.cursor = cursor;
    }

    public List<I> getItems() {
        return items;
    }

    public String getCursor() {
        return cursor;
    }
}
