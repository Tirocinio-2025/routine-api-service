package tech.aesys.finale.routine.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.aesys.finale.routine.exception.InvalidTokenException;
import tech.aesys.finale.routine.exception.TokenValidationException;
import tech.aesys.finale.routine.exception.UnauthorizedException;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ProblemDetail handleInvalidToken(InvalidTokenException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        pd.setTitle("Invalid Token");
        String uri = request.getRequestURL().toString();
        pd.setType(URI.create(uri));
        pd.setProperty("code", ex.getCode());

        log.error(pd.toString(), ex);

        return pd;
    }

    @ExceptionHandler(TokenValidationException.class)
    public ProblemDetail handleTokenValidation(TokenValidationException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        pd.setTitle("Token Validation Error");
        String uri = request.getRequestURL().toString();
        pd.setType(URI.create(uri));
        pd.setProperty("code", ex.getCode());

        log.error(pd.toString(), ex);

        return pd;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorized(UnauthorizedException ex, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        pd.setTitle("Unauthorized Access");
        String uri = request.getRequestURL().toString();
        pd.setType(URI.create(uri));
        pd.setProperty("code", ex.getCode());

        log.error(pd.toString(), ex);

        return pd;
    }
}

