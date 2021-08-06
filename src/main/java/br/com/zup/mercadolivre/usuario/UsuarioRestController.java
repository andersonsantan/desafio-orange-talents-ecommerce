package br.com.zup.mercadolivre.usuario;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/usuarios")
public class UsuarioRestController {

    private UsuarioRepositori usuarioRepositori;

    public UsuarioRestController(UsuarioRepositori usuarioRepositori) {
        this.usuarioRepositori = usuarioRepositori;
    }

    @PostMapping
    @Transactional
    public String cadastraUsuario(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest){
        Usuario usuario= usuarioRepositori.save(novoUsuarioRequest.toModel());
        return "Usuario criado com sucesso ";
    }
}
