package com.elypia.elypiai.utils.elyscript;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.*;

public final class ElyScript {

	private static final Pattern SELECT = Pattern.compile("(?!.+\\()\\((?<select>(?:\\\\\\)|[^)])*)\\)(?:\\{(?<param>(?<optional>\\?)|(?<repeat>(?<min>\\d+)?,?(?<max>\\d+)?))})?");
	private static final Pattern RANDOM = Pattern.compile("\\((?:\\{(?<format>(?:\\\\}|[^}])*)})?\\{(?<min>\\d+)?\\.*(?<max>\\d+)?}\\)");
	private static final Pattern SPLITTER = Pattern.compile("\\|");

	public String compile(String markup) {
		return select(markup);
	}

	private String select(String markup) {
		Random rand = ThreadLocalRandom.current();
		Matcher matcher;

		while ((matcher = SELECT.matcher(markup)).find()) {
			do {
				String select = matcher.group("select");
				String[] split = SPLITTER.split(select);
				String selected = split[rand.nextInt(split.length)];
				boolean optional = matcher.group("optional") != null;

				if (optional) {
					markup = markup.replace(matcher.group(), rand.nextBoolean() ? selected : "");
					continue;
				}

				String repeat = matcher.group("repeat");

				if (repeat != null) {
					String min = matcher.group("min");
					String max = matcher.group("max");
					int minValue = 0;
					int maxValue = 0;

					if (min != null)
						minValue = Integer.parseInt(min);

					if (max != null)
						maxValue = Integer.parseInt(max);

					int total = rand.nextInt(maxValue - minValue + 1) + minValue;

					StringJoiner joiner = new StringJoiner("");

					for (int i = 0; i < total; i++)
						joiner.add(selected);

					markup = markup.replace(matcher.group(), joiner.toString());
					continue;
				}

				markup = markup.replace(matcher.group(), selected);
			} while (matcher.find());
		}

		return markup;
	}

	private String random() {


// TO-DO: Zero padding
//				if (random != null) {
//		String randmin = matcher.group("randmin");
//		int min = Integer.parseInt(randmin);
//
//		String randmax = matcher.group("randmax");
//		int max = Integer.parseInt(randmax);
//
//		int value = rand.nextInt(max - min) + min;
//
//		markup = markup.replace(matcher.group(), String.valueOf(value));
	}
}
