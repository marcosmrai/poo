package br.com.loja.model;

import java.util.Collections;
import java.util.List;

import br.com.loja.service.Boleto;
import br.com.loja.service.Cartao;
import br.com.loja.service.Pix;

public class Pedido {
    final private double valorDaCompra;
    final private List<Produto> itens;
    final private double desconto;
    private String rastreio;
    
    // Atributos injetados que inflam as responsabilidades da classe
    final private Pagavel metodoPagamento;
    private boolean pagamentoAprovado;
    final private String emailCliente;

    public Pedido(List<Produto> itens, double desconto, double valorDaCompra, Pagavel metodoPagamento, String emailCliente){
        this.itens = itens;
        this.desconto = desconto;
        this.valorDaCompra = valorDaCompra;
        
        this.metodoPagamento = metodoPagamento;
        this.emailCliente = emailCliente;
        this.pagamentoAprovado = false;
        this.rastreio = null; // Inicia nulo, só gera se aprovado
    }

    // A MISTURA DE RESPONSABILIDADES: O malabarismo (Viola SRP e OCP)
    public void verificarPagamento() {
        System.out.println("\n--- Iniciando verificação de pagamento do Pedido ---");

        // O Pedido precisa saber os detalhes de CADA tipo de pagamento.
        if (this.metodoPagamento instanceof Cartao) {
            System.out.println("Conectando com a adquirente do Cartão de Crédito...");
        } else if (this.metodoPagamento instanceof Boleto) {
            System.out.println("Aguardando compensação bancária do Boleto (Pode levar até 3 dias úteis)...");
        } else if (this.metodoPagamento instanceof Pix) {
            System.out.println("Consultando API do Banco Central para confirmar recebimento do Pix...");
        }

        // Tenta processar efetivamente
        this.pagamentoAprovado = this.metodoPagamento.processar(this.valorDaCompra);

        if (this.pagamentoAprovado) {
            this.rastreio = "BR" + (long)(Math.random() * 100000000L) + "BR";
            enviarEmailConfirmacao();
        } else {
            System.out.println("Falha ou pendência na aprovação do pagamento.");
        }
    }

    // RESPONSABILIDADE EXTERNA: Comunicação
    private void enviarEmailConfirmacao() {
        System.out.println("\n[Servidor SMTP] Conectando à porta 587...");
        System.out.println("Enviando e-mail para: " + this.emailCliente);
        System.out.println("Assunto: Pagamento Aprovado!");
        System.out.println("Corpo: Seu pagamento foi confirmado. Código de Rastreio: " + this.rastreio);
        System.out.println("[Servidor SMTP] E-mail enviado com sucesso.\n");
    }

    public List<Produto> consultaItens() {
        return Collections.unmodifiableList(this.itens);
    }

    public double consultaDesconto(){
        return this.desconto;
    }

    public double valorDaCompra(){
        return this.valorDaCompra;
    }
    
    public boolean isPagamentoAprovado() {
        return this.pagamentoAprovado;
    }
}