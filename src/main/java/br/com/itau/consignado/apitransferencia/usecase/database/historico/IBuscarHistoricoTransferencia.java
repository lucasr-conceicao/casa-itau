package br.com.itau.consignado.apitransferencia.usecase.database.historico;

import java.util.List;

public interface IBuscarHistoricoTransferencia {
    List<HistoricoTransferenciaResponse> buscarHistorico(HistoricoTransferenciaRequest request);
}
