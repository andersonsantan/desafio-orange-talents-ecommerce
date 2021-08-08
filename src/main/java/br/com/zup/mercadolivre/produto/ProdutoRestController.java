package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.CategotiaRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepositori;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/produtos")
public class ProdutoRestController {

    private ProdutoRepository produtoRepository;
    private UsuarioRepositori usuarioRepositori;
    private CategotiaRepository categotiaRepository;
    private Uploader uploaderFake;

    public ProdutoRestController(ProdutoRepository produtoRepository, UsuarioRepositori usuarioRepositori, CategotiaRepository categotiaRepository, Uploader uploaderFake) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepositori = usuarioRepositori;
        this.categotiaRepository = categotiaRepository;
        this.uploaderFake = uploaderFake;
    }

    @PostMapping
    @Transactional
    public String criarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal Usuario usuario){
        Produto produto = produtoRepository.save(novoProdutoRequest.toModel(categotiaRepository,usuario));
        if(!produto.pertenceAoUsuario(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return produto.toString();
    }

    @PostMapping(value = "/produto/{id}/imagens")
    @Transactional
    public String adicionarImagens(@PathVariable("id")Long id, @Valid NovasImagensRequest request){
        Set<String> links  = uploaderFake.envia(request.getImagens());
        Produto produto = produtoRepository.findById(id).get();
        produto.associarImagens(links);
        produtoRepository.save(produto);
        return "Imagem armazenada";
    }

}
