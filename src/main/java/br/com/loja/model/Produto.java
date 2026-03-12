package br.com.loja.model;

/**
 * Representa um item comercializável.
 * Design focado em proteger a integridade dos dados (Invariantes).
 */
public class Produto {
    // ESTADO: Atributos privados para "Information Hiding"
    private String nome;
    private double preco;

    // MEMBRO ESTÁTICO: Compartilhado por todas as instâncias (Economia de memória)
    private static double taxaImposto = 0.10; 

    // CONSTRUTOR: A Base da Indução. Garante que o objeto nasce válido.
    public Produto(String nome, double preco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório."); // Fail-Fast
        }
        this.nome = nome; // 'this' resolve conflito de escopo
        this.alteraPreco(preco); // Reuso da lógica de validação
    }

    // COMPORTAMENTO: Interface Pública (O Contrato)
    private void alteraPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo."); // Defesa de Invariante
        }
        this.preco = preco;
    }

    public double precoFinal() {
        return this.preco * (1 + taxaImposto); // Interação entre estado local e estático
    }

    // Método Estático: Altera a regra para todos os objetos simultaneamente
    public static void alteraTaxaImposto(double novaTaxa) {
        taxaImposto = novaTaxa;
    }

    public String getStatus() {
        return String.format("Produto: %s | Preço com Imposto: %.2f", nome, precoFinal());
    }

    public String toString() {
        return String.format("Produto{nome='%s', preco=%.2f}", nome, preco);
    }
}