package br.com.zup.mercadolivre.detalheproduto;

import br.com.zup.mercadolivre.config.exception.ProdutoNotFoundException;
import br.com.zup.mercadolivre.opiniao.OpiniaoRepository;
import br.com.zup.mercadolivre.pergunta.PerguntaRepository;
import br.com.zup.mercadolivre.produto.CaracteristicaRepository;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class DetalhesProdutoController {

    private CaracteristicaRepository caracteristicaRepository;
    private ProdutoRepository produtoRepository;
    private ImagemProdutoRepository imagemRepository;
    private PerguntaRepository perguntaRepository;
    private OpiniaoRepository opiniaoRepository;

    public DetalhesProdutoController(CaracteristicaRepository caracteristicaRepository, ProdutoRepository produtoRepository,
                                     ImagemProdutoRepository imagemRepository, PerguntaRepository perguntaRepository,
                                     OpiniaoRepository opiniaoRepository) {

        this.caracteristicaRepository = caracteristicaRepository;
        this.produtoRepository = produtoRepository;
        this.imagemRepository = imagemRepository;
        this.perguntaRepository = perguntaRepository;
        this.opiniaoRepository = opiniaoRepository;
    }

    @GetMapping(path = "api/v1/produtos/{id}/detalhe-produto")
    @Transactional
    public DetalhesProdutoDto getDetalhes(@PathVariable ("id") Long id) throws ProdutoNotFoundException {
        Produto produto = produtoRepository.findById(id).orElseThrow(()-> new ProdutoNotFoundException(id));
        DetalhesProdutoDto dto = new DetalhesProdutoDto(produto, caracteristicaRepository, imagemRepository, perguntaRepository, opiniaoRepository);
        return dto;
    }
}
