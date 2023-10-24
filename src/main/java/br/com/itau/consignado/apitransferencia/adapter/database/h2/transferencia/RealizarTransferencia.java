package br.com.itau.consignado.apitransferencia.adapter.database.h2.transferencia;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Cliente;
import br.com.itau.consignado.apitransferencia.adapter.database.h2.exceptions.RecursoNaoEncontradoException;
import br.com.itau.consignado.apitransferencia.adapter.database.repository.ClienteRepository;
import br.com.itau.consignado.apitransferencia.usecase.database.transferencia.IRealizarTransferencia;
import br.com.itau.consignado.apitransferencia.usecase.database.transferencia.RealizarTransferenciaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RealizarTransferencia implements IRealizarTransferencia {

    private final ClienteRepository repository;

    @Override
    @Transactional
    public void realizarTransferencia(RealizarTransferenciaRequest request) {
        var dadosContaOrigem = montarRequestContaOrigem(request.getContaOrigem(), request);
        var dadosContaDestino = montarRequestContaDestino(request.getContaDestino(), request);
        repository.save(dadosContaOrigem);
        repository.save(dadosContaDestino);
    }

    private Cliente montarRequestContaOrigem(UUID contaOrigem, RealizarTransferenciaRequest request) {
        var cliente = buscarDadosContaOrigem(contaOrigem);
        cliente.setClienteId(cliente.getClienteId());
        cliente.setNumeroConta(contaOrigem);
        cliente.setSaldo(request.getValorDebitadoTransferenciaContaOrigem());
        cliente.setNome(cliente.getNome());
        cliente.setCpf(cliente.getCpf());
        return cliente;
    }

    private Cliente montarRequestContaDestino(UUID contaDestino, RealizarTransferenciaRequest request) {
        var cliente = buscarDadosContaDestino(contaDestino);
        cliente.setClienteId(cliente.getClienteId());
        cliente.setNumeroConta(contaDestino);
        cliente.setSaldo(request.getValorDepositadoTransferenciaContaDestino());
        cliente.setNome(cliente.getNome());
        cliente.setCpf(cliente.getCpf());
        return cliente;
    }

    @Transactional(readOnly = true)
    private Cliente buscarDadosContaOrigem(UUID contaOrigem) {
        var cliente = repository.findByNumeroConta(contaOrigem);
        if (Objects.isNull(cliente))
            throw new RecursoNaoEncontradoException("Cliente nao encontrado para o numero da conta " + contaOrigem);
        return cliente;
    }

    @Transactional(readOnly = true)
    private Cliente buscarDadosContaDestino(UUID contaDestino) {
        var cliente = repository.findByNumeroConta(contaDestino);
        if (Objects.isNull(cliente))
            throw new RecursoNaoEncontradoException("Cliente nao encontrado para o numero da conta " + contaDestino);
        return cliente;
    }

}
