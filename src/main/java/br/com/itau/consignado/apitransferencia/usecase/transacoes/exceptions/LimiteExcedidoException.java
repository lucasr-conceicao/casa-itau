package br.com.itau.consignado.apitransferencia.usecase.transacoes.exceptions;

import br.com.itau.consignado.apitransferencia.usecase.exception.UseCaseException;

public class LimiteExcedidoException extends UseCaseException {
    public LimiteExcedidoException(String mensagem) {
        super(mensagem);
    }
}
