package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.GatewayPagamento;
import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.RetornoGatewayPagamento;
import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.Transacao;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Produto produtoEscolhido;

    @Positive
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    public Compra() {
    }

    public Compra(Produto produtoEscolhido, int quantidade, Usuario comprador, GatewayPagamento gateway) {
        this.produtoEscolhido = produtoEscolhido;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoEscolhido=" + produtoEscolhido +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                ", transacoes=" + transacoes +
                '}';
    }

    public void adicionaTransacao(@Valid RetornoGatewayPagamento gatewayPagamento) {
        Transacao novaTransacao = gatewayPagamento.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transacao igual a essa processada "+novaTransacao.toString());
        Set<Transacao> transacoesConcluidasComSucesso = transacoesConcluidasComSucesso();
        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra foi concluida com sucesso");

        this.transacoes.add(gatewayPagamento.toTransacao(this));
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size()<= 1, "Existe mais de uma transação concluida para compra"+this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getDonodoProduto(){
        return this.produtoEscolhido.getDono();
    }
}
