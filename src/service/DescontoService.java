package service;

public class DescontoService {
    public static double aplicarDesconto(double total) {
        double porcentagemDesconto = 0.0;

        if (total > 500) {
            porcentagemDesconto = 0.85;
        } else if (total > 200) {
            porcentagemDesconto = 0.9;
        }

        return total * porcentagemDesconto;
    }
}
