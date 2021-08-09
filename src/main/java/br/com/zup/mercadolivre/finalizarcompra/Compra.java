package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    private ListaGatewayPagamento gateway;

    public Compra() {
    }

    public Compra(Produto produtoEscolhido, int quantidade, Usuario comprador, ListaGatewayPagamento gateway) {
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
                "\n, produtoEscolhido=" + produtoEscolhido +
                "\n, quantidade=" + quantidade +
                "\n, comprador=" + comprador +
                '}';
    }
}
