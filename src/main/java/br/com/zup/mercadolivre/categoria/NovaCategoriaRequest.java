package br.com.zup.mercadolivre.categoria;


import br.com.zup.mercadolivre.config.validacao.annotation.ExistsId;
import br.com.zup.mercadolivre.config.validacao.annotation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Essa categoria já existe")
    private String nome;

    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "Essa supercategoria não existe")
    private Long superCategoriaId;

    public NovaCategoriaRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(String nome, Long superCategoriaId) {
        this.nome = nome;
        this.superCategoriaId = superCategoriaId;
    }

    public Categoria toModel(EntityManager entityManager){
        if(superCategoriaId != null){
            Categoria supercategoria = entityManager.find(Categoria.class, superCategoriaId);
            Assert.notNull(superCategoriaId, "O id da supercategoria não existe");
             Categoria categoria = new Categoria(nome, supercategoria);
            return categoria;
        }else {
            Categoria categoria = new Categoria(nome);
            return categoria;
        }
    }


}
