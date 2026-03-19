package br.com.loja.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Pedido {
    private double valorDaCompra;
    private List<Produto> itens;
    private double desconto;
    private String rastreio;

    public Pedido(List<Produto> itens, double desconto, double valorDaCompra){
        this.itens = itens;
        this.desconto = desconto;
        this.valorDaCompra = valorDaCompra;
        this.rastreio = "sdadads";
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
    
}
