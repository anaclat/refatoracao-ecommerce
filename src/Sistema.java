public class Sistema {

    public static void main(String[] args) {

        Estoque estoque = new Estoque();
        Pedido p = new Pedido();

        p.clienteNome = "Maria";
        p.clienteEmail = "maria@email.com";
        p.clienteEndereco = "SC";

        Produto notebook = new Produto("Notebook", 3000.0);
        Produto mouse = new Produto("Mouse", 100.0);



        p.adicionarItem(notebook, 1);
        p.adicionarItem(mouse, 2);

        p.finalizar();

        BancoDeDados.salvarLog("Sistema finalizado");

        RelatorioService r = new RelatorioService();
        r.gerar(p);

        System.out.println("Frete: " + p.frete);
        System.out.println("Status: " + p.status);
    }
}