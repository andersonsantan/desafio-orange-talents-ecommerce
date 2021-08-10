package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}
