package tech.aesys.finale.routine.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.aesys.finale.routine.exception.AlertNotFoundException;

import java.net.URI;

@ControllerAdvice
public class AlertExceptionController {

    @ExceptionHandler(AlertNotFoundException.class)
    public ProblemDetail handleUserNotFound(AlertNotFoundException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Resource not found");
        String uri = request.getRequestURL().toString();
        pd.setType(URI.create(uri));
        pd.setProperty("code", ex.getCode());
        return pd;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Errore interno del server");
        pd.setTitle("Internal Server Error");
        String uri = request.getRequestURL().toString();
        pd.setType(URI.create(uri));
        return pd;
    }

    @ExceptionHandler(UserRoleMismatchException.class)
    public ProblemDetail handleUserRoleMismatch(UserRoleMismatchException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Invalid request");
        String uri = request.getRequestURL().toString();
        pd.setType(URI.create(uri));
        pd.setProperty("code", ex.getCode());
        return pd;
    }
}
