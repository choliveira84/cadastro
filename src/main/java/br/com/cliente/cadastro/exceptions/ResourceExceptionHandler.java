package br.com.cliente.cadastro.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * ResourceExceptionHandler
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultError> objectNotFound(EntityNotFoundException e, HttpServletRequest request) {
        DefaultError err = new DefaultError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado",
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação", "Verifique o(s) dado(s) abaixo para continuar", request.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DefaultError> ConstraintViolationHandler(ConstraintViolationException e,
            HttpServletRequest request) {
        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Erro de validação", "Verifique o(s) dado(s) abaixo para continuar", request.getRequestURI());

        for (ConstraintViolation<?> x : e.getConstraintViolations()) {
            err.addError(x.getPropertyPath().toString(), x.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DefaultError> HttpRequestMethodNotSupportHandler(HttpServletRequest req, Exception e)
            throws Exception {

        DefaultError err = new DefaultError(System.currentTimeMillis(), HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Método não suportado", e.getMessage(), req.getRequestURI());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<DefaultError> InvalidDateHandler(InvalidDateException e, HttpServletRequest request) {
        DefaultError err = new DefaultError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Datas inválidas", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DefaultError> MethodArgumentTypeMismatchHandler(HttpServletRequest req, Exception e)
            throws Exception {

        DefaultError err = new DefaultError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Falha durante a conversão", e.getMessage(), req.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<DefaultError> HttpMessageNotReadableHandler(HttpServletRequest req, Exception e)
            throws Exception {

        DefaultError err = new DefaultError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Corpo da requisição inválido", e.getMessage(), req.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // TODO IllegalArgumentException
}