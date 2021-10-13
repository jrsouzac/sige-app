package com.sige.application.exception;

import com.sige.application.enums.ExceptionOperacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler({CampoException.class})
    public final ResponseEntity<Object> handleAllExceptions(CampoException ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getCampo(), ex.getMensagem(), ex.getDetalhes(), ex.getOperacao());
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LoginException.class})
    public final ResponseEntity<Object> handleAllExceptions(LoginException ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getCampo(), ex.getMensagem(), ex.getDetalhes(), ex.getOperacao());
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getClass().getName(), ex.getMessage(), ex.getCause().getMessage(), null);
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NullPointerException.class})
    public final ResponseEntity<Object> handleAllExceptions(NullPointerException ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getClass().getName(), ex.getMessage(), ex.getCause().getMessage(), null);
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<Object> handleAllExceptions(RuntimeException ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getClass().getName(), ex.getMessage(), ex.getCause().getMessage(), null);
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAllExceptions(
            MethodArgumentNotValidException ex) {
        ExceptionSchema[] execao = new ExceptionSchema[1];
        Map<String, String> errors = new HashMap<>();
        String msg = "";
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            msg.concat(error.getDefaultMessage() + "\n");

        });
        errors.put("mensagem", msg);
        return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
