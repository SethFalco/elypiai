package com.elypia.elypiai.nanowrimo.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum NanoError {

    UNKNOWN("Unknown"),

    @XmlEnumValue("user does not exist")
    USER_DOES_NOT_EXIST("user does not exist"),

    @XmlEnumValue("user does not have a current novel")
    USER_DOES_NOT_HAVE_A_CURRENT_NOVEL("user does not have a current novel");

    private final String MESSAGE;

    NanoError(String message) {
        MESSAGE = message;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
