package br.com.zup.mercadolivre.config.security;

import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import br.com.zup.mercadolivre.cadastrousuario.UsuarioRepositori;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    UsuarioRepositori usuarioRepositori;

    public AutenticacaoService(UsuarioRepositori usuarioRepositori) {
        this.usuarioRepositori = usuarioRepositori;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepositori.findByEmail(email);
        if (usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
