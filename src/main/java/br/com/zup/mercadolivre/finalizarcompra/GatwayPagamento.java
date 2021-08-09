package br.com.zup.mercadolivre.finalizarcompra;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;



public interface GatwayPagamento {
    String redirecionamentoGatway(UriComponentsBuilder componentsBuilder, ListaGatewayPagamento gateway, Compra novaCompra);
}
