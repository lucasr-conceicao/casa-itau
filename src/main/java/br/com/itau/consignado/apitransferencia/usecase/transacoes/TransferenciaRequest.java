package br.com.itau.consignado.apitransferencia.usecase.transacoes;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class TransferenciaRequest {

    private UUID contaOrigem;
    private UUID contaDestino;
    private BigDecimal valorTransferencia;
}
