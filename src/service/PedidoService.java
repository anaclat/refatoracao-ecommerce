package service;

import entities.Pedido;
import repositories.PedidoRepositoryBanco;

public class PedidoService {

    public void finalizarPedido(Pedido pedido) {

        pedido.calcularTotal();

        pedido.setTotal(DescontoService.aplicarDesconto(pedido.getTotal())); ;

        pedido.setFrete(FreteService.calcularFrete(pedido.getTotal(), pedido.getCliente().getEndereco()));

        PagamentoService.processarPagamento(pedido.getTipoPagamento());

        //estoqueService.baixarItens(pedido);

        PedidoRepositoryBanco.salvarPedido(pedido);

        NotificacaoService.enviarNotificacao(pedido.getCliente().getEmail());
    }
}
