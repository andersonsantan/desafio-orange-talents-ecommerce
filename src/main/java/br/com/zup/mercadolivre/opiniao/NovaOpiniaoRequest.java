package br.com.zup.mercadolivre.opiniao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class NovaOpiniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaOpiniaoRequest() {
    }

    public NovaOpiniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }
    public Opiniao toModel(Usuario usuario, Produto produto){
        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
