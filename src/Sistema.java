import entities.Cliente;
import entities.Pedido;
import entities.Produto;
import enums.Pagamentos;
import repositories.PedidoRepositoryBanco;
import service.PedidoService;
import service.RelatorioService;

public class Sistema {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Maria", "maria@email.com", "SC");
        Pedido p = new Pedido(cliente);

        Produto notebook = new Produto("Notebook", 3000.0, 30);
        Produto mouse = new Produto("Mouse", 100.0, 20);

        p.adicionarItem(notebook, 1);
        p.adicionarItem(mouse, 2);

        PedidoService.finalizarPedido(p, Pagamentos.CARTAO);

        PedidoRepositoryBanco.salvarLog("Sistema finalizado");

        RelatorioService r = new RelatorioService();
        r.gerar(p);

        System.out.println("Frete: " + p.getFrete());
        System.out.println("Status: " + p.getStatus());
    }
}