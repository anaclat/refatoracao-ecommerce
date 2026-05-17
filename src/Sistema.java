import entities.Cliente;
import entities.Pedido;
import entities.Produto;
import repositories.PedidoRepositoryBanco;
import service.RelatorioService;

public class Sistema {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Maria", "maria@email.com", "SC");
        Pedido p = new Pedido(cliente);

        Produto notebook = new Produto("Notebook", 3000.0);
        Produto mouse = new Produto("Mouse", 100.0);

        p.adicionarItem(notebook, 1);
        p.adicionarItem(mouse, 2);

        p.finalizar();

        PedidoRepositoryBanco.salvarLog("Sistema finalizado");

        RelatorioService r = new RelatorioService();
        r.gerar(p);

        System.out.println("Frete: " + p.getFrete());
        System.out.println("Status: " + p.getStatus());
    }
}