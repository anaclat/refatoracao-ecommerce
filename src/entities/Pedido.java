package entities;

import repositories.PedidoRepository;
import repositories.PedidoRepositoryBanco;
import service.DescontoService;
import service.FreteService;
import service.NotificacaoService;

import java.util.*;

public class Pedido {
    Carrinho carrinho;

    private final PedidoRepository pedidoRepository;

    public String clienteNome;
    public String clienteEmail;
    public String clienteEndereco;

    public double total;
    public double frete;
    public String status;

    public Pedido() {
        this.carrinho = new Carrinho();
        this.pedidoRepository = new PedidoRepositoryBanco();
    }

    public void adicionarItem(Produto produto, int qtd) {
        this.carrinho.adicionarItem(produto, qtd);
    }

    public void calcularTotal() {
        total = 0;
        Set<Produto> produtos = carrinho.getProdutos();
        for (Produto p : produtos) {
            total += carrinho.obterQuantidadeProduto(p) * p.getPreco();
        }
    }

    public void atualizarEstoque() {
        //for (String p : produtos) {
        //    System.out.println("Atualizando estoque de: " + p);
       // }
    }

    public void processarPagamento(String tipo) {
        switch (tipo) {
            case "cartao" -> System.out.println("Pagamento cartão OK");
            case "boleto" -> System.out.println("Boleto gerado");
            case "pix" -> System.out.println("PIX enviado");
        }
    }

    public void gerarRelatorio() {
        System.out.println("Relatorio do pedido:");
        for (Produto p : carrinho.getProdutos()) {
            System.out.println(p.getNome());
        }
        System.out.println("Total: " + total);
    }

    public void salvarNoBanco() {
        this.pedidoRepository.salvarPedido(this);
        this.pedidoRepository.salvarLog("entities.Pedido salvo: " + clienteNome);
    }

    public void finalizar() {
        calcularTotal();
        this.total = DescontoService.aplicarDesconto(this.total);
        FreteService.calcularFrete(this.total, this.clienteEndereco);
        atualizarEstoque();
        processarPagamento("cartao");
        NotificacaoService.enviarNotificacao(this.clienteEmail);
        gerarRelatorio();
        salvarNoBanco();
        status = "FINALIZADO";
    }
}