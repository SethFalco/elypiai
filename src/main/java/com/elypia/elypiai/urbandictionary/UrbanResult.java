package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.urbandictionary.data.UrbanResultType;
import com.elypia.elypiai.utils.ElyUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UrbanResult {

	private UrbanResultType type;
	private Collection<String> tags;
	private List<UrbanDefinition> definitions;
	private List<String> sounds;
	private String term;

	public UrbanResult(JSONObject object, String term) {
		this.term = term;

		type = UrbanResultType.getByName(object.getString("result_type"));

		if (type == UrbanResultType.NO_RESULTS)
			return;

		tags = new ArrayList<>();
		definitions = new ArrayList<>();
		sounds = new ArrayList<>();

		JSONArray array = object.getJSONArray("tags");
		ElyUtils.arrayToString(array, tags);

		array = object.getJSONArray("sounds");
		ElyUtils.arrayToString(array, sounds);

		array = object.getJSONArray("list");

		for (int i = 0; i < array.length(); i++) {
			JSONObject def = array.getJSONObject(i);
			UrbanDefinition definition = new UrbanDefinition(def);
			definitions.add(definition);
		}
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

	public Collection<String> getSounds() {
		return sounds;
	}

	/**
	 * @param amount	The max number of sounds to return.
	 * @return			A randomised list of sounds.
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
	 * @return	A full list of all returned definitions in
	 * 			the order they were received in.
	 */

	public List<UrbanDefinition> getDefinitions() {
		return definitions;
	}

	/**
	 * @return	The top resulting definition that appears on UrbanDictionary for this search.
	 */

	public UrbanDefinition getResult() {
		return getResult(false);
	}

	/**
	 * @param random If to get the top definition or a random one.
	 * @return	A random result of all the returned definitions of the search term.
	 */

	public UrbanDefinition getResult(boolean random) {
		int index = random ? ElyUtils.RANDOM.nextInt(definitions.size()) : 0;
		return definitions.get(index);
	}

	/**
	 * @return	The search term to provide this set of results.
	 */

	public String getSearchTerm() {
		return term;
	}
}
