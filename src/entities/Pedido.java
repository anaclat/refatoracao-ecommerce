package entities;

import repositories.PedidoRepository;
import repositories.PedidoRepositoryBanco;
import service.DescontoService;
import service.FreteService;
import service.NotificacaoService;

import java.util.*;

public class Pedido {
    private Carrinho carrinho;
    private Cliente cliente;

    private double total;
    private double frete;
    private String status;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.carrinho = new Carrinho();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public double getFrete() {
        return frete;
    }

    public String getStatus() {
        return status;
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

    private void salvarNoBanco() {
        PedidoRepositoryBanco.salvarPedido(this);
        PedidoRepositoryBanco.salvarLog("entities.Pedido salvo: " + cliente.getNome());
    }

    public void finalizar() {
        calcularTotal();
        this.total = DescontoService.aplicarDesconto(this.total);
        this.frete = FreteService.calcularFrete(this.total, cliente.getEndereco());
        atualizarEstoque();
        processarPagamento("cartao");
        NotificacaoService.enviarNotificacao(cliente.getEmail());
        gerarRelatorio();
        salvarNoBanco();
        status = "FINALIZADO";
    }
}