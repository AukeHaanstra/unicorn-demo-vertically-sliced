package nl.pancompany.unicorn.unicorn.adapter.in.web.controller;

import jakarta.validation.ConstraintViolationException;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnicornRepository.UnicornNotFoundException.class)
    public ResponseEntity<?> handle(UnicornRepository.UnicornNotFoundException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UnicornRepository.UnicornAlreadyExistsException.class)
    public ResponseEntity<?> handle(UnicornRepository.UnicornAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage("A unicorn with this identifier already exists."));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handle(ConstraintViolationException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.toString()));
    }

    record ErrorMessage (String message) {
    }
}
