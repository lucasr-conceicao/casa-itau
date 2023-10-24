package br.com.itau.consignado.apitransferencia.adapter.database.repository;

import br.com.itau.consignado.apitransferencia.adapter.database.domain.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoricoRepository extends JpaRepository<Historico, UUID> {

    List<Historico> findByContaOrigem(UUID contaId);
}
