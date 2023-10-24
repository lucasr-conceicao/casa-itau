package br.com.itau.consignado.apitransferencia.adapter.rest.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class JsonHandler {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY hh:MM:ss")
    private LocalDateTime data;
    private Integer code;
    private HttpStatus httpStatus;
    private String path;
    private String mensagem;
}
