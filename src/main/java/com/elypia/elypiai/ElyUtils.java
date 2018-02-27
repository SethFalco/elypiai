package com.elypia.elypiai;

import org.apache.commons.lang3.math.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class ElyUtils {

	private ElyUtils() {
		// Don't instatiate this class.
	}

	public static final Random RAND = new Random();

	public static boolean isValidUrl(String url) {
		return url.matches("^https?:\\/\\/.+\\..+$");
	}

	public static boolean isValidImageUrl(String url) {
		String formats = String.join("|", ImageIO.getReaderFileSuffixes());
		return url.matches("(?i)^https?:\\/\\/.+\\/.+\\.(" + formats + ")$");
	}

	/**
	 * Checks if the text provided starts with the prefix provided
	 * while ignoring any capitilisation. <br>
	 * Returns false by default if either is null.
	 *
	 * @param	text 	Text to check prefix for.
	 * @param	prefix	The prefix.
	 * @return			If text starts with prefix ignoring case.
	 */

	public static boolean startsWithIgnoreCase(String text, String prefix) {
		if (text == null || prefix == null)
			return false;

		return text.toLowerCase().startsWith(prefix.toLowerCase());
	}

	public static String mdUrl(String text, String url) {
		return String.format("[%s](%s)", text, url);
	}

	/**
	 * Checks if the text provided contains String provided
	 * while ignoring any capitilisation. <br>
	 * Returns false by default if either is null.
	 *
	 * @param	text 		Text to check prefix for.
	 * @param	contains	The String to check for.
	 * @return				If text contains with String ignoring case.
	 */

	public static boolean containsIgnoreCase(String text, String... contains) {
		if (text == null || contains == null)
			return false;

		for (String string : contains) {
			if (text.toLowerCase().contains(string.toLowerCase()))
				return true;
		}

		return false;
	}

	/**
	 * Try to pass a String to a numerical value, however
	 * if that String provided is not a number, use the default
	 * instead.
	 *
	 * @param	string			String to parse.
	 * @param	elseDefault		Default value if string is not a number.
	 * @return					String as an int.
	 */

	public static int parseIntOrDefault(String string, int elseDefault) {
		return NumberUtils.isCreatable(string) ? Integer.parseInt(string) : elseDefault;
	}

	/**
	 * Try to pass a String to a numerical value, however
	 * if that String provided is not a number, use the default
	 * instead.
	 *
	 * @param	string			String to parse.
	 * @param	elseDefault		Default value if string is not a number.
	 * @return					String as a double.
	 */

	public static double parseDoubleOrDefault(String string, double elseDefault) {
		return NumberUtils.isCreatable(string) ? Double.parseDouble(string) : elseDefault;
	}

	/**
	 * @return	Returns a random color.
	 */

	public static Color getRandomColor() {
		return new Color(RAND.nextInt(256), RAND.nextInt(256), RAND.nextInt(256));
	}

	public static long convertTime(long time, TimeUnit current, TimeUnit desired) {
		switch (desired) {
			case DAYS: 			return current.toDays(time);
			case HOURS: 		return current.toHours(time);
			case MICROSECONDS: 	return current.toMicros(time);
			case MILLISECONDS: 	return current.toMillis(time);
			case MINUTES: 		return current.toMinutes(time);
			case NANOSECONDS: 	return current.toNanos(time);
			case SECONDS: 		return current.toSeconds(time);
			default: 			return time;
		}
	}

	public static <LIST> List<List<LIST>> splitList(List<LIST> list, int listSize) {
		// Clone the list so we don't modify the original list.
		List<LIST> listClone = new ArrayList<>(list);
		List<List<LIST>> lists = new ArrayList<>();

		while (listClone.size() > listSize) {
			List<LIST> sublist = listClone.subList(0, listSize);
			lists.add(new ArrayList<>(sublist));
			sublist.clear();
		}

		if (listClone.size() != 0)
			lists.add(listClone);

		return lists;
	}

	public static String makeMarkdown(String text, String url) {
		return String.format("[%s](%s)", text, url);
	}

	public static String generateTable(String format, Object[]... objects) {
		return generateTable(format, -1, objects);
	}

	public static String generateTable(String format, int limit, Object[]... objects) {
		return generateTable(format, false, limit, objects);
	}

	public static String generateTable(String format, boolean ascii, int limit, Object[]... objects) {
		StringBuilder builder = new StringBuilder();

		builder.append(String.format(format, objects[0][0], objects[0][1], objects[0][2]));

		for (int i = 1; i < objects.length; i++) {
			String line = String.format(format, objects[i][0], objects[i][1], objects[i][2]);

			if (builder.length() + line.length() > limit && limit != -1)
				break;

			builder.append(line);
		}

		return builder.toString();
	}

	public static <T> boolean arrayContains(T item, T... items) {
		for (T t : items) {
			if (item == t || item.equals(t))
				return true;
		}

		return false;
	}

	public static <T> boolean containsAny(Iterable<T> iter, T... items) {
		for (T t : iter) {
			if (arrayContains(t, items))
				return true;
		}

		return false;
	}

	public static <T> boolean containsAny(T[] array, T... items) {
		for (T t : array) {
			if (arrayContains(t, items))
				return true;
		}

		return false;
	}
}
