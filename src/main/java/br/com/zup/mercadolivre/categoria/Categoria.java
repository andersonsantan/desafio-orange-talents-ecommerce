package br.com.zup.mercadolivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria superCategoria;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }
    public Categoria(String nome, Categoria superCategoria) {
        this.nome = nome;
        this.superCategoria= superCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", superCategoria=" + superCategoria +
                '}';
    }
}
