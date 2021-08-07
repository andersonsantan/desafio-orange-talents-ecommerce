package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotBlank String pergunta, Produto produto, Usuario usuario) {

        this.titulo = titulo;
        this.pergunta = pergunta;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                "\n, titulo='" + titulo + '\'' +
                "\n, pergunta='" + pergunta + '\'' +
                "\n, produto=" + produto +
                "\n, usuario=" + usuario +
                "\n, usuario=" + instante +
                '}';
    }


}
