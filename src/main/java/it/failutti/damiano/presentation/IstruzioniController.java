package it.failutti.damiano.presentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.failutti.damiano.exceptions.IstruzioniException;




@CrossOrigin (origins = "http://www.amazon.it" , maxAge = 5000)
@RestController
@RequestMapping("/api")
public class IstruzioniController {
	
	
	@Value("${prenotazioni.istruzioniItaliano}")
	private String italiano;
	
	
	@Value("${prenotazioni.istruzioniInglese}")
	private String inglese;
	
	@GetMapping("/istruzioni/{lingua}")
	public String daiIstruzioniPerLingua(@PathVariable String lingua) {
	
	if(lingua.equals("italiano")) {
		return italiano;
	}
		else if (lingua.equals("inglese")) {
			return inglese;
		
		} else {
	
				throw new IstruzioniException ("Lingua " + lingua + " non supportata");	
			
			
		}
		
		
		

	}

}