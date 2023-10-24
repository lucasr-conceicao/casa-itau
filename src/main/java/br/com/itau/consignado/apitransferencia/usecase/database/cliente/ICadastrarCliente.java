package br.com.itau.consignado.apitransferencia.usecase.database.cliente;

public interface ICadastrarCliente {

    ClienteResponse cadastrarCliente(ClienteRequest request);
}
