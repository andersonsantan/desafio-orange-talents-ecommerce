package br.com.zup.mercadolivre.config.security;

import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import br.com.zup.mercadolivre.cadastrousuario.UsuarioRepositori;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepositori.findByLogin(username);
        if(usuario.isPresent()){
            return (UserDetails) usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
