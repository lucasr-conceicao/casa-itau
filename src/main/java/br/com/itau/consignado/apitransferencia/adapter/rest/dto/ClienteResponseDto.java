package br.com.itau.consignado.apitransferencia.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDto {

    @JsonProperty("cliente_id")
    private String clienteId;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("numero_conta")
    private String numeroConta;

    @JsonProperty("saldo_conta")
    private BigDecimal saldo;
}
