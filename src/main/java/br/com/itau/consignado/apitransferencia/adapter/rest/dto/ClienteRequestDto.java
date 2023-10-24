package br.com.itau.consignado.apitransferencia.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ClienteRequestDto {

    @NotNull
    @JsonProperty("nome")
    private String nome;

    @NotNull
    @JsonProperty("cpf")
    private String cpf;

    @NotNull
    @JsonProperty("saldo_conta")
    private BigDecimal saldo;
}
