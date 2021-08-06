package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.CategotiaRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepositori;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/produtos")
public class ProdutoRestController {

    ProdutoRepository produtoRepository;
    UsuarioRepositori usuarioRepositori;
    CategotiaRepository categotiaRepository;


    public ProdutoRestController(ProdutoRepository produtoRepository, UsuarioRepositori usuarioRepositori, CategotiaRepository categotiaRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepositori = usuarioRepositori;
        this.categotiaRepository = categotiaRepository;
    }

    @PostMapping
    public Produto criarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest){
        Usuario usuario = usuarioRepositori.findByEmail("anderson@email.com.br").get();
        Produto produto = produtoRepository.save(novoProdutoRequest.toModel(categotiaRepository,usuario));
        return produto;
    }

}
