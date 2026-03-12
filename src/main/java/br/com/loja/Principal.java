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
            System.out.println("2. Memória e Escopo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    Aula1.entendendoObjetos();
                    break;
                case 2:
                    Aula1.demonstrandoMemoriaEEscopo();
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
}

class Aula1 {
        // Aula 1: Entendendo Objetos, Referências e Membros Estáticos
    static void entendendoObjetos() {
        // INSTANCIAÇÃO: Objetos distintos na Heap
        Produto p1 = new Produto("Smartphone", 2000.0);
        Produto p2 = new Produto("Capa", 50.0);

        // DEMONSTRAÇÃO DE REFERÊNCIA VS IDENTIDADE
        System.out.println("P1 e P2 são o mesmo objeto? " + (p1 == p2)); // false

        // PASSAGEM POR REFERÊNCIA: p1 será alterado na Heap
        aplicarDescontoEspecial(p1);
        System.out.println("Novo status P1:\n" + p1.toString());

        // MEMBRO ESTÁTICO: Afeta p1 e p2 ao mesmo tempo
        Produto.atualizaTaxaImposto(0.20);
        System.out.println("P2 após novo imposto:\n" + p2.toString());
    }
    
    // Aula 1: Primitivos vs. Referências e Ciclo de Vida (GC)
    static void demonstrandoMemoriaEEscopo() {
        // 1. Tipos Primitivos (Vivem na Stack)
        double valorOriginal = 1000.0;
        tentarMudarPrimitivo(valorOriginal);
        System.out.println("Primitivo após método: " + valorOriginal); // Continua 1000.0

        // 2. Referências (O endereço vive na Stack, o objeto na Heap)
        Produto p1 = new Produto("Tablet", 1500.0);
        confirmarMudancaReferencia(p1);
        System.out.println("Objeto após método: " + p1.toString()); // Preço mudou para 1200.0

        // 3. Ciclo de Vida e Inalcançabilidade (Garbage Collector)
        // Criamos um novo objeto
        Produto p2 = new Produto("Monitor", 800.0); 
        
        // Perda de Referência: O objeto "Monitor" torna-se um "órfão" na Heap
        p2 = null; 
        
        System.out.println("O objeto Monitor ainda existe na Heap, mas está inacessível.");
        // O Garbage Collector agirá quando perceber que não há caminho até ele.
    }

    private static void tentarMudarPrimitivo(double valor) {
        // Uma cópia do valor é criada na Stack deste método 
        valor = valor * 0.5; 
    }

    private static void confirmarMudancaReferencia(Produto p) {
        // Recebemos uma cópia do endereço; alteramos o estado do objeto real na Heap 
        p.atualizaPreco(1200.0); 
    }

    private static void aplicarDescontoEspecial(Produto p) {
        p.atualizaPreco(p.precoFinal() * 0.9); // Altera o objeto original via referência
    }
}