package br.com.itau.consignado.apitransferencia.usecase.database.cliente;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ClienteRequest {

    private String nome;
    private String cpf;
    private BigDecimal saldo;
}
