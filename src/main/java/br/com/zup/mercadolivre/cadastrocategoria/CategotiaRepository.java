package br.com.zup.mercadolivre.cadastrocategoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategotiaRepository extends JpaRepository<Categoria, Long> {
}
