package com.elypia.elypiai.cleverbot;

import org.json.JSONObject;

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

	ScriptResponse(CleverResponse clever, JSONObject object) {
		// Keep reference to original response
		this.clever = clever;

		// Grab values
		inputLabel 		= object.getString("input_label");
		predictedInput 	= object.getString("predicted_input");
		accuracy		= object.getString("accuracy");
		outputLabel		= object.getString("output_label");
		databaseVersion = object.optInt("database_version");
		softwareVersion = object.optInt("software_version");
		randomNumber	= object.optInt("random_number");
		reaction		= object.getString("reaction");
		reactionTone	= object.getString("reaction_tone");
		emotion 		= object.getString("emotion");
		emotionTone		= object.getString("emotion_tone");
		cleverAccuracy	= object.optInt("clever_accuracy");
		cleverOutput	= object.getString("clever_output");
		cleverMatch		= object.getString("clever_match");
		csres30			= object.getString("CSRES30");
		filteredInput	= object.getString("filtered_input");
		reactionDegree	= object.getString("reaction_degree");
		emotionDegree	= object.getString("emotion_degree");
		reactionValues	= object.getString("reaction_values");
		emotionValues	= object.getString("emotion_values");

		int timeSecond = object.optInt("time_second");
		int timeMinute = object.optInt("time_minute");
		int timeHour = object.optInt("time_hour");
		int timeDay	= object.optInt("time_day");
		int timeMonth = object.optInt("time_month");
		int timeYear = object.optInt("time_year");

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
