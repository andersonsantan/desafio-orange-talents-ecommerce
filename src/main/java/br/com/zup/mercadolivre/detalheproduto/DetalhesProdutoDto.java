package br.com.zup.mercadolivre.detalheproduto;

import br.com.zup.mercadolivre.opiniao.OpiniaoRepository;
import br.com.zup.mercadolivre.pergunta.PerguntaRepository;
import br.com.zup.mercadolivre.produto.CaracteristicaRepository;
import br.com.zup.mercadolivre.produto.Produto;

import java.util.Set;
import java.util.SortedSet;

public class DetalhesProdutoDto {

    private String nome;
    private String descricao;
    private Double preco;
    private Set<CaracteristicaProdutoView> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<OpiniaoResponse> opinioes;
    private double mediaNotas;
    private int totalDeAvaliacoes;

    public DetalhesProdutoDto() {
    }

    public DetalhesProdutoDto(Produto produto, CaracteristicaRepository caracteristicaRepository,
                              ImagemProdutoRepository imagemRepository,
                              PerguntaRepository perguntaRepository, OpiniaoRepository opiniaoRepository) {

        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.caracteristicas = getCaracteristicas(caracteristicaRepository, produto.getId());
        this.linksImagens = getLinksImagens(imagemRepository, produto.getId());
        this.perguntas = getPerguntasList(perguntaRepository, produto.getId());
        this.opinioes = getOpinioesList(opiniaoRepository, produto.getId());
        this.mediaNotas = getMediaNotas(opiniaoRepository, produto.getId());
        this.totalDeAvaliacoes = getTotalOpinioes(opiniaoRepository, produto.getId());
    }

    private Set<CaracteristicaProdutoView> getCaracteristicas(CaracteristicaRepository caracteristicaRepository, Long id){
        Set<CaracteristicaProdutoView> caracteristicaProduto = caracteristicaRepository.findAllByProdutoDto(id);
       return caracteristicaProduto;
    }
    private Set<String> getLinksImagens(ImagemProdutoRepository imagemRepository, Long id){
        Set<String> imagemProduto = imagemRepository.findByImagem(id);
        return  imagemProduto;
    }
    private SortedSet<String> getPerguntasList(PerguntaRepository perguntaRepository, Long id){
        SortedSet<String> perguntas = perguntaRepository.findPerguntaPorIdProduto(id);
        return perguntas;
    }
    private Set<OpiniaoResponse> getOpinioesList(OpiniaoRepository opiniaoRepository, Long id){
        Set<OpiniaoResponse> opinioes = opiniaoRepository.findOpiniaoPorIdProduto(id);
        return opinioes;
    }
    private double getMediaNotas(OpiniaoRepository opiniaoRepository, Long id){
    double mediaNotas = opiniaoRepository.mediaDasNotas(id);
    return mediaNotas;
    }
    private int getTotalOpinioes(OpiniaoRepository opiniaoRepository, Long id){
        int total = opiniaoRepository.totalDeOpinioes(id);
        return total;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<CaracteristicaProdutoView> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }
}
