package br.com.itau.consignado.apitransferencia.usecase.transacoes;

import br.com.itau.consignado.apitransferencia.usecase.database.cliente.ClienteResponse;
import br.com.itau.consignado.apitransferencia.usecase.database.cliente.IBuscarCliente;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.HistoricoTransferenciaRequest;
import br.com.itau.consignado.apitransferencia.usecase.database.historico.IGravarHistoricoTransferencia;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.exceptions.LimiteExcedidoException;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.exceptions.SaldoInsuficienteException;
import br.com.itau.consignado.apitransferencia.usecase.transacoes.exceptions.TransacaoInvalidaException;
import br.com.itau.consignado.apitransferencia.usecase.database.transferencia.IRealizarTransferencia;
import br.com.itau.consignado.apitransferencia.usecase.database.transferencia.RealizarTransferenciaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferenciaImpl implements ITranferencia{

    private final IBuscarCliente buscarCliente;
    private final IRealizarTransferencia realizarTransferencia;
    private final IGravarHistoricoTransferencia gravarHistoricoTransferencia;


    @Override
    public void realizarTransferencia(TransferenciaRequest request) {
        var requestTransferencia = montarRequest(request);
        var dataHoraTransferencia = new Date();
        realizarTransferencia.realizarTransferencia(requestTransferencia);
        gravarHistoricoTransferencia.gravarHistoricoTransferencia(montarRequestHistorico(request, dataHoraTransferencia));
    }

    private RealizarTransferenciaRequest montarRequest(TransferenciaRequest request) {
        var validacaoTransacao = validaSeContaTemSaldo(request.getContaOrigem());
        validaSeValorDaTransacaoEMenorOuIgualAMilReais(request.getValorTransferencia());
        validaSeValorDaTransacaoEMaiorQueZeroReais(request.getValorTransferencia());

        return RealizarTransferenciaRequest.builder()
                .contaOrigem(request.getContaOrigem())
                .contaDestino(request.getContaDestino())
                .valorDebitadoTransferenciaContaOrigem(
                        BigDecimal.valueOf(validacaoTransacao.getSaldo().doubleValue() - request.getValorTransferencia().doubleValue()))
                .valorDepositadoTransferenciaContaDestino(
                        BigDecimal.valueOf(validacaoTransacao.getSaldo().doubleValue() + request.getValorTransferencia().doubleValue()))
                .build();
    }

    private HistoricoTransferenciaRequest montarRequestHistorico(TransferenciaRequest request, Date dataHoraTransferencia) {
        return HistoricoTransferenciaRequest.builder()
                .historicoId(UUID.randomUUID())
                .contaOrigem(request.getContaOrigem())
                .contaDestino(request.getContaDestino())
                .valorTransferido(request.getValorTransferencia())
                .dataHoraTransferencia(dataHoraTransferencia)
                .build();

    }

    private ClienteResponse validaSeContaTemSaldo(UUID contaOrigem) {
        var cliente = buscarCliente.buscarCliente(contaOrigem);
        if (cliente.getSaldo().doubleValue() < 0) {
            throw new SaldoInsuficienteException("A conta não tem saldo suficiente para realizar a transação.");
        }
        return cliente;
    }

    private void validaSeValorDaTransacaoEMenorOuIgualAMilReais(BigDecimal valorTransacao) {
        if (valorTransacao.doubleValue() > 1000) {
            throw new LimiteExcedidoException("Valor da transferencia excede o limite de mil reais.");
        }
    }

    private void validaSeValorDaTransacaoEMaiorQueZeroReais(BigDecimal valorTransacao) {
        if (valorTransacao.doubleValue() <= 0) {
            throw new TransacaoInvalidaException("Transacao deve ser maior que 0.00 reais.");
        }
    }
}


