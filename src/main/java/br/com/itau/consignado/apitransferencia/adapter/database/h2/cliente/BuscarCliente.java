package br.com.itau.consignado.apitransferencia.adapter.database.h2.cliente;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Cliente;
import br.com.itau.consignado.apitransferencia.adapter.database.h2.exceptions.RecursoNaoEncontradoException;
import br.com.itau.consignado.apitransferencia.adapter.database.repository.ClienteRepository;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ClienteResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.IBuscarCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarCliente implements IBuscarCliente {

    private final ClienteRepository repository;

    @Override
    public ClienteResponse buscarCliente(UUID contaId) {
        var cliente = repository.findByNumeroConta(contaId);
        var clienteValidado = validarCliente(cliente, contaId);
        return converterResponse(clienteValidado);
    }

    private Cliente validarCliente(Cliente response, UUID contaId) {
        if (Objects.isNull(response))
            throw new RecursoNaoEncontradoException("cliente nao encontrado para o numero da conta: " + contaId);
        return response;
    }

    private ClienteResponse converterResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .clienteId(cliente.getClienteId().toString())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .numeroConta(cliente.getNumeroConta().toString())
                .saldo(cliente.getSaldo())
                .build();
    }
}