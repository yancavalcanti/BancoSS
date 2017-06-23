//package com.example.yan.bancosuperseguro.exceptions;
package com.example.yan.bss.exceptions;

public class ContaInvalidaException extends Exception {
	
	public ContaInvalidaException() {
		super("Invalid Account");
	}
	
	public ContaInvalidaException(int num) {
		super("Invalid Account - "+num);
	}

}
