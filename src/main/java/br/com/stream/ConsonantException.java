package br.com.stream;

public class ConsonantException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConsonantException(){
		super("Consoante não encontrada na String");
	}
}
