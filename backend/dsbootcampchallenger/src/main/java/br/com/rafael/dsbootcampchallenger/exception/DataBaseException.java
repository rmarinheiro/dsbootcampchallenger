package br.com.rafael.dsbootcampchallenger.exception;

/**
 * Classe Responsável por tratar as exceções de exclusão de registros.
 * @author RAFAEL
 *
 */
public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataBaseException(String msg) {
		super(msg);
	}
}
