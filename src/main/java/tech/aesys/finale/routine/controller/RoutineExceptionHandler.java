package tech.aesys.finale.routine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.aesys.finale.routine.exception.RoutineNonTrovataException;

@ControllerAdvice
public class RoutineExceptionHandler {

    @ExceptionHandler(RoutineNonTrovataException.class)
    @ResponseBody
    public ResponseEntity<String> routineNotFound(RoutineNonTrovataException ex) {

        return ResponseEntity.status(404).body(ex.getMessage());

    }

}
