package br.com.itau.consignado.apitransferencia.adapter.rest.controller;

import br.com.itau.consignado.apitransferencia.adapter.rest.dto.HistoricoTransacoesResponseDto;
import br.com.itau.consignado.apitransferencia.adapter.rest.dto.TransacoesRequestDto;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.HistoricoTransferenciaRequest;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.HistoricoTransferenciaResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.IBuscarHistoricoTransferencia;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.ITranferencia;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.TransferenciaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes/v1/transferencia")
@RequiredArgsConstructor
public class TransacoesController {

    private final ITranferencia tranferencia;
    private final IBuscarHistoricoTransferencia buscarHistoricoTransferencia;

    @PostMapping()
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransacoesRequestDto requestDto) {
        tranferencia.realizarTransferencia(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Transferencia realizada com sucesso.");
    }

    @GetMapping("/historico/{contaId}")
    public ResponseEntity<List<HistoricoTransacoesResponseDto>> buscarHistoricoTransacoes(@PathVariable(value = "contaId") UUID contaId) {
        var response = buscarHistoricoTransferencia.buscarHistorico(montarRequestHistorico(contaId));
        return ResponseEntity.status(HttpStatus.OK).body(converterResponseHistorico(response));
    }

    private List<HistoricoTransacoesResponseDto> converterResponseHistorico(List<HistoricoTransferenciaResponse> response) {
        return response.stream().map(this::mapResponseDto).collect(Collectors.toList());
    }

    private HistoricoTransacoesResponseDto mapResponseDto(HistoricoTransferenciaResponse response) {
        return HistoricoTransacoesResponseDto.builder()
                .historicoId(response.getHistoricoId())
                .contaOrigem(response.getContaOrigem())
                .contaDestino(response.getContaDestino())
                .dataHoraTransferencia(response.getDataHoraTransferencia())
                .valorTransferido(response.getValorTransferido())
                .build();
    }

    private TransferenciaRequest montarRequest(TransacoesRequestDto dto) {
        return TransferenciaRequest.builder()
                .contaOrigem(dto.getContaOrigem())
                .contaDestino(dto.getContaDestino())
                .valorTransferencia(dto.getValorTransferencia())
                .build();
    }

    private HistoricoTransferenciaRequest montarRequestHistorico(UUID contaId) {
        return HistoricoTransferenciaRequest.builder()
                .contaOrigem(contaId)
                .build();
    }
}
