package br.com.zup.mercadolivre.detalheproduto;

import br.com.zup.mercadolivre.opiniao.Opiniao;

import java.util.Objects;

public class OpiniaoResponse {

    private String titulo;
    private String descricao;

    public OpiniaoResponse() {
    }

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpiniaoResponse)) return false;
        OpiniaoResponse that = (OpiniaoResponse) o;
        return Objects.equals(titulo, that.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }
}
