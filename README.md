# Exercícios Aula 4: SRP e Associações


## Exercício 1: A "Classe Deus" e o Desafio da Coesão (SRP)

**Objetivo:** Identificar os sintomas de uma classe sobrecarregada e entender como o acoplamento excessivo prejudica a evolução do software.

**Contexto:** No estágio atual do projeto, a classe `Pedido` assumiu o papel de "Classe Deus". Ela não apenas gerencia os itens da compra, mas também realiza um "malabarismo" lógico para processar diferentes tipos de pagamento e detém o conhecimento técnico de como disparar e-mails via SMTP.

**Tarefa:** Analise o código da classe `Pedido.java` e responda:

1.  **O Problema do Malabarismo de Tipos:** Por que o uso de `instanceof` para diferenciar `Cartao`, `Boleto` e `Pix` dentro de `Pedido` viola o Princípio do Aberto/Fechado (OCP)? O que aconteceria se decidíssemos adicionar o "Pagamento via Cripto" amanhã?
2.  **A Armadilha da Infraestrutura:** Atualmente, a lógica de comunicação (e-mail) está "chumbada" dentro de um método do `Pedido`. Se a empresa decidir trocar o envio de e-mail por uma mensagem de WhatsApp, por que somos obrigados a alterar a classe `Pedido`?
3.  **Diagnóstico LCOM (Qualitativo):** Observe os atributos `itens` e `emailCliente`. Existe algum método que utilize ambos simultaneamente? Se a resposta for não, o que isso nos diz sobre a coesão desta classe?

## Exercício 2: Implementando Composição (Cartão e Histórico)

**Objetivo:** Praticar a criação de objetos onde o "Todo" controla o ciclo de vida das "Partes".

**Contexto:** Anteriormente, o `Cliente` recebia um `Cartao` de fora (Agregação). Agora, para garantir que os dados financeiros fiquem restritos ao contexto da transação, o `Pedido` deve ser o dono do `Cartao`.

**Tarefa:**
1. Altere o construtor do `Pedido` para não receber mais um objeto `Pagavel`, mas sim os dados necessários (ex: `limiteDisponivel`) para criar um `Cartao` internamente.
2. Implemente a classe `HistoricoStatus` como uma **classe interna privada** (*private inner class*) dentro de `Pedido`.
3. **Desafio de Reflexão:** - Tente instanciar um `HistoricoStatus` dentro da classe `Principal`. O que acontece? Por que o compilador impede isso?
   - Se o objeto `Pedido` for definido como `null` na `Principal`, o que acontece com o objeto `Cartao` que estava dentro dele após a passagem do Garbage Collector?

## Exercício 3: Notificação e Dependência

**Objetivo:** Diferenciar uma associação estrutural de uma dependência funcional.

**Tarefa:**
1. Crie o método `confirmar(Notificador servico)` na classe `Pedido`.
2. Observe que o `Notificador` não é um atributo do `Pedido`. Ele é apenas usado como um parâmetro temporário para enviar a mensagem.
3. **Pergunta:** Por que essa relação é uma **Dependência** e não uma Composição?