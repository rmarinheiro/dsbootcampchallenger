package br.com.rafael.dsbootcampchallenger.exception;

/**
 * Classe Responsável por tratar as exceções de objeto não encontrado
 * @author RAFAEL
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);

	}

}
