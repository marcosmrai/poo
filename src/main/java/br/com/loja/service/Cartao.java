package br.com.loja.service;
import br.com.loja.model.Pagavel;

public class Cartao implements Pagavel {
    private String numero;
    private double limite;
    private double limiteGasto;

    public Cartao(double limite) {
        this.limite = limite;
        this.limiteGasto = 0;
        this.numero = String.valueOf((long)(Math.random() * 10000000000000000L));
    }

    @Override
    public boolean processar(double valor) {
        if (this.limiteGasto + valor > this.limite){
            return false;
        }
        System.out.println("Limite restante: R$ " + (this.limite - valor));
        this.limiteGasto += valor;
        return true;
    }

    public String getNumero() {
        return this.numero;
    }
}