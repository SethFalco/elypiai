package com.elypia.elypiai.utils;

import javax.imageio.ImageIO;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {

	// Secret Matching

	DISCORD_BOT_TOKEN("(?i)[A-Z\\d]{24}\\.[A-Z\\d-_]{6}\\.[A-Z\\d-_]{27}"),
	AMAZON_ACCESS_KEY("AKIA[IJ][A-Z\\d]{14}[AQ]"),
	AMAZON_SECRET("(?i)[A-Z\\d\\/+]{40}"),
	STEAM_API_KEY("[A-F\\d]{32}"),
	GOOGLE_API_KEY("AIzaSy[A-C][A-Za-z\\d-_]{32}"),
	TWITCH_SECRET("[a-z\\d]{30}"),
	LEAGUE_OF_LEGENDS_API_KEY("RGAPI-[a-f\\d]{8}(?:-[a-f\\d]{4}){3}-[a-f\\d]{12}"),
	OSU_API_KEY("[a-f\\d]{40}"),
	YANDEX_API_KEY("trnsl(?:\\.\\d){2}\\.\\d{8}T\\d{6}Z\\.[a-z\\d]{16}\\.[a-z\\d]{40}"),

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

	public static Pattern compileTogether(Regex... regexes) {
		StringJoiner joiner = new StringJoiner("|");

		for (Regex regex : regexes)
			joiner.add(regex.getRegex());

		return Pattern.compile(joiner.toString());
	}
}
