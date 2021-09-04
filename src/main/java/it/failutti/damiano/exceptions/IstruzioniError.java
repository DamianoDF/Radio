package it.failutti.damiano.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;


@Data
public class IstruzioniError {
	
	private String messaggio = "Questa funzione non e' abilitata";
	private final String developerReference = "Epicode";
	private HttpStatus stato;

}
