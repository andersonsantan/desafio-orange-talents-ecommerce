package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;
import br.com.zup.mercadolivre.finalizarcompra.CompraRepository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class FechamentoCompraController {

    private CompraRepository compraRepository;

    private EventosDeNovaCompra eventosDeNovaCompra;

    public FechamentoCompraController(CompraRepository compraRepository, EventosDeNovaCompra eventosDeNovaCompra) {
        this.compraRepository = compraRepository;
        this.eventosDeNovaCompra = eventosDeNovaCompra;
    }

    @PostMapping(path = "/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPagSeguroRequest pagSeguroRequest) {
        return processa(idCompra, pagSeguroRequest);
    }

    @PostMapping(path = "/retorno-paypal/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPaypalRequest paypalRequest) {
        return processa(idCompra, paypalRequest);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = compraRepository.getById(idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        compraRepository.save(compra);
        eventosDeNovaCompra.processa(compra);
        return compra.toString();
    }




}
