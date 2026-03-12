package br.com.loja.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Carrinho {
    private double total;
    private ArrayList<Produto> itens;

    public Carrinho() {
        // A "forma" é List, mas o "motor" na Heap é ArrayList
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    public void adicionarItem(Produto p) {
        this.itens.add(p);
        this.total += p.precoFinal(); // Mantendo a Invariante
    }

    public void removerItem(Produto p) {
        this.itens.remove(p);
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

    public double getTotal() { return this.total; }
}