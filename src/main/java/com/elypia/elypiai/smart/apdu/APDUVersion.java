package com.elypia.elypiai.smart.apdu;

public enum APDUVersion {

	ONE((byte)0x01);

	private byte value;

	APDUVersion(byte value) {
		this.value = value;
	}

	public byte getByte() {
		return value;
	}
}
