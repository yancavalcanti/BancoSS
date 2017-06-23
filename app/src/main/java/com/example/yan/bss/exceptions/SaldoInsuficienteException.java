//package com.example.yan.bancosuperseguro.exceptions;
package com.example.yan.bss.exceptions;

/**
 * Created by aluno on 18/02/2017.
 */

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(){
        super("Saldo Insuficiente!");
    }
}
