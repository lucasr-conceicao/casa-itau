package br.com.itau.consignado.apitransferencia.usecase.database.historico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoTransferenciaResponse {

    private UUID historicoId;
    private UUID contaOrigem;
    private UUID contaDestino;
    private Date dataHoraTransferencia;
    private BigDecimal valorTransferido;
}
