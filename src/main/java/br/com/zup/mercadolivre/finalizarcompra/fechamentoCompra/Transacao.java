package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;
import br.com.zup.mercadolivre.finalizarcompra.StatusTransacao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String idTransacaoGateway;

    @NotNull
    private LocalDateTime instante;

    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, @NotBlank String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }
}
