package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.urbandictionary.data.UrbanResultType;
import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class UrbanResult {

	@SerializedName("result_type")
	private UrbanResultType type;

	@SerializedName("tags")
	private Collection<String> tags;

	@SerializedName("list")
	private List<UrbanDefinition> definitions;

	@SerializedName("sounds")
	private List<String> sounds;

	private UrbanResult() {

	}

	/**
	 * @return	The top resulting definition that appears on UrbanDictionary for this search.
	 */

	public UrbanDefinition getDefinition() {
		return getDefinition(false);
	}

	/**
	 * @param random If to get the top definition or a random one.
	 * @return A urban dictionary defintion entry for this word.
	 */

	public UrbanDefinition getDefinition(boolean random) {
		int index = random ? ThreadLocalRandom.current().nextInt(definitions.size()) : 0;
		return definitions.get(index);
	}

	/**
	 * @return	The type of result set receieved.
	 */

	public UrbanResultType getResultType() {
		return type;
	}

	/**
	 * @return	Tags deemed associated with the word defined.
	 */

	public Collection<String> getTags() {
		return tags;
	}

	/**
	 * @return Tags with duplicates removed.
	 */

	public Collection<String> getTagsDistinct() {
		return tags.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * @return	Sounds related to this defintion as urls linking to audio files.
	 */

	public List<String> getSounds() {
		return Collections.unmodifiableList(sounds);
	}

	/**
	 * @param amount The max number of sounds to return.
	 * @return A randomised list of sounds.
	 */

	public Collection<String> getSounds(int amount) {
		if (sounds.size() <= amount)
			return sounds;

		List<String> randomSounds = new ArrayList<>();

		for (int i = 0; i < amount; i++) {

			int index = ThreadLocalRandom.current().nextInt(sounds.size());
			randomSounds.add(sounds.get(index));
		}

		return Collections.unmodifiableCollection(randomSounds);
	}

	/**
	 * @return	A full list of all returned definitions in the order they were
	 * recieved in, by default this should be by vote.
	 */

	public List<UrbanDefinition> getDefinitions() {
		return definitions;
	}
}
