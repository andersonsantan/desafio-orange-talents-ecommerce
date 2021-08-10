package br.com.zup.mercadolivre.finalizarcompra;


import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.GatewayPagamento;
import org.springframework.web.util.UriComponentsBuilder;


public interface Gatway {
    String redirecionamentoGatway(UriComponentsBuilder componentsBuilder, GatewayPagamento gateway, Compra novaCompra);
}
