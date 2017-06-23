//package com.example.yan.bancosuperseguro.model;
package com.example.yan.bss.model;

//import com.example.yan.bancosuperseguro.exceptions.ContaInvalidaException;
//import com.example.yan.bancosuperseguro.exceptions.SaldoInsuficienteException;
//import com.example.aucianofa.bancosuperseguroreserval.exceptions.ContaInvalidaException;

//import com.example.laucianofa.bancosuperseguroreserva.exceptions.SaldoInsuficienteException;

import com.example.yan.bss.exceptions.ContaInvalidaException;
import com.example.yan.bss.exceptions.SaldoInsuficienteException;

import java.util.ArrayList;

//import com.example.yan.bss.exceptions.ContaInvalidaException;

/**
 * Created by aluno on 18/02/2017.
 */

public class Repositorio {

    /***** Atributos *****/

    public static ArrayList<Cliente> clientes;
    public static ArrayList<Conta> contas;

    /***** Construtores *****/

    public Repositorio () {
        if(clientes == null) {
            clientes = new ArrayList<Cliente>();
        }
        if(contas == null) {
            contas = new ArrayList<Conta>();
        }
    }

    /***** Métodos *****/

    public Cliente checaCliente(String cpf){
        for(Cliente c : clientes){
            if(c.getCpf().equals(cpf)){
                return c;
            }
        }
        return null;
    }

    public Conta checaConta(int numConta){
        for(Conta c : contas){
            if(c.getNumConta() == numConta){
                return c;
            }
        }
        return null;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(Conta c) {
        contas.remove(c);
    }

    public void adicionaCliente(Cliente c) {
        clientes.add(c);
    }

    public double checarSaldo(Conta c) {
        return c.getValor();
    }

    public void sacar(Conta c, double value) throws SaldoInsuficienteException {
                if (c.getValor() >= value) {
                    c.sacarValor(value);
                    c.addTransacao("Saque de "+value+"\nTotal da conta: "+c.getValor()+ "\n");
                } else {
                    throw new SaldoInsuficienteException();
                }
            }


    public void depositar (Conta c, double value) {
                c.depositarValor(value);
                c.addTransacao("Deposito de "+value+"\nTotal da conta: "+c.getValor()+"\n");
    }

       public void tranferir(Conta contaOrigem, int numContaDestino, double value) throws SaldoInsuficienteException, ContaInvalidaException {
        Conta contaDestino = checaConta(numContaDestino);


        if (contaDestino != null) {
            if (contaOrigem.getValor() >= value) {
                contaOrigem.sacarValor(value);
                contaOrigem.addTransacao("Transferência efetuada de " + value + "\nPara a conta " + contaDestino.getNumConta() + "\nTotal da conta: " + contaOrigem.getValor() + "\n");
                contaDestino.depositarValor(value);
                contaDestino.addTransacao("Transferência recebida de " + value + "\nDa conta " + contaOrigem.getNumConta() + "\nTotal da conta: " + contaDestino.getValor() + "\n");
            } else {
                throw new SaldoInsuficienteException();
            }

        } else {
                throw new ContaInvalidaException();       }

    }



    /***** Getters e Setters *****/

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }
}
