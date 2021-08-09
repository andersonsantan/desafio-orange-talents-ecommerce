package br.com.zup.mercadolivre.finalizarcompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    private int quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private ListaGatewayPagamento gateway;

    @Deprecated
    public NovaCompraRequest() {
    }

    public NovaCompraRequest(int quantidade, Long idProduto, ListaGatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public ListaGatewayPagamento getGateway() {
        return gateway;
    }
}
