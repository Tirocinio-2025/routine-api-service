package tech.aesys.finale.routine.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.aesys.finale.routine.exception.RoutineNonTrovataException;
@Slf4j
@ControllerAdvice(assignableTypes = RoutineController.class)
public class RoutineExceptionHandler {

    @ExceptionHandler(RoutineNonTrovataException.class)
    @ResponseBody
    public ResponseEntity<String> routineNotFound(RoutineNonTrovataException ex) {
        log.error("RoutineNonTrovataException", ex);

        return ResponseEntity.status(404).body(ex.getMessage());

    }

}
