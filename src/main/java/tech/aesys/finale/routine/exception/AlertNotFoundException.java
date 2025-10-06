package tech.aesys.finale.routine.exception;

import lombok.Getter;
@Getter
public class AlertNotFoundException extends RuntimeException {
    private final String code;

    public AlertNotFoundException(String message) {
        this(message, "RESOURCE_NOT_FOUND");
    }

    public AlertNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
