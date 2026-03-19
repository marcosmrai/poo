package br.com.loja.service;
import br.com.loja.model.Pagavel;

public class Pix implements Pagavel {
    @Override
    public boolean processar(double valor) {
        System.out.println("[Pix] Gerando QR Code Copia e Cola para R$ " + valor);
        return true; 
    }
}