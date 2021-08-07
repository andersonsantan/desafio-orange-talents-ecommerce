package br.com.zup.mercadolivre.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(Long id){
        super("Produto com id "+id+" não encontrado");
    }
}
