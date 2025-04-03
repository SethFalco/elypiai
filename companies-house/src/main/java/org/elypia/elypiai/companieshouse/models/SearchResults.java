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

package org.elypia.elypiai.companieshouse.models;

import java.util.List;

public class SearchResults<T extends SearchItem> {

    private String etag;
    private List<T> items;
    private int itemsPerPage;
    private String kind;
    private int startIndex;
    private int totalResults;

    public String getEtag() {
        return etag;
    }

    public List<T> getItems() {
        return items;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public String getKind() {
        return kind;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
