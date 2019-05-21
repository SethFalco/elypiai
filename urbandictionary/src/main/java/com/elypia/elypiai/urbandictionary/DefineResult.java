package com.elypia.elypiai.urbandictionary;

import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class DefineResult {

	@SerializedName("list")
	private List<Definition> definitions;

	/**
	 * Same as calling {@link #getDefinitions(boolean)} with false.
	 *
	 * @return	A full list of all returned definitions in the order they were
	 * recieved in, by default this should be by vote.
	 */
	public List<Definition> getDefinitions() {
		return getDefinitions(false);
	}

	/**
	 * Return all definitions in either the ordered returned by the API (false)
	 * or order of {@link Definition#getThumbsUp()}.
	 *
	 * @param upvoteOrder If definitions should be sorted in upvote order.
	 * @return A list of all returned definitions.
	 */
	public List<Definition> getDefinitions(boolean upvoteOrder) {
		if (!upvoteOrder)
			return Collections.unmodifiableList(definitions);

		List<Definition> sorted = new ArrayList<>(definitions);
		Collections.sort(sorted);
		return Collections.unmodifiableList(sorted);
	}

	/**
	 * @return The top resulting definition that
	 * appears on UrbanDictionary for this search.
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

	/** @return Collate all sounds across all definitions. */
	public List<String> getSounds() {
		return definitions.stream()
			.map(Definition::getSoundUrls)
			.flatMap(List::stream)
			.collect(Collectors.toUnmodifiableList());
	}

	/**
	 * @param amount The max number of sounds to return.
	 * @return A randomised list of sounds.
	 */
	public Collection<String> getSounds(int amount) {
		List<String> sounds = getSounds();

		if (sounds.size() <= amount)
			return sounds;

		Random rand = ThreadLocalRandom.current();
		List<String> randomSounds = new ArrayList<>();

		for (int i = 0; i < amount; i++)
			randomSounds.add(sounds.get(rand.nextInt(sounds.size())));

		return Collections.unmodifiableCollection(randomSounds);
	}
}
