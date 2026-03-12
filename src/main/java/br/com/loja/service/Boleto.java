package br.com.loja.service;
import br.com.loja.model.Pagavel;

public class Boleto implements Pagavel {
    @Override
    public boolean processar(double valor) {
        if (valor < 10.0) {
            System.out.println("Valor mínimo para boleto é R$ 10.0");
            return false;
        }
        System.out.println("Gerando boleto com linha digitável para R$ " + valor);
        return true;
    }
}