package br.com.itau.consignado.apitransferencia.usecase.database.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private String clienteId;
    private String nome;
    private String cpf;
    private String numeroConta;
    private BigDecimal saldo;
}
