package service;

public class FreteService {
    public static double calcularFrete(double total, String endereco) {
        double frete = 0.0;

        if (endereco.contains("SC")) {
            frete = total * 0.05;
        } else {
            frete = total * 0.15;
        }

        return frete;
    }
}
