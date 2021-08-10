package br.com.zup.mercadolivre.finalizarcompra.fechamentoCompra;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criarNota(@RequestBody @Valid NovaCompraNFRequest request) throws InterruptedException{
        System.out.println("Criando nota para "+request);
        Thread.sleep(150);
    }
    @PostMapping(value = "/ranking")
    public void ranking(@RequestBody @Valid RankingNovoCompraRequest request) throws InterruptedException{
        System.out.println("Criando ranking para "+request);
        Thread.sleep(150);
    }

}
