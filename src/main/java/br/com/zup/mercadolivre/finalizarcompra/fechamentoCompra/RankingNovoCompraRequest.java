package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import javax.validation.constraints.NotNull;

public class RankingNovoCompraRequest {
    @NotNull
    private Long idCompra;

    @NotNull
    private Long idDonoProduto;

    @Deprecated
    public RankingNovoCompraRequest() {
    }

    public RankingNovoCompraRequest(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "RankingNovoCompraRequest{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdDonoProduto() {
        return idDonoProduto;
    }
}
