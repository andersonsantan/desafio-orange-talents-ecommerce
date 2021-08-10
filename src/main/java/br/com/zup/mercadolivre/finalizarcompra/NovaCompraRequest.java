package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    private int quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento gateway;

    @Deprecated
    public NovaCompraRequest() {
    }

    public NovaCompraRequest(int quantidade, Long idProduto, GatewayPagamento gateway) {
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

    public GatewayPagamento gatewayPagamento() {
        return gateway;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setGateway(GatewayPagamento gateway) {
        this.gateway = gateway;
    }
}
