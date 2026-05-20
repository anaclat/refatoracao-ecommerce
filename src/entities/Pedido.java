package entities;

import enums.PagamentosEnum;
import enums.PedidoStatusEnum;

import java.util.*;

public class Pedido {
    private Carrinho carrinho;
    private Cliente cliente;

    private double total;
    private double frete;
    private PagamentosEnum tipoPagamento;
    private PedidoStatusEnum status;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.carrinho = new Carrinho();
        this.status = PedidoStatusEnum.PENDENTE;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public PedidoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PedidoStatusEnum status) {
        this.status = status;
    }

    public void adicionarItem(Produto produto, int qtd) {
        this.carrinho.adicionarItem(produto, qtd);
    }

    public HashMap<Produto, Integer> getProdutos() {
        return carrinho.getProdutos();
    }

    public double getTotal() {return total;}

    public void setTotal(double total) {this.total = total;}

    public void setFrete(double frete) {this.frete = frete;}

    public double getFrete() {return frete;}

    public void setTipoPagamento(PagamentosEnum tipoPagamento) {this.tipoPagamento = tipoPagamento;}

    public PagamentosEnum getTipoPagamento() {return tipoPagamento;}

    public void calcularTotal() {
        total = 0;
        Set<Produto> produtos = carrinho.getProdutos().keySet();
        for (Produto p : produtos) {
            total += carrinho.obterQuantidadeProduto(p) * p.getPreco();
        }
    }

//    public void atualizarEstoque() {
//        //for (String p : produtos) {
//        //    System.out.println("Atualizando estoque de: " + p);
//       // }
//    }

//    public void processarPagamento(String tipo) {
//        switch (tipo) {
//            case "cartao" -> System.out.println("Pagamento cartão OK");
//            case "boleto" -> System.out.println("Boleto gerado");
//            case "pix" -> System.out.println("PIX enviado");
//        }
//    }


//    private void salvarNoBanco() {
//        PedidoRepositoryBanco.salvarPedido(this);
//        PedidoRepositoryBanco.salvarLog("entities.Pedido salvo: " + cliente.getNome());
//    }
//
//    public void finalizar() {
//        calcularTotal();
//        this.total = DescontoService.aplicarDesconto(this.total);
//        this.frete = FreteService.calcularFrete(this.total, cliente.getEndereco());
//        atualizarEstoque();
//        processarPagamento("cartao");
//        NotificacaoService.enviarNotificacao(cliente.getEmail());
//        //gerarRelatorio();
//        salvarNoBanco();
//        status = "FINALIZADO";
//    }
}