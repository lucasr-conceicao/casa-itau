package br.com.itau.consignado.apitransferencia.adapter.database.h2.cliente;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Cliente;
import br.com.itau.consignado.apitransferencia.adapter.database.h2.exceptions.RecursoNaoEncontradoException;
import br.com.itau.consignado.apitransferencia.adapter.database.repository.ClienteRepository;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ClienteResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.IBuscarTodosClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarTodosClientes implements IBuscarTodosClientes {

    private final ClienteRepository repository;

    @Override
    public List<ClienteResponse> buscarTodosClientes() {
        var cliente = repository.findAll();
        var clienteValidado = validarCliente(cliente);
        return converterResponse(clienteValidado);
    }

    private List<Cliente> validarCliente(List<Cliente> responseCliente) {
        if (Objects.isNull(responseCliente) || responseCliente.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum registro de clientes encontrado.");
        }
        return responseCliente;
    }

    private List<ClienteResponse> converterResponse(List<Cliente> responseCliente) {
        return responseCliente.stream().map(this::mapCliente).collect(Collectors.toList());
    }

    private ClienteResponse mapCliente(Cliente cliente) {
        return ClienteResponse.builder()
                .clienteId(cliente.getClienteId().toString())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .numeroConta(cliente.getNumeroConta().toString())
                .saldo(cliente.getSaldo())
                .build();
    }
}
