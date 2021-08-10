package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Gatway;
import br.com.zup.mercadolivre.finalizarcompra.PagseguroGateway;
import br.com.zup.mercadolivre.finalizarcompra.PaypalGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;



public enum GatewayPagamento{

        PAGSEGURO(new PagseguroGateway())
    , PAYPAL(new PaypalGateway());

    private final Gatway gatway;

    GatewayPagamento(Gatway gatway) {
        this.gatway = gatway;
    }



    public Gatway getGatway(){
        return gatway;
    }
}
