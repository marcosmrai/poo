package br.com.loja.model;
import br.com.loja.service.Cartao;

/**
 * Representa o usuário do sistema.
 * Demonstra a associação entre classes no modelo de domínio.
 */
public class Cliente {
    private String nome;
    private Cartao cartao; // Associação: o cliente "tem" um cartão

    public Cliente(String nome) {
        // Princípio Fail-Fast: garante que o objeto não nasça sem nome [cite: 775, 903]
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }
        this.nome = nome;
    }

    // Método para vincular um cartão ao cliente
    public void cadastrarCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Cartao getCartao() {
        return this.cartao;
    }

    public String consultaNome() {
        return this.nome;
    }

    public String toString() {
        return String.format("Cliente: %s, Cartão: %s", 
            nome, (cartao != null ? cartao.getNumero() : "Nenhum"));
    }

    public void pagar(double valor) {
    }
}
