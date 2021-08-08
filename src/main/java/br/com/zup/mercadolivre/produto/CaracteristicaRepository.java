package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.detalheproduto.CaracteristicaProdutoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CaracteristicaRepository extends JpaRepository<CaracteristicaProduto, Long> {
    @Query("select p from CaracteristicaProduto p where p.produto.id= :id")
    Set<CaracteristicaProdutoView> findAllByProdutoDto(Long id);
}
