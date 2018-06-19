package com.elypia.elypiai.cleverbot;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;

public class ScriptResponse {

	private String inputLabel;
	private String predictedInput;
	private String accuracy;
	private String outputLabel;
	private int databaseVersion;
	private int softwareVersion;
	private int randomNumber;
	private LocalDateTime time;
	private String reaction;
	private String reactionTone;
	private String emotion;
	private String emotionTone;
	private int cleverAccuracy;
	private String cleverOutput;
	private String cleverMatch;
	private String csres30;
	private String filteredInput;
	private String reactionDegree;
	private String emotionDegree;
	private String reactionValues;
	private String emotionValues;

	private CleverResponse clever;

	ScriptResponse(CleverResponse clever, JsonObject object) {
		// Keep reference to original response
		this.clever = clever;

		// Grab values
		inputLabel = object.get("input_label").getAsString();
		predictedInput = object.get("predicted_input").getAsString();
		accuracy = object.get("accuracy").getAsString();
		outputLabel	= object.get("output_label").getAsString();
		databaseVersion = object.get("database_version").getAsInt();
		softwareVersion = object.get("software_version").getAsInt();
		randomNumber = object.get("random_number").getAsInt();
		reaction = object.get("reaction").getAsString();
		reactionTone = object.get("reaction_tone").getAsString();
		emotion = object.get("emotion").getAsString();
		emotionTone	= object.get("emotion_tone").getAsString();
		cleverAccuracy = object.get("clever_accuracy").getAsInt();
		cleverOutput = object.get("clever_output").getAsString();
		cleverMatch	= object.get("clever_match").getAsString();
		csres30	= object.get("CSRES30").getAsString();
		filteredInput = object.get("filtered_input").getAsString();
		reactionDegree = object.get("reaction_degree").getAsString();
		emotionDegree = object.get("emotion_degree").getAsString();
		reactionValues = object.get("reaction_values").getAsString();
		emotionValues = object.get("emotion_values").getAsString();

		int timeSecond = object.get("time_second").getAsInt();
		int timeMinute = object.get("time_minute").getAsInt();
		int timeHour = object.get("time_hour").getAsInt();
		int timeDay	= object.get("time_day").getAsInt();
		int timeMonth = object.get("time_month").getAsInt();
		int timeYear = object.get("time_year").getAsInt();

		// Get times and create DateTime object
		time = LocalDateTime.of(timeYear, timeMonth, timeDay, timeHour, timeMinute, timeSecond);

		// Convert empty Strings to null when apt
		if (predictedInput.isEmpty())
			predictedInput = null;

		if (accuracy.isEmpty())
			accuracy = null;

		if (reaction.isEmpty())
			reaction = null;

		if (reactionTone.isEmpty())
			reactionTone = null;

		if (emotion.isEmpty())
			emotion = null;

		if (emotionTone.isEmpty())
			emotionTone = null;

		if (cleverMatch.isEmpty())
			cleverMatch = null;

		if (reactionDegree.isEmpty())
			reactionDegree = null;

		if (emotionDegree.isEmpty())
			emotionDegree = null;

		if (reactionValues.isEmpty())
			reactionValues = null;

		if (emotionValues.isEmpty())
			emotionValues = null;
	}

	public String getInputLabel() {
		return inputLabel;
	}

	public String getPredictedInput() {
		return predictedInput;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public String getOutputLabel() {
		return outputLabel;
	}

	public int getDatabaseVersion() {
		return databaseVersion;
	}

	public int getSoftwareVersion() {
		return softwareVersion;
	}

	public int getRandomNumber() {
		return randomNumber;
	}

	public LocalDateTime getDateTime() {
		return time;
	}

	public String getReaction() {
		return reaction;
	}

	public String getReactionTone() {
		return reactionTone;
	}

	public String getEmotion() {
		return emotion;
	}

	public String getEmotionTone() {
		return emotionTone;
	}

	public int getCleverAccuracy() {
		return cleverAccuracy;
	}

	public String getCleverOutput() {
		return cleverOutput;
	}

	public String getCleverMatch() {
		return cleverMatch;
	}

	public String getCSRES30() {
		return csres30;
	}

	public String getFilteredInput() {
		return filteredInput;
	}

	public String getReactionDegree() {
		return reactionDegree;
	}

	public String getEmotionDegree() {
		return emotionDegree;
	}

	public String getReactionValues() {
		return reactionValues;
	}

	public String getEmotionValues() {
		return emotionValues;
	}

	public CleverResponse getCleverResponse() {
		return clever;
	}
}
