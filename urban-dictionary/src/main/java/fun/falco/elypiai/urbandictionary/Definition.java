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

package fun.falco.elypiai.urbandictionary;

import java.time.Instant;
import java.util.List;

import org.elypia.retropia.gson.deserializers.EmptyNullDeserializer;
import org.elypia.retropia.gson.deserializers.IsoDateTimeTemporalDeserializer;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Definition implements Comparable<Definition> {

    @SerializedName("definition")
    private String definitionBody;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("thumbs_up")
    private int thumbsUp;

    @SerializedName("sound_urls")
    private List<String> soundUrls;

    @SerializedName("author")
    private String author;

    @SerializedName("word")
    private String word;

    @SerializedName("defid")
    private int definitionId;

    @SerializedName("current_vote")
    @JsonAdapter(EmptyNullDeserializer.class)
    private String currentVote;

    @SerializedName("written_on")
    @JsonAdapter(IsoDateTimeTemporalDeserializer.class)
    private Instant createdDate;

    @SerializedName("example")
    @JsonAdapter(EmptyNullDeserializer.class)
    private String example;

    @SerializedName("thumbs_down")
    private int thumbsDown;

    /**
     * @return Definition of the word.
     */
    public String getDefinitionBody() {
        return definitionBody;
    }

    /**
     * @return Permanent URL that will always link to this definition.
     */
    public String getPermaLink() {
        return permalink;
    }

    /**
     * @return Total number of thumbs up the definition has.
     */
    public int getThumbsUp() {
        return thumbsUp;
    }

    public List<String> getSoundUrls() {
        return soundUrls;
    }

    /**
     * @return Name of the author of this definition.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return Word that was defined.
     */
    public String getWord() {
        return word;
    }

    /**
     * @return Unique ID of this definition.
     */
    public int getDefinitionId() {
        return definitionId;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    /**
     * @return Demonstration of how the word is used.
     */
    public String getExample() {
        return example;
    }

    /**
     * @return Total number of down votes the definition has gotten.
     */
    public int getThumbsDown() {
        return thumbsDown;
    }

    @Override
    public int compareTo(Definition o) {
        return o.thumbsUp - thumbsUp;
    }
}
