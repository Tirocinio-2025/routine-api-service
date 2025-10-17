package tech.aesys.finale.routine.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
    private final String code;

    public InvalidTokenException(String message) {
        this(message, "INVALID_TOKEN");
    }

    public InvalidTokenException(String message, String code) {
        super(message);
        this.code = code;
    }
}