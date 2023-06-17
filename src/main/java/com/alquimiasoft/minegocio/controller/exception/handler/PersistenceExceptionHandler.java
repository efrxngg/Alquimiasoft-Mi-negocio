package com.alquimiasoft.minegocio.controller.exception.handler;

import com.alquimiasoft.minegocio.controller.exception.GenericNotFoundException;
import com.alquimiasoft.minegocio.dto.ProblemDetailMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Log4j2
public class PersistenceExceptionHandler {

    /**
     * Este tipo de excepcion es lanzada cuando se rompe con una
     * restriccion establecida en la base de datos
     */
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<ProblemDetailMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        String errorMessage = "Ya existe un registro con alguno de los campos identificadores que has ingresado";
        log.error(errorMessage, e);

        ProblemDetailMessage problemDetailMessage = ProblemDetailMessage.builder()
                .message(errorMessage)
                .date(LocalDateTime.now())
                .path(URI.create(request.getRequestURI()))
                .build();

        return ResponseEntity.badRequest().body(problemDetailMessage);
    }

    /**
     * Este tipo de excepcion generica se lanza cuando se no encuentra algo
     * que es critico para la logica de alguna funcion
     */
    @ExceptionHandler(value = {GenericNotFoundException.class})
    public ResponseEntity<ProblemDetailMessage> globalNotFoundException(GenericNotFoundException e, HttpServletRequest request) {
        String errorMessage = e.getMessage();
        log.error(errorMessage, e);

        return status(HttpStatus.BAD_REQUEST).body(ProblemDetailMessage.builder()
                .message(errorMessage)
                .date(LocalDateTime.now())
                .path(URI.create(request.getRequestURI())).build());
    }

}
