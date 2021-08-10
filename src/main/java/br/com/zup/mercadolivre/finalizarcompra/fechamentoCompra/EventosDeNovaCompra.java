package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;


import br.com.zup.mercadolivre.finalizarcompra.Compra;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosDeNovaCompra {
    private Set<EventoCompraSucesso> eventoCompraSucesso;

    public EventosDeNovaCompra(Set<EventoCompraSucesso> eventoCompraSucesso) {
        this.eventoCompraSucesso = eventoCompraSucesso;
    }

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()){
            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
        }else {
            //emailSucesso.processa(compra);
        }
    }

}
