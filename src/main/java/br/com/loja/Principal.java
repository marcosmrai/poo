package br.com.loja;

import br.com.loja.model.Cliente;
import br.com.loja.model.Produto;
import br.com.loja.service.Cartao;
import br.com.loja.model.Carrinho;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        int opcao = -1;
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (opcao != 0) {
            System.out.println("\n=== MENU DE EXEMPLOS ===");
            System.out.println("\n=== Aula 1 ===");
            System.out.println("1. Entendendo Objetos");
            System.out.println("2. Memória e Escopo");
            System.out.println("\n=== Aula 2 ===");
            System.out.println("3. Forçando Erros em Máquinas de Estado");
            System.out.println("4. Invariante no Carrinho (Desafio)");
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
                case 3:
                    Aula2.maquinaDeEstados();
                    break;
                case 4:
                    Aula2.invarianteNoCarrinho();
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

class Aula2 {
    // Aula 2: Forçando Erros em Máquinas de Estado e Invariantes
    static void maquinaDeEstados() {
        System.out.println("\n=== TESTES DE FALHA (AULA 2) ===");

        // ERRO 1: Produto com Preço Inválido (Fail-Fast no Construtor)
        try {
            System.out.println("Tentando criar produto com preço negativo...");
            Produto pInvalido = new Produto("Erro", -10.0);
        } catch (IllegalArgumentException e) {
            System.err.println("Capturado: " + e.getMessage());
        }

        // ERRO 2: Carrinho aceitando Produto Nulo
        Carrinho cart = new Carrinho();
        try {
            System.out.println("\nTentando adicionar produto nulo ao carrinho...");
            cart.adicionarItem(null);
        } catch (IllegalArgumentException e) {
            System.err.println("Capturado: " + e.getMessage());
        }

        // ERRO 3: Lógica de Negócio (Invariante Silenciosa)
        System.out.println("\nDemonstrando falha lógica no Cartão (Sem checagem de limite):");
        Cliente joao = new Cliente("João");
        joao.cadastrarCartao(new Cartao(100.0));
        
        // João tenta pagar 500. O sistema deveria impedir, mas a "máquina" está falha.
        joao.getCartao().processar(500.0); 
    }

    // Aula 2: O Carrinho como Máquina de Estado e o Desafio da Abstração
    static void invarianteNoCarrinho() {
        System.out.println("\n=== DESAFIO DO CARRINHO (AULA 2) ===");
        
        Carrinho meuCarrinho = new Carrinho();
        Produto p1 = new Produto("Teclado Mecânico", 300.0);
        Produto p2 = new Produto("Mouse Gamer", 150.0);

        // 1. ADIÇÃO E MANUTENÇÃO DA INVARIANTE [cite: 765]
        meuCarrinho.adicionarItem(p1);
        meuCarrinho.adicionarItem(p2);
        System.out.println("Total após adições: " + meuCarrinho.getTotal());
        
        // Provocação 1: O problema do acoplamento rígido (ArrayList vs List)
        // O método confereTotal exige ArrayList, mas o getItens() retorna List.
        // Isso forçará um erro de compilação ou exigirá um 'cast' feio.
        ArrayList<Produto> listaParaConferir = new ArrayList<>(meuCarrinho.getItens());
        System.out.println("Invariante está íntegra? " + meuCarrinho.confereTotal(listaParaConferir));

        // 2. A ARMADILHA NA REMOÇÃO (QUEBRA DE INVARIANTE) 
        System.out.println("\n--- Removendo um item ---");
        meuCarrinho.removerItem(p1);
        
        // Provocação 2: O total não mudou! 
        // Como o método removerItem não subtrai o valor, o objeto agora está mentindo.
        System.out.println("Total após remover item: " + meuCarrinho.getTotal()); 
        System.out.println("Invariante continua íntegra? " + 
                           meuCarrinho.confereTotal(new ArrayList<>(meuCarrinho.getItens())));

        // 3. TENTATIVA DE SABOTAGEM (CÓPIA DEFENSIVA)
        try {
            System.out.println("\nTentando 'limpar' a lista de itens por fora do contrato...");
            // Provocação 3: O aluno pode achar que consegue deletar tudo via referência.
            meuCarrinho.getItens().clear(); 
        } catch (UnsupportedOperationException e) {
            System.err.println("BLOQUEADO: A Cópia Defensiva impediu a sabotagem externa!");
            System.err.println("O encapsulamento protegeu o estado interno.");
        }
    }
}