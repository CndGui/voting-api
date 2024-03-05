package com.guilhermerodrigues.votingapi.exception;

public class ParametersNotValidException extends RuntimeException {
    public ParametersNotValidException(String message) {
        super(message);
    }
}
