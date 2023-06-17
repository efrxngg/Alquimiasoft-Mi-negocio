package com.alquimiasoft.minegocio.controller.exception.handler;

import com.alquimiasoft.minegocio.dto.ProblemDetailMessage;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.MissingFormatArgumentException;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /*
     * La exepcion se genera cuando no se cumple con las validaciones establecidad
     * por el javax.validation.constraints
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ProblemDetailMessage> globalInvalidArgumentExcpetion(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> msg.append(error.getDefaultMessage()).append(", "));
        int len = msg.length();
        msg.delete(len - 2, len);
        log.error(msg.toString(), e);

        return status(HttpStatus.BAD_REQUEST).body(ProblemDetailMessage.builder()
                .message(msg.toString())
                .date(LocalDateTime.now())
                .path(URI.create(request.getRequestURI())).build());
    }

    /*
     * Se lanza cuando hay un especificador de formato que no tiene un argumento
     * correspondiente
     * o si su indice de argumento hace referencia a un argumento que no existe
     * ex: RoleType.Hora
     */
    @ExceptionHandler(value = {MissingFormatArgumentException.class})
    public ResponseEntity<ProblemDetailMessage> globalMissingErrorHandler(MissingFormatArgumentException e, HttpServletRequest request) {
        String errorMessage = e.getMessage();
        log.error(errorMessage, e);

        return status(HttpStatus.BAD_REQUEST).body(ProblemDetailMessage.builder()
                .message(errorMessage)
                .date(LocalDateTime.now())
                .path(URI.create(request.getRequestURI())).build());
    }

    /*
     * Se debe a que la entrada no se asigna a la definicion de destino. Ejemplo:
     * input esperado: int
     * input enviado:str
     */
    @ExceptionHandler(value = {MismatchedInputException.class})
    public ResponseEntity<ProblemDetailMessage> globalMismatchedInput(MismatchedInputException e, HttpServletRequest request) {
        String errorMessage = "Alguno de los campos tiene un input no soportado";
        log.error(errorMessage, e);

        return status(HttpStatus.BAD_REQUEST).body(ProblemDetailMessage.builder()
                .message(errorMessage)
                .date(LocalDateTime.now())
                .path(URI.create(request.getRequestURI())).build());
    }

}
