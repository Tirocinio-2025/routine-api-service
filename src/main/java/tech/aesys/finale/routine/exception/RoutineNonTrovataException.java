package tech.aesys.finale.routine.exception;

public class RoutineNonTrovataException extends RuntimeException {
    String code;


    public RoutineNonTrovataException(String message) {
        super(message);
        this.code = "404";
    }
}
