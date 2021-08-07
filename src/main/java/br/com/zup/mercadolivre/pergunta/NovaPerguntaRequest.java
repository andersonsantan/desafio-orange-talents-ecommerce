package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String pergunta;

    public NovaPerguntaRequest() {
    }

    public NovaPerguntaRequest(String titulo, String pergunta) {
        this.titulo = titulo;
        this.pergunta = pergunta;
    }

    public Pergunta toModel(Produto produto, Usuario usuario){
        return new Pergunta(titulo,pergunta, produto, usuario);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest{" +
                "\n titulo='" + titulo + '\'' +
                ", pergunta='" + pergunta + '\'' +
                '}';
    }
}
