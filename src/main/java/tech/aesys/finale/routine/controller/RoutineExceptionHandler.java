package tech.aesys.finale.routine.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.aesys.finale.routine.exception.RoutineNonTrovataException;

@ControllerAdvice
public class RoutineExceptionHandler {

    @ExceptionHandler(RoutineNonTrovataException.class)
    public String routineNotFound(RoutineNonTrovataException ex) {
        return ex.getMessage();
    }

}
