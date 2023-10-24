package br.com.itau.consignado.apitransferencia.adapter.database.h2.historico;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Cliente;
import br.com.itau.consignado.apitransferencia.adapter.database.domain.Historico;
import br.com.itau.consignado.apitransferencia.adapter.database.h2.exceptions.RecursoNaoEncontradoException;
import br.com.itau.consignado.apitransferencia.adapter.database.repository.HistoricoRepository;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ClienteResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.HistoricoTransferenciaRequest;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.HistoricoTransferenciaResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.IBuscarHistoricoTransferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarHistoricoTransferencia implements IBuscarHistoricoTransferencia {

    private final HistoricoRepository repository;

    @Override
    public List<HistoricoTransferenciaResponse> buscarHistorico(HistoricoTransferenciaRequest request) {
        var historicoResponse = repository.findByContaOrigem(request.getContaOrigem());
        var historicoValidado = validarHistorico(historicoResponse, request.getContaOrigem());
        return converterResponse(historicoValidado);
    }

    private List<Historico> validarHistorico(List<Historico> response, UUID contaId) {
        if (Objects.isNull(response))
            throw new RecursoNaoEncontradoException("Historico nao encontrado para o numero da conta: " + contaId);
        return response;
    }

    private List<HistoricoTransferenciaResponse> converterResponse(List<Historico> historico) {
        return historico.stream().map(this::mapHistoricoTransferencia).collect(Collectors.toList());
    }

    private HistoricoTransferenciaResponse mapHistoricoTransferencia(Historico historico) {
        return HistoricoTransferenciaResponse.builder()
                .historicoId(historico.getHistoricoId())
                .contaOrigem(historico.getContaOrigem())
                .contaDestino(historico.getContaDestino())
                .dataHoraTransferencia(historico.getDataHoraTransferencia())
                .valorTransferido(historico.getValorTransferido())
                .build();
    }
}