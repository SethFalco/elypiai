package com.elypia.elypiai.utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ElyUtils {

	private ElyUtils() {

	}

	public static final Random RANDOM = new Random();

	public static <T> String[] toStringArray(Collection<T> items) {
		return toStringArray(items.toArray());
	}

    public static <T> String[] toStringArray(T[] items) {
        int length = items.length;
        String[] strings = new String[length];

        for (int i = 0; i < length; i++)
            strings[i] = items[i].toString();

        return strings;
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
	 * provided instead.
	 *
	 * @param	string			String to parse.
	 * @param	elseDefault		Default value if string is not a number.
	 * @return					String as an int.
	 */

	public static int optInt(String string, int elseDefault) {
		if (Regex.NUMBER.matches(string)) {
			double d = Double.parseDouble(string);

			if (d % 1 == 0 && d <= Integer.MAX_VALUE)
				return (int)d;
		}

		return elseDefault;
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
		return Regex.NUMBER.matches(string) ? Double.parseDouble(string) : elseDefault;
	}

	/**
	 * @return	Returns a random color.
	 */

	public static Color getRandomColor() {
		return new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256));
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

	public static <T> boolean arrayContains(T item, Object... items) {
		for (Object t : items) {
			if (item == t || item.equals(t))
				return true;
		}

		return false;
	}

	public static <T> boolean containsAny(T[] array, Object... items) {
		for (T t : array) {
			if (arrayContains(t, items))
				return true;
		}

		return false;
	}
}
