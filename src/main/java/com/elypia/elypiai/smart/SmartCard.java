package com.elypia.elypiai.smart;

import com.elypia.elypiai.smart.apdu.*;

import javax.smartcardio.*;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SmartCard {

	private final Card CARD;
	private final CardChannel CHANNEL;
	private final APDUType TYPE;
	private final byte[] AUTH;

	public SmartCard(final Card CARD, APDUType type, byte[] auth) {
		this.CARD = CARD;
		CHANNEL = CARD.getBasicChannel();
		TYPE = type;
		AUTH = auth;
	}

	/**
	 * @return					Get the serial number of the card provided.
	 * @throws CardException
	 */

	public String getSerial() throws CardException {
		byte[] getData = {
			APDUClass.AUTHENTICATION.getByte(),
			APDUInstruction.GET_DATA.getByte(),
			0x00, // No first param
			0x00, // No second param
			0x00 // Don't specify means return unlimited bytes
		};

		CommandAPDU command = new CommandAPDU(getData);
		Response resp = new Response(transmit(command));
		return resp.getHexData();
	}

	/**
	 *
	 * @return					Authorise access keys on the card so we can
	 * 							read and write data to it.
	 * @throws CardException
	 */

	public boolean authoriseSmartCard() throws CardException {
		CommandAPDU command = new CommandAPDU(
			APDUClass.AUTHENTICATION.getByte(),
			APDUInstruction.LOAD_AUTH_KEYS.getByte(),
			0x00, // No first param
			0x00, // No second param
			AUTH // Authorisation key
		);

		Response resp = new Response(transmit(command));

		return resp.getStatus() == APDUStatus.SUCCESS;
	}

	/**
	 * @param block				The block to request access to manipulate.
	 * @return					If we were granted access to that block.
	 * @throws CardException
	 */

	public boolean authoriseBlock(byte block) throws CardException {
		byte[] auth = {
			APDUVersion.ONE.getByte(),
			0x00,
			block, // Which block to authenticate
			TYPE.getByte(),
			0x00 // Key number
		};

		CommandAPDU command = new CommandAPDU(
			APDUClass.AUTHENTICATION.getByte(),
			APDUInstruction.AUTH_BLOCK.getByte(),
			0x00, // No first param
			0x00, // No second param
			auth // Authorisation key
		);

		Response resp = new Response(transmit(command));

		return resp.getStatus() == APDUStatus.SUCCESS;
	}

	/**
	 * @param block				Which block we wish to read from.
	 * 							Ensure to call {@link #authoriseBlock(byte)} on block
	 * 							prior to attempting any read/write operation.
	 * @param length			How much data to read from data block. <br>
	 * 							See {@link #readData(byte)} to default to 16 bytes.
	 * @return					The response object with all information regarding the request.
	 * @throws CardException
	 * @see 					{@link #readData(byte, byte)} to default to reading 16 bytes.
	 */

	public Response readData(byte block, byte length) throws CardException {
		CommandAPDU command = new CommandAPDU(
			APDUClass.AUTHENTICATION.getByte(),
			APDUInstruction.READ_DATA.getByte(),
			0x00, // No first param
			block, // Block of data to read from
			length // How much data to read (16 bytes)
		);

		return new Response(transmit(command));
	}

	/**
	 * @param block				Which block we wish to read from.
	 * 							Ensure to call {@link #authoriseBlock(byte)} on block
	 * 							prior to attempting any read/write operation.
	 * @return					Response object with all information regarding the request.
	 * @throws CardException
	 * @see 					{@link #readData(byte, byte)} to specify how many bytes to read.
	 */

	public Response readData(byte block) throws CardException {
		return readData(block, (byte)0x10);
	}

	/**
	 * @param block				The block of which to write data to.
	 * 							Ensure to call {@link #authoriseBlock(byte)} on block
	 * 							prior to attempting any read/write operations.
	 * @param data				The data to write to this location.
	 * @return					The resposne object with all information regarding the request.
	 * @throws CardException
	 */

	public Response writeData(byte block, byte[] data) throws CardException {
		CommandAPDU command = new CommandAPDU(
			APDUClass.AUTHENTICATION.getByte(),
			APDUInstruction.WRITE_DATA.getByte(),
			0x00, // No first param
			block, // Block of data to read from
			data // Data to write to block
		);

		return new Response(transmit(command));
	}

	// Temp, doesn't check size of card
	public void dumpData(File file) throws CardException, IOException {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 256; i++) {
			authoriseBlock((byte)i);
			Response resp = readData((byte)i);

			builder.append(resp.getHexData() + "\n");
		}

		if (!file.exists()) {
			if (file.getParentFile() != null)
				file.getParentFile().mkdirs();
		}

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(builder.toString().trim());
		}
	}

	/*////////////////////////////////////////////////////////////////////////
	 * 						WRAP AROUND ORIGINAL CLASS						//
	 *////////////////////////////////////////////////////////////////////////

	/**
	 * @return	Original Card object as provided by Java.
	 */

	public Card getCard() {
		return CARD;
	}

	/**
	 * @return	ATR of card.
	 */

	public String getHexATR() {
		return DatatypeConverter.printHexBinary(CARD.getATR().getBytes());
	}

	/**
	 * Literily calls {@link CardChannel#transmit(CommandAPDU)}.
	 *
	 * @param command			Command to issue to card.
	 * @return					ResponseAPDU
	 * @throws CardException
	 */

	public ResponseAPDU transmit(CommandAPDU command) throws CardException {
		return CHANNEL.transmit(command);
	}
}
