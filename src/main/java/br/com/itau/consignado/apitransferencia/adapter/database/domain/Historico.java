package br.com.itau.consignado.apitransferencia.adapter.database.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_historico")
public class Historico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_historico")
    private UUID historicoId;

    @Column(name = "conta_origem", nullable = false)
    private UUID contaOrigem;

    @Column(name = "conta_destino", nullable = false)
    private UUID contaDestino;

    @Column(name = "data_hora_transferencia", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY hh:MM:ss")
    private Date dataHoraTransferencia;

    @Column(name = "valor_transferido")
    private BigDecimal valorTransferido;
}
