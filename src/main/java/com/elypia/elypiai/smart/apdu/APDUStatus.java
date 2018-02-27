package com.elypia.elypiai.smart.apdu;

import javax.smartcardio.ResponseAPDU;
import javax.xml.bind.DatatypeConverter;

/**
 * A list of status codes and descriptions for {@link ResponseAPDU}.<br>
 * A lot of information regarding status codes and descriptions have been taken from.
 *
 * @see https://eftlab.co.uk/index.php/site-map/knowledge-base/118-apdu-response-list
 */

public enum APDUStatus {

	/**
	 * Command performed succesfully.
	 */

	SUCCESS(new byte[] {(byte)0x90, 0x00}),

	/**
	 * No information given (NV-Ram changed)
	 */

	OPERATION_FAILURE(new byte[] {0x63, 0x00}),

	/**
	 * Function not supported
	 */

	UNSUPPORTED_FUNCTION(new byte[] {0x6A, (byte)0x81}),

	FILE_NOT_FOUND(new byte[] {0x6A, (byte)0x81}),

	/**
	 * There is insufficient memory space in record or file.
	 */

	INSUFFICENT_SPACE(new byte[] {0x6A, (byte)0x84}),

	/**
	 * Should never occur, fail safe if un-noted error code or new one
	 * is implemented.
	 */

	UNKNOWN(new byte[] {0x00, 0x00});

	private byte[] value;

	APDUStatus(byte[] value) {
		this.value = value;
	}

	public byte[] getBytes() {
		return value;
	}

	/**
	 * @param bytes		The bytes received as a response upon
	 * 					transmitting a {@link CommandAPDU}.
	 * @return			The status code in a more human understandable
	 * 					format.
	 *
	 * @see 			{@link #getStatus(String)} to obtain status
	 * 					from String representation of bytes.
	 */

	public static APDUStatus getStatus(byte[] bytes) {
		for (APDUStatus status : APDUStatus.values()) {
			if (DatatypeConverter.printHexBinary(bytes).equals(DatatypeConverter.printHexBinary(status.getBytes())))
				return status;
		}

		return UNKNOWN;
	}

	/**
	 * @param string	The bytes recieved in the form of a String.
	 * @return			The status code in a more human understandbale
	 * 					format.
	 */

	public static APDUStatus getStatus(String string) {
		return getStatus(DatatypeConverter.parseHexBinary(string));
	}
}