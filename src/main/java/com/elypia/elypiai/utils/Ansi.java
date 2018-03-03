package com.elypia.elypiai.utils;

public enum Ansi {

	/**
	 * Resets and reverts back to default style.
	 */

	RESET(0),

	/**
	 * Turn on high-intensity colors.
	 * Useful for dark backgrounds. <3
	 * See also {@link #BOLD_OFF} to disable.
	 */

	BOLD_ON(1),

	/**
	 * Turn on italics, see {@link #ITALICS_OFF} to disable.
	 */

	ITALICS_ON(3),
	UNDERLINE_ON(4),

	/**
	 * Invert the background and foreground colors.
	 */

	INVERSE_ON(7),
	STRIKETHOUGH_ON(9),

	/**
	 * Turns high-intensity colors off.
	 * Does nothing if {@link #BOLD_ON} is not enabled.
	 */

	BOLD_OFF(22),

	ITALICS_OFF(23),
	UNDERLINE_OFF(24),
	INVERSE_OFF(27),
	STRIKETHROUGH_OFF(29),
	FOREGROUND_BLACK(30),
	FOREGROUND_RED(31),
	FOREGROUND_GREEN(32),
	FOREGROUND_YELLOW(33),
	FOREGROUND_BLUE(34),
	FOREGROUND_MAGENTA(35),
	FOREGROUND_CYAN(36),
	FOREGROUND_WHITE(37),
	FOREGROUND_DEFAULT(39),
	BACKGROUND_BLACK(40),
	BACKGROUND_RED(41),
	BACKGROUND_GREEN(42),
	BACKGROUND_YELLOW(43),
	BACKGROUND_BLUE(44),
	BACKGROUND_MAGENTA(45),
	BACKGROUND_CYAN(46),
	BACKGROUND_WHITE(47),
	BACKGROUND_DEFAULT(49);

	private int code;

	Ansi(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return String.format("\u001B[%dm", code);
	}
}
