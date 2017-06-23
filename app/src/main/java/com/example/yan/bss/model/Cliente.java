//package com.example.yan.bancosuperseguro.model;
package com.example.yan.bss.model;

/**
 * Created by aluno on 18/02/2017.
 */

public class Cliente {

    /***** Atributos *****/

    private String cpf;
    private String nome;
    private String email;

    /***** Construtores *****/

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    /***** Métodos *****/

    /****** Getters e Setters *****/

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
