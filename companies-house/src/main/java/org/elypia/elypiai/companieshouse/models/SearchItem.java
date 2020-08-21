/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

public class SearchItem {

    private Address address;
    private String addressSnippet;
    private String description;
    private List<Integer> descriptionIdentifiers;
    private String kind;
    private String snippet;
    private String title;

    public Address getAddress() {
        return address;
    }

    public String getAddressSnippet() {
        return addressSnippet;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getDescriptionIdentifiers() {
        return descriptionIdentifiers;
    }

    public String getKind() {
        return kind;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getTitle() {
        return title;
    }
}
