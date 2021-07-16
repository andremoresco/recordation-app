package com.recordation.usermanagementservice.exceptions;

import com.recordation.usermanagementservice.util.HttpErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionsHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<HttpErrorResponse> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(HttpErrorResponse.builder()
                        .errorCode(e.code)
                        .message(e.message)
                        .build());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleUserNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(HttpErrorResponse.builder()
                        .errorCode("USER_NOT_FOUND")
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponse.builder()
                        .errorCode("METHOD_ARGUMENT_NOT_VALID")
                        .message(errors)
                        .build());
    }

}
