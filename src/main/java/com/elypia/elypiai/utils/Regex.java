package com.elypia.elypiai.utils;

import javax.imageio.ImageIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {

	// Message Parser Regexes

	MSGML_ARGS("(?i)(?:^\\{\\?(?<args>[A-Z|]*)\\})"),
	MSGML_SELECT("(?:(?<!\\!)\\{(?<select>[^?][^\\{\\}\\[\\]]+)\\}(?!\\[))"),
	MSGML_REPEAT("(?:\\{(?<repeat>[^\\{\\}]+)\\}\\[(?<repmin>\\d+)\\,(?<repmax>\\d+)\\])"),
	MSGML_OPTIONAL("(?:\\{(?<optional>[^\\{\\}]+)\\}\\[\\?\\])"),
	MSGML_RANDOM("(?:\\{\\[(?<random>(?<randmin>\\d+)-(?<randmax>\\d+)))\\]\\}"),
	MSGML_IGNORE("(?:\\!\\{(?<ignore>[^\\{\\}]+)\\})"),

	// Markdown Regexes

	MD_HEADING("(?:^|[\\n\\r])(?<header>\\#{1,6})\\ (?<text>.+)"),
	MD_URL("\\[(?<display>.*[^\\\\])\\]\\((?<url>.*[^\\\\])\\)"),


	// Amazon

	AMAZON_ACCESS_KEY("AKIA[IJ][A-Z2-7]{14}[AQ]"),
	AMAZON_SECRET("(?i)[A-Z\\d\\/+]{40}"),

	// General Purpose Regexes

	NUMBER("^(?<minus>-)?(?=\\.?\\d)(?<int>\\d*)?(?:\\.(?<float>\\d*))?$"),
	TIME(""),
	DATE(""),
	COLOR(""),
	URL(""),
	IMAGE_URL (
		String.format(
			"https?:\\/\\/.+\\/.+\\.(%s)",
			String.join("|", ImageIO.getReaderFileSuffixes())
		)
	),
	EMAIL_ADDRESS("(?i)(?<username>[\\w-]+)@(?<domain>[A-Z\\d-]+(?<tld>(?:\\.[A-Z]{2,}){1,2}))");

	private String regex;
	private Pattern pattern;

	Regex(String regex) {
		this.regex = regex;
		pattern = Pattern.compile(regex);
	}

	public Pattern getPattern() {
		return pattern;
	}

	public Matcher getMatcher(String matchAgainst) {
		return pattern.matcher(matchAgainst);
	}

	public boolean matches(String matchAgainst) {
		return matchAgainst.matches(regex);
	}

	public String getRegex() {
		return regex;
	}

	@Override
	public String toString() {
		return regex;
	}

	public static Pattern compileTogether(Regex... regexs) {
		String[] strings = new String[regexs.length];

		for (int i = 0; i < strings.length; i++)
			strings[i] = regexs[i].toString();

		String joined = String.join("|", strings);
		return Pattern.compile(joined);
	}
}
