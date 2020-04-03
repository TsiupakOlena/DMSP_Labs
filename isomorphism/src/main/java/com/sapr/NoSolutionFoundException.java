package com.sapr;

public class NoSolutionFoundException extends Exception {
    public NoSolutionFoundException() {
        super();
    }

    public NoSolutionFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
