package br.com.itau.consignado.apitransferencia.usecase.database.cliente;

import java.util.UUID;

public interface IBuscarCliente {

    ClienteResponse buscarCliente(UUID contaId);
}
