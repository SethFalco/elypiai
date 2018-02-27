package com.elypia.elypiai.smart;

import com.elypia.elypiai.smart.apdu.APDUStatus;

import javax.smartcardio.ResponseAPDU;
import javax.xml.bind.DatatypeConverter;

public class Response {

	private final ResponseAPDU RESPONSE;

	private APDUStatus status;
	private String hexBytes;
	private String hexData;

	public Response(final ResponseAPDU RESPONSE) {
		this.RESPONSE = RESPONSE;
		status = APDUStatus.getStatus(String.format("%x", RESPONSE.getSW()));
		hexBytes = DatatypeConverter.printHexBinary(RESPONSE.getBytes());
		hexData = DatatypeConverter.printHexBinary(RESPONSE.getData());
	}

	/**
	 * @return 	Actual ResponseAPDU object with all information
	 * 			regarding the command and response.
	 */

	public ResponseAPDU getResponseAPDU() {
		return RESPONSE;
	}

	/**
	 * @return	Response status, if succesful or if not what
	 * 			went wrong.
	 * @see {@link APDUStatus}
	 */

	public APDUStatus getStatus() {
		return status;
	}

	public String getHexBytes() {
		return hexBytes;
	}

	/**
	 * @return Hex representation of the data received.
	 */

	public String getHexData() {
		return hexData;
	}

	/**
	 * @return Ascii character representation of the data received.
	 */

	public String getDataAsString() {
		StringBuilder builder = new StringBuilder();

		for (byte b : RESPONSE.getData())
			builder.append((char)b);

		return builder.toString();
	}
}
