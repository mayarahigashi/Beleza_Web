package br.com.salao.jdbc.exception;

public class PersistenciaException extends Exception {

	private static final long serialVersionUID = -8796457926599751430L;

	public PersistenciaException(String msg, Exception exception) {
		super(msg, exception);
	}
	
	public PersistenciaException(String msg) {
		super(msg);
	}
	
}
