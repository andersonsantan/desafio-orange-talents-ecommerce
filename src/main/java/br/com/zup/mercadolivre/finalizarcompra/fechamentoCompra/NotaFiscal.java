package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import br.com.zup.mercadolivre.finalizarcompra.Compra;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso{

    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        System.out.println(request);
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
