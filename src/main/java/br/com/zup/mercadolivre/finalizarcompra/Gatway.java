package br.com.zup.mercadolivre.finalizarcompra;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Primary
@Component
public class Gatway implements GatwayPagamento {

    public String redirecionamentoGatway(UriComponentsBuilder componentsBuilder, ListaGatewayPagamento gateway, Compra novaCompra) {
        if (gateway.equals(ListaGatewayPagamento.PAGSEGURO)) {
            String urlRetornoPagSeguro = componentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
            return "pagseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagSeguro;

        } else {
            String urlRetornoPayPal = componentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
            return "paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPayPal;
        }
    }
}
