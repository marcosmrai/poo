# Exercícios Aula 4: SRP e Associações

## Exercício 1: Identificando a Classe Deus
Analise a classe `Pedido.java`. 
1. Por que o uso de `instanceof` para diferenciar `Cartao` e `Boleto` prejudica a extensão do sistema?
2. Se mudarmos o servidor de E-mail, por que precisamos alterar a classe `Pedido`?

## Exercício 2: Implementando Composição
Refatore a classe `Pedido` para gerenciar seu próprio histórico:
- Crie uma classe interna privada `HistoricoStatus`.
- Garanta que nenhum objeto externo consiga criar um `HistoricoStatus` sem ser através de um `Pedido`.

## Exercício 3: Delegação e Polimorfismo
1. No método `verificarPagamento`, remova as verificações de tipo.
2. Faça o `Pedido` delegar o processamento para a interface `Pagavel`.
3. Adicione um novo método de pagamento `Pix` e verifique se o `Pedido` precisa ser alterado para aceitá-lo (Dica: se precisar, o SRP/OCP foi violado).