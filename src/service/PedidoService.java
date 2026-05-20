package service;

import entities.Pedido;
import enums.PagamentosEnum;
import enums.PedidoStatusEnum;
import repositories.PedidoRepositoryBanco;

public class PedidoService {

    public static void finalizarPedido(Pedido pedido, PagamentosEnum pagamento) {

        pedido.calcularTotal();

        pedido.setTotal(DescontoService.aplicarDesconto(pedido.getTotal())); ;

        pedido.setFrete(FreteService.calcularFrete(pedido.getTotal(), pedido.getCliente().getEndereco()));

        pedido.setTipoPagamento(pagamento);

        PagamentoService.processarPagamento(pedido.getTipoPagamento());

        EstoqueService.atualizarEstoque(pedido);

        PedidoRepositoryBanco.salvarPedido(pedido);

        NotificacaoService.enviarNotificacao(pedido.getCliente().getEmail());
        pedido.setStatus(PedidoStatusEnum.FINALIZADO);
    }
}
