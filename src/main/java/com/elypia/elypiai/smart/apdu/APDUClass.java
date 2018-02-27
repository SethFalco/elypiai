package com.elypia.elypiai.smart.apdu;

public enum APDUClass {

	AUTHENTICATION((byte)0xFF);

	private byte value;

	APDUClass(byte value) {
		this.value = value;
	}

	public byte getByte() {
		return value;
	}
}
