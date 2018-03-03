package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.utils.ElyUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class UrbanResult {

	private UrbanResultType type;
	private String[] tags;
	private String[] sounds;
	private List<UrbanDefinition> definitions;
	private String term;

	public UrbanResult(JSONObject object, String term) {
		this.term = term;

		type = UrbanResultType.valueOf(object.getString("result_type").toUpperCase());

		if (type == UrbanResultType.NO_RESULTS)
			return;

		JSONArray array = object.getJSONArray("tags");
		tags = array.toList().toArray(new String[array.length()]);

		array = object.getJSONArray("sounds");
		sounds = array.toList().toArray(new String[array.length()]);

		array = object.getJSONArray("list");

		definitions = new ArrayList<>();

		for (int i = 0; i < array.length(); i++)
			definitions.add(new UrbanDefinition(array.getJSONObject(i)));
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

	public String[] getTags() {
		return tags;
	}

	/**
	 * @return Tags with duplicates removed.
	 */

	public String[] getTagsDistinct() {
		Set<String> set = new HashSet<>();

		for (String string : tags)
			set.add(string);

		return set.toArray(new String[set.size()]);
	}

	/**
	 * @return	Sounds related to this defintion as urls linking to audio files.
	 */

	public String[] getSounds() {
		return sounds;
	}

	/**
	 * @param amount	The max number of sounds to return.
	 * @return			A randomised list of sounds.
	 */

	public String[] getRandomSounds(int amount) {
		List<String> randomSounds = Arrays.asList(sounds);
		Collections.shuffle(randomSounds);

		if (sounds.length <= amount)
			return randomSounds.toArray(new String[sounds.length]);

		String[] toReturn = new String[amount];

		for (int i = 0; i < amount; i++)
			toReturn[i] = randomSounds.get(i);

		return toReturn;
	}

	/**
	 * @return	A full list of all returned definitions in
	 * 			the order they were received in.
	 */

	public List<UrbanDefinition> getDefinitions() {
		return definitions;
	}

	/**
	 * @return	The top resulting definition that appears on
	 * 			UrbanDictionary for this search.
	 */

	public UrbanDefinition getTopResult() {
		return definitions.get(0);
	}

	/**
	 * @return	A random result of all the returned definitions
	 * 			of the search term.
	 */

	public UrbanDefinition getRandomResult() {
		return definitions.get(ElyUtils.RAND.nextInt(definitions.size()));
	}

	/**
	 * @return	The search term to provide this set of results.
	 */

	public String getSearchTerm() {
		return term;
	}
}
