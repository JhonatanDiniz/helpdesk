package com.jhonatan.helpdesk.resources.exceptions;

import com.jhonatan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jhonatan.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){
        StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Object Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
        StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Violation Integrity", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> validationErro(ConstraintViolationException ex, HttpServletRequest request){
        ValidationError erros = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation erro fields", "Error validating fields", request.getRequestURI());

        for(ConstraintViolation<?> x : ex.getConstraintViolations()){
            erros.addErro(x.getPropertyPath().toString(), x.getMessageTemplate());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}
