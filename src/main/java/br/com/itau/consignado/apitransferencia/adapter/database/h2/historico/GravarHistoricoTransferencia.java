package br.com.itau.consignado.apitransferencia.adapter.database.h2.historico;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Historico;
import br.com.itau.consignado.apitransferencia.adapter.database.repository.HistoricoRepository;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.HistoricoTransferenciaRequest;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.IGravarHistoricoTransferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GravarHistoricoTransferencia implements IGravarHistoricoTransferencia {

    private final HistoricoRepository repository;

    @Override
    public void gravarHistoricoTransferencia(HistoricoTransferenciaRequest request) {
        var historico = montarRequestHistorico(request);
        repository.save(historico);
    }

    private Historico montarRequestHistorico(HistoricoTransferenciaRequest request) {
        return Historico.builder()
                .historicoId(request.getHistoricoId())
                .contaOrigem(request.getContaOrigem())
                .contaDestino(request.getContaDestino())
                .dataHoraTransferencia(request.getDataHoraTransferencia())
                .valorTransferido(request.getValorTransferido())
                .build();
    }
}
