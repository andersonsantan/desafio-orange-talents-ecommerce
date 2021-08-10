package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso{

    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idDonoProduto", compra.getDonodoProduto().getId());
        System.out.println(request);
        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
    }
}
