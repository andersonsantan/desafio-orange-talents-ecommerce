package br.com.zup.mercadolivre.opiniao;

import br.com.zup.mercadolivre.config.exception.ProdutoNotFoundException;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpiniaoRestController {


    private OpiniaoRepository opiniaoRepository;
    private ProdutoRepository produtoRepository;

    public OpiniaoRestController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/api/v1/produtos/{id}/opiniao")
    @Transactional
    public String criarOpiniao(@AuthenticationPrincipal Usuario usuario, @PathVariable("id") Long id,
                             @RequestBody @Valid NovaOpiniaoRequest request) throws ProdutoNotFoundException {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
        Opiniao opiniao = opiniaoRepository.save(request.toModel(usuario, produto));
        return opiniao.toString();

    }
}
