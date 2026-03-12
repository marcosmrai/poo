package br.com.loja.model;

/**
 * Interface que define o contrato de pagamento.
 * Qualquer classe que assine este contrato pode ser usada no Checkout.
 */
public interface Pagavel {
    // Abstração Pura: Não possui estado, apenas comportamento [cite: 838]
    boolean processar(double valor);
}