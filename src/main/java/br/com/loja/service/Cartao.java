package br.com.loja.service;
import br.com.loja.model.Pagavel;

public class Cartao implements Pagavel {
    @Override
    public boolean processar(double valor) {
        System.out.println("Validando limite do cartão para R$ " + valor);
        return true;
    }
}