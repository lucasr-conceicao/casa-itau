package br.com.itau.consignado.apitransferencia.adapter.rest.controller;

import br.com.itau.consignado.apitransferencia.adapter.rest.dto.ClienteRequestDto;
import br.com.itau.consignado.apitransferencia.adapter.rest.dto.ClienteResponseDto;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ICadastrarCliente cadastrarCliente;
    private final IBuscarTodosClientes buscarTodosClientes;
    private final IBuscarCliente buscarCliente;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponseDto> cadastrarPessoa(@RequestBody @Valid ClienteRequestDto requestDto) {
        var response = cadastrarCliente.cadastrarCliente(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/conta/{contaId}")
    public ResponseEntity<ClienteResponseDto> buscarContaId(@PathVariable(value = "contaId") UUID contaId) {
        var response = buscarCliente.buscarCliente(contaId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(response));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteResponseDto>> buscarClientes() {
        var response = buscarTodosClientes.buscarTodosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(converterResponseList(response));
    }

    private ClienteRequest montarRequest(ClienteRequestDto dto) {
        return ClienteRequest.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .saldo(dto.getSaldo())
                .build();
    }

    private ClienteResponseDto converterResponse(ClienteResponse response) {
        return ClienteResponseDto.builder()
                .clienteId(response.getClienteId())
                .nome(response.getNome())
                .cpf(response.getCpf())
                .numeroConta(response.getNumeroConta())
                .saldo(response.getSaldo())
                .build();
    }

    private List<ClienteResponseDto> converterResponseList(List<ClienteResponse> response) {
        return response.stream().map(this::mapParaResponseDto).collect(Collectors.toList());
    }

    private ClienteResponseDto mapParaResponseDto(ClienteResponse cliente) {
        return ClienteResponseDto.builder()
                .clienteId(cliente.getClienteId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .numeroConta(cliente.getNumeroConta())
                .saldo(cliente.getSaldo())
                .build();
    }
}
