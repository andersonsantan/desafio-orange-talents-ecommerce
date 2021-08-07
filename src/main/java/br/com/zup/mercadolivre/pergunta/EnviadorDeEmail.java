package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class EnviadorDeEmail implements DisparadorEmail{

    public void enviarEmail(Pergunta pergunta) {
        System.out.println(pergunta.toString());
    }
}
