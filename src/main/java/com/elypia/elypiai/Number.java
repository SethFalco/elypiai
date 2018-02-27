package com.elypia.elypiai;

public enum Number {

	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	ELEVEN(11),
	TWELVE(12),
	THIRTEEN(13),
	FOURTEEN(14),
	FIFTEEN(15),
	SIXTEEN(16),
	SEVENTEEN(17),
	EIGHTEEN(18),
	NINETEEN(19),
	TWENTY(20),
	THIRTY(30),
	FOURTY(40),
	FIFTY(50),
	SIXTY(60),
	SEVENTY(70),
	EIGHTY(80),
	NINETY(90),
	HUNDRED(100),
	THOUSAND(1000),
	MILLION(1000000),
	BILLION(1000000000);

	int number;

	Number(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public static String toString(int i) {
		for (Number num : Number.values()) {
			if (num.getNumber() == i)
				return num.toString();
		}

		return null;
	}

	public static String toString(String string) {
		return toString(Integer.parseInt(string));
	}

	public static String toString(char u) {
		return toString(Character.getNumericValue(u));
	}

	public static String toString(char t, char u) {
		return toString(String.format("%c%c", t, u));
	}
}
