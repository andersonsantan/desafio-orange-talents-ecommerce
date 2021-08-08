package br.com.zup.mercadolivre.pergunta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.SortedSet;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    @Query("select p.titulo from Pergunta p where p.produto.id= :id")
    SortedSet<String> findPerguntaPorIdProduto(Long id);

}
