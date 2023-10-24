package br.com.itau.consignado.apitransferencia.usecase.database.transferencia;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class RealizarTransferenciaRequest {

    private UUID contaOrigem;
    private UUID contaDestino;
    private BigDecimal valorDebitadoTransferenciaContaOrigem;
    private BigDecimal valorDepositadoTransferenciaContaDestino;
}
