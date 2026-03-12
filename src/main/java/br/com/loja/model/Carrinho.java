package br.com.loja.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Carrinho {
    private double total;
    // PROGRAMAR PARA INTERFACE: Usamos List (Abstração)
    private List<Produto> itens;

    public Carrinho() {
        // A "forma" é List, mas o "motor" na Heap é ArrayList
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    public void adicionarItem(Produto p) {
        if (p == null) throw new IllegalArgumentException("Produto nulo."); // Fail-Fast
        this.itens.add(p);
        this.total += p.precoFinal(); // Mantendo a Invariante
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