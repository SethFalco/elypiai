package com.elypia.elypiai.runescape;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

    private RuneScape runescape;

    private Date date;
    private String details;
    private String text;

    public Activity(RuneScape runescape, JSONObject object) {
        this.runescape = runescape;

        try {
            date = FORMAT.parse(object.getString("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        details = object.getString("details");
        text = object.getString("text");
    }
}
