package br.com.zup.mercadolivre.cadastrousuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositori extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
