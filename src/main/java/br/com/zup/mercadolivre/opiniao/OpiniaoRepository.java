package br.com.zup.mercadolivre.opiniao;

import br.com.zup.mercadolivre.detalheproduto.OpiniaoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
    @Query("select o from Opiniao o where o.produto.id= :id")
    Set<OpiniaoResponse> findOpiniaoPorIdProduto(Long id);

    @Query("select avg(o.nota) from Opiniao o where o.produto.id= :id")
    double mediaDasNotas(Long id);

    @Query("SELECT count(o.nota) as totalDeOpinioes FROM Opiniao o where o.produto.id= :id")
    int totalDeOpinioes(Long id);
}
