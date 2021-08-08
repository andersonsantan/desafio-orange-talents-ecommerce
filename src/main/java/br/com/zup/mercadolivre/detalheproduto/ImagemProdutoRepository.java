package br.com.zup.mercadolivre.detalheproduto;

import br.com.zup.mercadolivre.produto.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {

    @Query("select i.link from ImagemProduto i where i.produto.id= :id")
    Set<String> findByImagem(Long id);

}
