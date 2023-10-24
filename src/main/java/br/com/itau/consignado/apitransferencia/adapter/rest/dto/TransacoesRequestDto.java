package br.com.itau.consignado.apitransferencia.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
public class TransacoesRequestDto {

    @NotNull
    @JsonProperty("conta_origem")
    private UUID contaOrigem;

    @NotNull
    @JsonProperty("conta_destino")
    private UUID contaDestino;

    @NotNull
    @JsonProperty("valor_transferencia")
    private BigDecimal valorTransferencia;
}
