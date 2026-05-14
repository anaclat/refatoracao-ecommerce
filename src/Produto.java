public class Produto {
    private String nome;
    private Double preco;

    Produto (String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco(){
        return this.preco;
    }
}
