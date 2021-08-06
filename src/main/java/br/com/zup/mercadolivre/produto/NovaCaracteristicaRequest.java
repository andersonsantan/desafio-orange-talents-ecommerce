package br.com.zup.mercadolivre.produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NovaCaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public NovaCaracteristicaRequest() {
    }

    public NovaCaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NovaCaracteristicaRequest)) return false;
        NovaCaracteristicaRequest that = (NovaCaracteristicaRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
