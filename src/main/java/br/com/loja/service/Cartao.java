package br.com.loja.service;
import br.com.loja.model.Pagavel;

public class Cartao implements Pagavel {
    private String numero;
    private double limite;

    public Cartao(double limite) {
        this.limite = limite;
        this.numero = String.valueOf((long)(Math.random() * 10000000000000000L));
    }

    @Override
    public boolean processar(double valor) {
        System.out.println("Limite restante: R$ " + (this.limite - valor));
        this.limite -= valor;
        return true;
    }

    public String getNumero() {
        return this.numero;
    }
}