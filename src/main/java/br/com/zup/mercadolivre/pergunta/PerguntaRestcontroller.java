package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.config.exception.ProdutoNotFoundException;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntaRestcontroller {

    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private DisparadorEmail enviadorDeEmail;

    public PerguntaRestcontroller(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, DisparadorEmail enviadorDeEmail) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.enviadorDeEmail = enviadorDeEmail;
    }

    @PostMapping(path = "api/v1/produtos/{id}/perguntas")
    @Transactional
    public String fazerPergunta(@PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuario, @RequestBody @Valid NovaPerguntaRequest request)
            throws ProdutoNotFoundException {
        Produto produto = produtoRepository.findById(id).orElseThrow(()->new ProdutoNotFoundException(id));
        Pergunta pergunta = perguntaRepository.save(request.toModel(produto, usuario));
        enviadorDeEmail.enviarEmail(pergunta);
        return pergunta.toString();
    }
}
