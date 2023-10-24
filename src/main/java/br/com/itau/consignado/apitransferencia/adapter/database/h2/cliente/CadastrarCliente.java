package br.com.itau.consignado.apitransferencia.adapter.database.h2.cliente;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Cliente;
import br.com.itau.consignado.apitransferencia.adapter.database.repository.ClienteRepository;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ClienteRequest;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ClienteResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ICadastrarCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastrarCliente implements ICadastrarCliente {

    private final ClienteRepository repository;

    @Override
    public ClienteResponse cadastrarCliente(ClienteRequest request) {
        var cliente = montarClienteRequest(request);
        repository.save(cliente);
        return converterResponse(cliente);
    }

    private Cliente montarClienteRequest(ClienteRequest request) {
        return Cliente.builder()
                .clienteId(UUID.randomUUID())
                .nome(request.getNome())
                .cpf(request.getCpf())
                .numeroConta(UUID.randomUUID())
                .saldo(request.getSaldo())
                .build();
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
