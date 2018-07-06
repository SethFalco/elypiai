package com.elypia.elypiai.utils.elyscript;

public class MalformedElyScriptException extends RuntimeException {

    public MalformedElyScriptException() {
        super();
    }

    public MalformedElyScriptException(String message) {
        super(message);
    }

    public MalformedElyScriptException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedElyScriptException(Throwable cause) {
        super(cause);
    }
}
