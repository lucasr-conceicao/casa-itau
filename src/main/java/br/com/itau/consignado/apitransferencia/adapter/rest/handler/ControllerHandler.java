package br.com.itau.consignado.apitransferencia.adapter.rest.handler;

import br.com.itau.consignado.apitransferencia.adapter.database.h2.exceptions.RecursoNaoEncontradoException;
import br.com.itau.consignado.apitransferencia.usecase.exception.UseCaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    private ResponseEntity<JsonHandler> HandlerMethodArgumentNotValidException(Exception ex, HttpServletRequest httpServlet) {
        val msg = "Body json não esta com todos os campos preenchidos.";
        return montarRetorno(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), httpServlet.getRequestURI(), msg);
    }

    @ExceptionHandler({UseCaseException.class})
    private ResponseEntity<JsonHandler> HandlerUseCaseException(Exception ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    private ResponseEntity<JsonHandler> HandlerRecursoNaoEncontradoException(Exception ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    private ResponseEntity<JsonHandler> montarRetorno(HttpStatus httpStatus, Integer code, String path, String mensagem) {
        return new ResponseEntity<>(new JsonHandler(LocalDateTime.now(), code , httpStatus, path, mensagem), httpStatus);
    }
}
