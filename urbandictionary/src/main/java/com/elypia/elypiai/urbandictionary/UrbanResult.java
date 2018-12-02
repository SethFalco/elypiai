package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.urbandictionary.data.ResultType;
import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class UrbanResult {

	@SerializedName("result_type")
	private ResultType type;

	@SerializedName("tags")
	private Collection<String> tags;

	@SerializedName("list")
	private List<Definition> definitions;

	@SerializedName("sounds")
	private List<String> sounds;

	/**
	 * @return	The top resulting definition that appears on UrbanDictionary for this search.
	 */
	public Definition getDefinition() {
		return getDefinition(false);
	}

	/**
	 * @param random If to get the top definition or a random one.
	 * @return A urban dictionary defintion entry for this word.
	 */
	public Definition getDefinition(boolean random) {
		int index = random ? ThreadLocalRandom.current().nextInt(definitions.size()) : 0;
		return definitions.get(index);
	}

	/**
	 * @return	The type of result set receieved.
	 */
	public ResultType getResultType() {
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

		Random rand = ThreadLocalRandom.current();
		List<String> randomSounds = new ArrayList<>();

		for (int i = 0; i < amount; i++)
			randomSounds.add(sounds.get(rand.nextInt(sounds.size())));

		return Collections.unmodifiableCollection(randomSounds);
	}

	/**
	 * @return	A full list of all returned definitions in the order they were
	 * recieved in, by default this should be by vote.
	 */
	public List<Definition> getDefinitions() {
		return definitions;
	}
}
