package br.com.loja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {
    private double total;
    private ArrayList<Produto> itens;
    private Cliente cliente;

    public Carrinho(Cliente cliente) {
        // A "forma" é List, mas o "motor" na Heap é ArrayList
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    public void adicionarItem(Produto p) {
        if (p == null) throw new IllegalArgumentException("Produto nulo.");
        this.itens.add(p);
        this.total += p.precoFinal(); // Mantendo a Invariante
    }

    public void removerItem(Produto p) {
        this.itens.remove(p);
        this.total -= p.precoFinal();
    }

    public boolean confereTotal(ArrayList<Produto> itens) {
        double soma = 0.0;
        for (Produto p : itens) {
            soma += p.precoFinal();
        }
        return soma == this.total; // Verifica a Invariante
    }

    /**
     * Cópia Defensiva: Protege o encapsulamento.
     * Impede que o mundo externo altere a lista diretamente (ex: itens.clear()).
     */
    public List<Produto> getItens() {
        return Collections.unmodifiableList(this.itens);
    }

    // Modifique apenas o método checkout na classe Carrinho
    public void checkout(Pagavel metodoPagamento, String emailCliente) {
        if (this.itens.isEmpty()) {
            System.out.println("Carrinho vazio. Não é possível realizar checkout.");
            return;
        }
        System.out.println("\nIniciando checkout...");
        
        // O Carrinho delega o pagamento e e-mail para o Pedido
        Pedido pedido = new Pedido(this.getItens(), 0.0, this.getTotal(), metodoPagamento, emailCliente);
        cliente.cadastraPedido(pedido);
        
        System.out.println("Pedido criado e atrelado ao cliente. Pendente de verificação de pagamento.");
    }

    public double getTotal() { return this.total; }

    public Cliente getCliente() { return this.cliente; }
}