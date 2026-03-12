package br.com.loja;

import br.com.loja.model.Cliente;
import br.com.loja.model.Produto;
import br.com.loja.service.Cartao;

public class Principal {
    public static void main(String[] args) {
        int opcao = -1;
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (opcao != 0) {
            System.out.println("\n=== MENU DE EXEMPLOS ===");
            System.out.println("1. Entendendo Objetos");
            System.out.println("2. Princípio de Demeter");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    entendendoObjetos();
                    break;
                case 2:
                    demeterIssue();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    public static void aplicarDescontoEspecial(Produto p) {
        p.alteraPreco(p.precoFinal() * 0.9); // Altera o objeto original via referência
    }
    
    // Aula 1: Entendendo Objetos, Referências e Membros Estáticos
    private static void entendendoObjetos() {
        // INSTANCIAÇÃO: Objetos distintos na Heap
        Produto p1 = new Produto("Smartphone", 2000.0);
        Produto p2 = new Produto("Capa", 50.0);

        // DEMONSTRAÇÃO DE REFERÊNCIA VS IDENTIDADE
        System.out.println("P1 e P2 são o mesmo objeto? " + (p1 == p2)); // false

        // PASSAGEM POR REFERÊNCIA: p1 será alterado na Heap
        aplicarDescontoEspecial(p1);
        System.out.println("Novo status P1: " + p1.getStatus());

        // MEMBRO ESTÁTICO: Afeta p1 e p2 ao mesmo tempo
        Produto.alteraTaxaImposto(0.20);
        System.out.println("P2 após novo imposto: " + p2.getStatus());
    }

    private static void demeterIssue() {
        // Exemplo de Violação do Princípio de Demeter
        // Cliente -> Carrinho -> Produto -> Preço
        // O cliente não deveria conhecer os detalhes internos do carrinho ou produto
        // Dentro do main em Principal.java
        Cliente cliente = new Cliente("João Silva");
        Cartao cartaoDoJoao = new Cartao(); // Limite baixo para teste

        cliente.cadastrarCartao(cartaoDoJoao);

        // Simulando uma compra que excede o limite
        double valorCompra = 500.0;
        cliente.getCartao().processar(valorCompra);
    }

    private static void demeterSolution() {
        System.out.println("Não Implementado.");
    }
    
}