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

package org.elypia.elypiai.urbandictionary;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class DefineResult {

    @SerializedName("list")
    private List<Definition> definitions;

    /**
     * @return If definitions were found.
     */
    public boolean hasDefinitions() {
        return definitions != null && !definitions.isEmpty();
    }

    /**
     * Same as calling {@link #getDefinitions(boolean)} with false.
     *
     * @return
     *     Definitions in the order they were received in, by default this
     *     should be by vote.
     */
    public List<Definition> getDefinitions() {
        return getDefinitions(false);
    }

    /**
     * All definitions in either the ordered returned by the API (false) or
     * order of {@link Definition#getThumbsUp()}.
     *
     * @param upvoteOrder If definitions should be sorted in upvote order.
     * @return Definitions for the term.
     */
    public List<Definition> getDefinitions(boolean upvoteOrder) {
        if (!upvoteOrder) {
            return Collections.unmodifiableList(definitions);
        }

        List<Definition> sorted = new ArrayList<>(definitions);
        Collections.sort(sorted);
        return Collections.unmodifiableList(sorted);
    }

    /**
     * @return
     *     Top resulting definition that appears on Urban Dictionary for this
     *     search.
     */
    public Definition getDefinition() {
        return getDefinition(false);
    }

    /**
     * @param random If to get the top definition or a random one.
     * @return Urban Dictionary definition entry for this word.
     */
    public Definition getDefinition(boolean random) {
        int index = random ? ThreadLocalRandom.current().nextInt(definitions.size()) : 0;
        return definitions.get(index);
    }

    /**
     * @return Collate all sounds across all definitions.
     */
    public List<String> getSounds() {
        return definitions.stream()
            .map(Definition::getSoundUrls)
            .flatMap(List::stream)
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * @param amount Max number of sounds.
     * @return Randomized list of sounds.
     */
    public Collection<String> getSounds(int amount) {
        List<String> sounds = getSounds();

        if (sounds.size() <= amount) {
            return sounds;
        }

        Random rand = ThreadLocalRandom.current();
        List<String> randomSounds = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            randomSounds.add(sounds.get(rand.nextInt(sounds.size())));
        }

        return Collections.unmodifiableCollection(randomSounds);
    }
}
