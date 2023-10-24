package br.com.itau.consignado.apitransferencia.usecase.database.cliente;

import java.util.List;

public interface IBuscarTodosClientes {

    List<ClienteResponse> buscarTodosClientes();
}
