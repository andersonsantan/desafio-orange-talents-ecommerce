package br.com.zup.mercadolivre.detalheproduto;

import br.com.zup.mercadolivre.produto.CaracteristicaProduto;

public class CaracteristicaProdutoView {
    private String nome;
    private String descricao;

    @Deprecated
    public CaracteristicaProdutoView() {
    }

    public CaracteristicaProdutoView(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
