package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.GatewayPagamento;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    private CompraRepository compraRepository;



    public CompraController(EntityManager manager, CompraRepository compraRepository) {
        this.manager = manager;
        this.compraRepository = compraRepository;
    }

    @PostMapping(value = "/api/v1/compras")
    @Transactional
    public String postMethodName(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal Usuario usuario,
                                 UriComponentsBuilder componentsBuilder, RedirectAttributes redirectAttributes) throws BindException {
        Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());
        boolean abateEstoque = produtoASerComprado.abateEstoque(request.getQuantidade());

        if (abateEstoque){
            GatewayPagamento gateway = request.gatewayPagamento();
            Compra novaCompra = new Compra(produtoASerComprado, request.getQuantidade(), usuario, request.gatewayPagamento());
            compraRepository.save(novaCompra);

            String redirect = request.gatewayPagamento().getGatway().redirecionamentoGatway(componentsBuilder,gateway,novaCompra);
            return redirect;
        }

        BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
        problemaComEstoque.reject(null, "Não fou possível realizar a compra, estoque abaixo da quantidade solicitada.");
        throw problemaComEstoque;
    }



}
