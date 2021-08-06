package br.com.zup.mercadolivre.produto;


import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;


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


}
