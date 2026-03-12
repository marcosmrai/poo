package br.com.loja;

import br.com.loja.model.Produto;

public class Principal {
    public static void main(String[] args) {
        // INSTANCIAÇÃO: Objetos distintos na Heap
        Produto p1 = new Produto("Smartphone", 2000.0);
        Produto p2 = new Produto("Capa", 50.0);

        // DEMONSTRAÇÃO DE REFERÊNCIA VS IDENTIDADE
        System.out.println("P1 e P2 são o mesmo objeto? " + (p1 == p2)); // false

        // PASSAGEM POR REFERÊNCIA: p1 será alterado na Heap
        aplicarDescontoEspecial(p1);
        System.out.println("Novo status P1: " + p1.getStatus());

        // MEMBRO ESTÁTICO: Afeta p1 e p2 ao mesmo tempo
        Produto.setTaxaImposto(0.20);
        System.out.println("P2 após novo imposto: " + p2.getStatus());
    }

    public static void aplicarDescontoEspecial(Produto p) {
        p.setPreco(p.getPrecoFinal() * 0.9); // Altera o objeto original via referência
    }
}