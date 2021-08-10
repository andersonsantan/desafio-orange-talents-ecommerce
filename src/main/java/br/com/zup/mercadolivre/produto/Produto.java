package br.com.zup.mercadolivre.produto;


import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.usuario.Usuario;
import com.fasterxml.classmate.Annotations;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Double valor;

    @NotNull
    private Integer quantidade;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Set<CaracteristicaProduto> caracteristicaProduto;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario dono;

    private LocalDateTime instante = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Double valor, Integer quantidade, Set<NovaCaracteristicaRequest> caracteristicaProduto, String descricao, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicaProduto = toModel(caracteristicaProduto);
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }

    private Set<CaracteristicaProduto> toModel(Set<NovaCaracteristicaRequest> request){
        Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
        request.forEach(e -> caracteristicas.add(new CaracteristicaProduto(e.getNome(),e.getDescricao(),this)));
        return caracteristicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome) && Objects.equals(caracteristicaProduto, produto.caracteristicaProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, caracteristicaProduto);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public void associarImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDone) {
        return this.dono.equals(possivelDone);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public Usuario getDono() {
        return dono;
    }

    public boolean abateEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade>0,"A quantidade deve ser maior que zero");
        if (quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }
}
