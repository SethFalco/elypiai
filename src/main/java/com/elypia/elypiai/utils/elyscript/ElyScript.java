package com.elypia.elypiai.utils.elyscript;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.*;

public final class ElyScript {

	private static final Pattern SELECT = Pattern.compile("(?!.+\\()\\((?<select>(?:\\\\\\)|[^)])*)\\)(?:\\{(?<param>(?<optional>\\?)|(?<repeat>(?<min>\\d+)?,?(?<max>\\d+)?))})?");
	private static final Pattern RANDOM = Pattern.compile("\\((?:\\{(?<format>(?:\\\\}|[^}])*)})?\\{(?<min>\\d+)?\\.*(?<max>\\d+)?}\\)");
	private static final Pattern SPLITTER = Pattern.compile("\\|");

	private final String MARKUP;

	private Queue<Integer> queue;

	public ElyScript(String markup) {
		this(markup, null);
	}

	public ElyScript(String markup, Queue<Integer> queue) {
		MARKUP = markup;
		this.queue = queue;
	}

	public String compile(String...args) {
		String compiled = compile();
		return String.format(compiled, args);
	}

	public String compile() {
		String markup = random(MARKUP);
		markup = select(markup);

		return markup;
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

	private String random(String markup) {
        Random rand = ThreadLocalRandom.current();
        Matcher matcher;

        while ((matcher = RANDOM.matcher(markup)).find()) {
            do {
                String format = matcher.group("format");
                String min = matcher.group("min");
                String max = matcher.group("max");
                Number minValue = 0;
                Number maxValue = 0;
                Number total = 0;

                if (min != null)
                    minValue = Integer.parseInt(min);

                if (max != null)
                    maxValue = Integer.parseInt(max);

                if (format != null && format.contains("f"))
					total = Math.random() * (maxValue.intValue() - minValue.intValue()) + minValue.intValue();
                else
                    total = rand.nextInt(maxValue.intValue() - minValue.intValue() + 1) + minValue.intValue();

                if (format != null)
                    markup = markup.replace(matcher.group(), String.format(format, total));
                else
                    markup = markup.replace(matcher.group(), String.valueOf((int)total));
            } while (matcher.find());
        }

        return markup;
	}
}
