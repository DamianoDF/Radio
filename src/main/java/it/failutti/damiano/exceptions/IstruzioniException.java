package it.failutti.damiano.exceptions;

import it.failutti.damiano.model.Utente;

public class IstruzioniException extends RuntimeException {
	
	private static final long serialVersionUID = 1l;
	
	public IstruzioniException (String message) {
		super(message);
	}

	public IstruzioniException(String string, Class<Utente> class1, Exception e) {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
