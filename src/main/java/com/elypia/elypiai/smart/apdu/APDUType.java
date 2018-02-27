package com.elypia.elypiai.smart.apdu;

public enum APDUType {

	TYPE_A((byte)0x60),
	TYPE_B((byte)0x61);

	private byte value;

	APDUType(byte value) {
		this.value = value;
	}

	public byte getByte() {
		return value;
	}
}
