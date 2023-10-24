package br.com.itau.consignado.apitransferencia.usecase.transacoes.exceptions;

import br.com.itau.consignado.apitransferencia.usecase.exception.UseCaseException;

public class TransacaoInvalidaException extends UseCaseException {
    public TransacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
