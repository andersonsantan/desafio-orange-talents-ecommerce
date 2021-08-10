package br.com.zup.mercadolivre.finalizarcompra;


import br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra.GatewayPagamento;
import org.springframework.web.util.UriComponentsBuilder;


public class PagseguroGateway implements Gatway{
    @Override
    public String redirecionamentoGatway(UriComponentsBuilder componentsBuilder, GatewayPagamento gateway, Compra novaCompra) {
        String urlRetornoPagSeguro = componentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
        return "pagseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagSeguro;
    }
}
