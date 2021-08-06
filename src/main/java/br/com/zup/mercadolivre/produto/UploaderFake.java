package br.com.zup.mercadolivre.produto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader{

    /**
     *
     * @param imagens
     * @return links para imagens que foram enviadas
     */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(imagen -> "http://bucket.io/"+imagen.getOriginalFilename()).collect(Collectors.toSet());
    }
}
