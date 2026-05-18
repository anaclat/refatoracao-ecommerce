package service;

public class DescontoService {
    public static double aplicarDesconto(double total) {
        double porcentagemDesconto = 0.0;

        if (total > 500) {
            porcentagemDesconto = 0.85;
        } else if (total > 200) {
            porcentagemDesconto = 0.9;
        }

        if (porcentagemDesconto == 0.0) {
            return total; // retorna o total original se nenhum desconto for aplicado
        } else {

            return total * porcentagemDesconto;
        }
    }
}
