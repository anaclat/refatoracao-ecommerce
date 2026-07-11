# Refatoracao Ecommerce

Projeto didático de um fluxo de compra (e-commerce) refatorado para separar responsabilidades. O objetivo é demonstrar como organizar as partes do sistema em camadas de entidades, serviços e repositórios.

## Estrutura do projeto

- `src/Sistema.java`
  - Classe com o `main` que demonstra a criação de um cliente, montagem de um pedido com produtos e finalização do pedido.

- `src/entities/`
  - Contém as classes de domínio (entidades) do e-commerce.
  - `Cliente`: nome, email e endereço.
  - `Produto`: nome, preço e quantidade disponível.
  - `Carrinho`: mantém os itens do pedido (produto -> quantidade).
  - `Pedido`: representa a compra do cliente e mantém referência ao carrinho, total, frete, tipo de pagamento e status.

- `src/enums/`
  - Enumeradores usados para padronizar valores.
  - `PagamentosEnum`: DINHEIRO, CARTAO, PIX, BOLETO.
  - `PedidoStatusEnum`: PENDENTE, FINALIZADO.

- `src/service/`
  - Contém classes responsáveis por regras de negócio e operações do fluxo.
  - `PedidoService`: orquestra a finalização do pedido.
  - `DescontoService`: calcula desconto com base no total.
  - `FreteService`: calcula frete com base no endereço.
  - `PagamentoService`: simula processamento do pagamento.
  - `EstoqueService`: atualiza a quantidade disponível dos produtos.
  - `NotificacaoService`: simula envio de notificação por e-mail.
  - `RelatorioService`: gera um relatório simples do pedido.

- `src/repositories/`
  - Contém abstrações e implementações de persistência.
  - `PedidoRepository`: interface (neste projeto, métodos estáticos apenas para fins didáticos).
  - `PedidoRepositoryBanco`: implementação que simula salvar pedido e log no “banco” via `System.out.println`.

## Fluxo do pedido (visão geral)

1. O `main` cria um `Cliente` e um `Pedido`.
2. Produtos são adicionados ao pedido (via `Pedido.adicionarItem`, que delega ao `Carrinho`).
3. Ao finalizar o pedido, `PedidoService.finalizarPedido(pedido, pagamento)` executa:
   - `pedido.calcularTotal()` (soma das quantidades x preços)
   - aplicação de desconto (`DescontoService.aplicarDesconto`)
   - cálculo de frete (`FreteService.calcularFrete`)
   - definição do tipo de pagamento no pedido
   - processamento do pagamento (`PagamentoService.processarPagamento`)
   - atualização de estoque (`EstoqueService.atualizarEstoque`)
   - persistência simulada (`PedidoRepositoryBanco.salvarPedido`)
   - notificação simulada por e-mail (`NotificacaoService.enviarNotificacao`)
   - atualização do status para `FINALIZADO`

## Regras de negócio implementadas

- Desconto (`DescontoService`):
  - se `total > 500`, aplica 15% de desconto (multiplica por 0.85)
  - se `total > 200` e `total <= 500`, aplica 10% de desconto (multiplica por 0.90)
  - caso contrário, não aplica desconto

- Frete (`FreteService`):
  - se o endereço contém `"SC"`, frete = `total * 0.05`
  - caso contrário, frete = `total * 0.15`

- Estoque (`EstoqueService`):
  - para cada produto no carrinho, decrementa a quantidade disponível pela quantidade comprada.

- Status (`PedidoStatusEnum`):
  - o pedido inicia como `PENDENTE`
  - após finalizar, muda para `FINALIZADO`.

## Como executar

1. Abra o projeto em uma IDE configurada para Java.
2. Execute `src/Sistema.java`.

O programa exibirá mensagens no console simulando pagamento, salvamento e notificações, além de imprimir frete e status ao final.

## Observações

Este projeto foi refatorado com foco em:

- Separação de responsabilidades (classe `Pedido` ficou focada no domínio; as regras de negócio ficaram em classes `*Service`).
- Encapsulamento via atributos privados e getters/setters.
- Uso de `enum` para valores padronizados de pagamento e status.
- Organização por pacotes: `entities`, `enums`, `service` e `repositories`.
