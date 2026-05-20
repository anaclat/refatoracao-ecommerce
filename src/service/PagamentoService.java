package service;

import enums.PagamentosEnum;

public class PagamentoService {
    public static void processarPagamento(PagamentosEnum tipo) {
        switch (tipo) {
            case CARTAO -> System.out.println("Pagamento cartão OK");
            case BOLETO -> System.out.println("Boleto gerado");
            case PIX -> System.out.println("PIX enviado");
            default -> System.out.println("Pagamento em dinheiro");
        }
    }
}
