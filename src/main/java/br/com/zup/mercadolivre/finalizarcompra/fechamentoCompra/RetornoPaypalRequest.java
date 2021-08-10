package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;


import br.com.zup.mercadolivre.finalizarcompra.Compra;
import br.com.zup.mercadolivre.finalizarcompra.StatusTransacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento{

    @NotBlank
    private String idTransacao;

    @Min(0)
    @Max(1)
    private int status;

    public RetornoPaypalRequest() {
    }

    public RetornoPaypalRequest(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RetornoPaypalRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusTransacao = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(statusTransacao,idTransacao, compra);
    }
}
