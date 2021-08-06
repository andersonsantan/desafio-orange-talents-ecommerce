package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategotiaRepository;
import br.com.zup.mercadolivre.config.validacao.annotation.ExistsId;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

import java.util.Set;


public class NovoProdutoRequest {

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    @Positive(message = "O valor não tem que ser maior que zero")
    private Double valor;

    @NotNull(message = "Campo obrigatório")
    @PositiveOrZero(message = "A quantidade deve ser maior que zero")
    private Integer quantidade;

    @Size(min = 3, message = "O produto deve possuir ao menos 3 características")
    private Set<NovaCaracteristicaRequest> caracteristicas;

    @NotBlank(message = "Campo obrigatório")
    @Length(max = 1000, message = "Limite de caractere excedido")
    private String descricao;

    @NotNull(message = "O campo não pode ser nulo")
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "Essa categoria não existe")
    private Long categoriaId;


    public NovoProdutoRequest() {
    }


    public NovoProdutoRequest(String nome, Double valor, Integer quantidade, Set<NovaCaracteristicaRequest> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public void setCaracteristicas(Set<NovaCaracteristicaRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Produto toModel(CategotiaRepository categotiaRepository, Usuario dono){
        Categoria categoria = categotiaRepository.findById(categoriaId).get();
        return new Produto(this.nome, this.valor, this.quantidade, this.caracteristicas, this.descricao,categoria,dono);
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }



}
