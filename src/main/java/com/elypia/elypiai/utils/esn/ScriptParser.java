package com.elypia.elypiai.utils.esn;

import com.elypia.elypiai.Regex;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptParser {

	private Pattern regex;
	private Pattern argsRegex;
	private Pattern ignoreRegex;
	private Random rand;

	public ScriptParser() {
		this(new Random());
	}

	public ScriptParser(final Random RAND) {
		rand = RAND;

		regex = Regex.compileTogether(
			Regex.MSGML_SELECT,
			Regex.MSGML_REPEAT,
			Regex.MSGML_OPTIONAL,
			Regex.MSGML_RANDOM
		);

		argsRegex = Regex.MSGML_ARGS.getPattern();
		ignoreRegex = Regex.MSGML_IGNORE.getPattern();
	}

	public String[] compile(String... strings) {

		for (int i = 0; i < strings.length; i++)
			strings[i] = compile(strings[i]);

		return strings;
	}

	public String compile(String markup) {
		Matcher matcher;

		while ((matcher = regex.matcher(markup)).find()) {
			do {
				String select = matcher.group("select");
				String repeat = matcher.group("repeat");
				String optional = matcher.group("optional");
				String random = matcher.group("random");

				// Select
				if (select != null) {
					String[] options = select.split("\\|");

					for (int i = 0; i < options.length; i++)
						options[i] = options[i].trim();

					markup = markup.replace(matcher.group(), options[rand.nextInt(options.length)]);
				}

				// Repeat
				if (repeat != null) {
					String repmin = matcher.group("repmin");
					int min = Integer.parseInt(repmin);

					String repmax = matcher.group("repmax");
					int max = Integer.parseInt(repmax);

					String[] strings = new String[rand.nextInt(max - min) + min];

					for (int i = 0; i < strings.length; i++)
						strings[i] = repeat;

					markup = markup.replace(matcher.group(), String.join(" ", strings));
				}

				// Optional (Repeat [0,1])
				if (optional != null)
					markup = markup.replace(matcher.group(), rand.nextBoolean() ? optional : "");

				// Random
				// TO-DO: Zero padding
				if (random != null) {
					String randmin = matcher.group("randmin");
					int min = Integer.parseInt(randmin);

					String randmax = matcher.group("randmax");
					int max = Integer.parseInt(randmax);

					int value = rand.nextInt(max - min) + min;

					markup = markup.replace(matcher.group(), String.valueOf(value));
				}
			} while (matcher.find());
		}

		Matcher argsMatcher = argsRegex.matcher(markup);

		// If in this case as arguments should only ever be at start.
		// TO-DO: Needs major improvement.
		if (argsMatcher.find()) {
			String args = argsMatcher.group("args");

			if (args != null) {
				markup = markup.replace(argsMatcher.group(), "");

				List<String> argsList = Arrays.asList(args);

				if (argsList.contains("ty")) {
					StringBuilder builder = new StringBuilder(markup);

					for (int i = 0; i < builder.length() / 36; i++) {
						int in = rand.nextInt(builder.length());

						if (rand.nextInt(64) == 0) {
							builder.deleteCharAt(in);
							continue;
						}

						char c = builder.charAt(in);
						builder.setCharAt(in, (char)(c + 1));
					}

					markup = builder.toString();
				}
			}
		}

		// Now that we've done applying everything, let's pull out our ignores.
		Matcher ignoreMatcher = ignoreRegex.matcher(markup);

		while (ignoreMatcher.find()) {
			String ignore = ignoreMatcher.group("ignore");

			if (ignore != null)
				markup = markup.replace(ignoreMatcher.group(), ignore);
		}

		return markup.trim();
	}
}
