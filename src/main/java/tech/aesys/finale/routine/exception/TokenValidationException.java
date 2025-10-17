package tech.aesys.finale.routine.exception;

import lombok.Getter;

@Getter
public class TokenValidationException extends RuntimeException {
    private final String code;

    public TokenValidationException(String message) {
        this(message, "TOKEN_VALIDATION_ERROR");
    }

    public TokenValidationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public TokenValidationException(String message, Throwable cause) {
        this(message, "TOKEN_VALIDATION_ERROR", cause);
    }

    public TokenValidationException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}

