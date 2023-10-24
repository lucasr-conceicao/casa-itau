package br.com.itau.consignado.apitransferencia.adapter.database.repository;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Cliente findByNumeroConta(UUID contaId);
}
