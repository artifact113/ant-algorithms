package br.dcc.ufrj.antvrp.exception;

public class IllegalArgumentWorldException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8841218780522016251L;

	public IllegalArgumentWorldException(String value) {
		super("Par�metro ".concat(value).concat(" n�o encontrado ou incorreto."));
	}
	

}
