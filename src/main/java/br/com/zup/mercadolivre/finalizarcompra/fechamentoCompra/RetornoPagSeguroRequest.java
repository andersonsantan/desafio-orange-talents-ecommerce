package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;
import br.com.zup.mercadolivre.finalizarcompra.StatusRetornoPagseguro;
import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento{

    @NotBlank
    private String idTransacao;

    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagSeguroRequest() {
    }

    public RetornoPagSeguroRequest(String idTransacao, StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusRetornoPagseguro getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RetornoPagSeguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
