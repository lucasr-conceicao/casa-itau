package br.com.itau.consignado.apitransferencia.usecase.database.historico;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class HistoricoTransferenciaRequest {

    private UUID historicoId;
    private UUID contaOrigem;
    private UUID contaDestino;
    private Date dataHoraTransferencia;
    private BigDecimal valorTransferido;
}
