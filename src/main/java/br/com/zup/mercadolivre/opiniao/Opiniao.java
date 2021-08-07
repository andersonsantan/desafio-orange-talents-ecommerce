package br.com.zup.mercadolivre.opiniao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                "\n nota=" + nota +
                "\n titulo='" + titulo + '\'' +
                "\n descricao='" + descricao + '\'' +
                "\n usuario=" + usuario +
                "\n produto=" + produto +
                '}';
    }
}
