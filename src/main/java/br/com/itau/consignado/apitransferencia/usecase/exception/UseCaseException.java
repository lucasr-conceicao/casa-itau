package br.com.itau.consignado.apitransferencia.usecase.exception;

public class UseCaseException extends RuntimeException {
    public UseCaseException(String mensagem) {
        super(mensagem);
    }
}
