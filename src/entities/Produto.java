package entities;

public class Produto {
    private String nome;
    private Double preco;
    private int quantidadeDisponivel;

    public Produto(String nome, Double preco, int quantidadeDisponivel) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco(){
        return this.preco;
    }

    public void incrementarQuantidade(int quantidade) {
        this.quantidadeDisponivel += quantidade;
    }

    public void decrementarQuantidade(int quantidade) {
        this.quantidadeDisponivel -= quantidade;
    }
}
