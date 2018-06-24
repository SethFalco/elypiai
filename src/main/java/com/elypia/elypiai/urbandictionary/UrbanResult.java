package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.utils.ElyUtils;
import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.stream.Collectors;

public class UrbanResult {

	@SerializedName("result_type")
	private UrbanResultType type;

	@SerializedName("tags")
	private Collection<String> tags;

	private Collection<String> tagsDistinct;

	@SerializedName("list")
	private List<UrbanDefinition> definitions;

	@SerializedName("sounds")
	private List<String> sounds;

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
		int index = random ? ElyUtils.RANDOM.nextInt(definitions.size()) : 0;
		return definitions.get(index);
	}

	/**
	 * @return Tags with duplicates removed.
	 */

	public Collection<String> getTagsDistinct() {
		if (tagsDistinct == null)
			tagsDistinct = tags.stream().distinct().collect(Collectors.toList());

		return tagsDistinct;
	}

	/**
	 * @param amount The max number of sounds to return.
	 * @return A randomised list of sounds.
	 */

	public Collection<String> getRandomSounds(int amount) {
		if (sounds.size() <= amount)
			return sounds;

		List<String> randomSounds = new ArrayList<>();

		for (int i = 0; i < amount; i++) {
			int index = ElyUtils.RANDOM.nextInt(sounds.size());
			randomSounds.add(sounds.get(index));
		}

		return randomSounds;
	}

	/**
	 * @return	The type of result set receieved.
	 */

	public UrbanResultType getResultType() {
		return type;
	}

	public void setResultType(UrbanResultType type) {
		this.type = type;
	}

	/**
	 * @return	Tags deemed associated with the word defined.
	 */

	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return	Sounds related to this defintion as urls linking to audio files.
	 */

	public List<String> getSounds() {
		return sounds;
	}

	public void setSounds(List<String> sounds) {
		this.sounds = sounds;
	}

	/**
	 * @return	A full list of all returned definitions in the order they were
	 * recieved in, by default this should be by vote.
	 */

	public List<UrbanDefinition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<UrbanDefinition> definitions) {
		this.definitions = definitions;
	}
}
