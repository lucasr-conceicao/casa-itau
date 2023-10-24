package br.com.itau.consignado.apitransferencia.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoTransacoesResponseDto {

    private UUID historicoId;
    private UUID contaOrigem;
    private UUID contaDestino;
    private Date dataHoraTransferencia;
    private BigDecimal valorTransferido;
}
