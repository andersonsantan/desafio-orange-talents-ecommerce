package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;

public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}
