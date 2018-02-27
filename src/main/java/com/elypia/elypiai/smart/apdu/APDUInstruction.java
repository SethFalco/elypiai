package com.elypia.elypiai.smart.apdu;

public enum APDUInstruction {

	/**
	 * Return the serial number of the card.
	 */

	GET_DATA((byte)0xCA),

	/**
	 * Load any authentication keys required to access the card.
	 */

	LOAD_AUTH_KEYS((byte)0x82),

	/**
	 * Request authorisation to read/write data to a specific block.
	 */

	AUTH_BLOCK((byte)0x86),

	/**
	 * Read data on the specified block.
	 * Block must be authorised prior, see {@link #AUTH_BLOCK}.
	 */

	READ_DATA((byte)0xB0),

	/**
	 * Write data on the specified block.
	 * Block must be authorised prior, see {@link #AUTH_BLOCK}.
	 */

	WRITE_DATA((byte)0xD6);

	/**
	 * Stores the byte value for the instruction.
	 */

	private byte value;

	APDUInstruction(byte value) {
		this.value = value;
	}

	/**
	 * @return	Return byte value to issue instruction.
	 */

	public byte getByte() {
		return value;
	}
}
