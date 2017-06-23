//package com.example.yan.bancosuperseguro.model;
package com.example.yan.bss.model;

//import com.example.yan.bancosuperseguro.exceptions.SaldoInsuficienteException;
//import com.example.laucianofa.bancosuperseguroreserva.exceptions.SaldoInsuficienteException;

import com.example.yan.bss.exceptions.SaldoInsuficienteException;

import java.util.ArrayList;


/**
 * Created by aluno on 18/02/2017.
 */

public class Conta {

    /***** Atributos *****/

    public int numConta;
    public Cliente cliente;
    public double valor;
    public ArrayList<String> transacoes;

    /***** Construtores *****/

    public Conta (Cliente cliente, int numConta, double valor){
        this.numConta = numConta;
        this.cliente = cliente;
        this.valor = valor;
        this.transacoes = new ArrayList<String>();
    }

    /***** Métodos *****/

    public void addTransacao(String transacao){
        transacoes.add(transacao);
    }

    public void depositarValor(double valor){
        this.valor += valor;
    }

    public void sacarValor(double valor) throws SaldoInsuficienteException {
        if(this.valor >= valor){
            this.valor -= valor;
        } else throw new SaldoInsuficienteException();
    }

    public String checaExtrato(int quantidade){
        String extrato = "Extrato\n\n";
        int tamanho = this.transacoes.size();
        for(int i = Math.max(0, tamanho - quantidade); i < tamanho; i++){
            extrato += transacoes.get(i) + "\n";
        }
        extrato += "Salto atual: " + this.valor;
        return extrato;
    }

    /***** Getters e Setters *****/

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ArrayList<String> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(ArrayList<String> transacoes) {
        this.transacoes = transacoes;
    }
}
