package br.com.itau.consignado.apitransferencia.usecase.transacoes.exceptions;

import br.com.itau.consignado.apitransferencia.usecase.exception.UseCaseException;

public class SaldoInsuficienteException extends UseCaseException {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
