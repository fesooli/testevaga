package br.com.stream;

public class VowelException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VowelException(){
		super("Vogal não encontrada na String");
	}
}
