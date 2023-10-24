package br.com.itau.consignado.apitransferencia.adapter.rest.controller;

import br.com.itau.consignado.apitransferencia.adapter.rest.dto.TransacoesRequestDto;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.ITranferencia;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.TransferenciaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes/v1/transferencia")
@RequiredArgsConstructor
public class TransacoesController {

    private final ITranferencia tranferencia;

    @PostMapping()
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransacoesRequestDto requestDto) {
        tranferencia.realizarTransferencia(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Transferencia realizada com sucesso.");
    }

    private TransferenciaRequest montarRequest(TransacoesRequestDto dto) {
        return TransferenciaRequest.builder()
                .contaOrigem(dto.getContaOrigem())
                .contaDestino(dto.getContaDestino())
                .valorTransferencia(dto.getValorTransferencia())
                .build();
    }
}
