package br.com.agenda.exceptions;

public class UserExistingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserExistingException(String msg) {
		super(msg);
	}

}
