package br.com.zup.mercadolivre.finalizarcompra;


import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.GatewayPagamento;
import org.springframework.web.util.UriComponentsBuilder;


public class PaypalGateway implements Gatway{
    @Override
    public String redirecionamentoGatway(UriComponentsBuilder componentsBuilder, GatewayPagamento gateway, Compra novaCompra) {
        String urlRetornoPayPal = componentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
        return "paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPayPal;
    }
}
