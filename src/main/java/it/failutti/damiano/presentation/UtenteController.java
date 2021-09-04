package it.failutti.damiano.presentation;

import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.failutti.damiano.businessLogic.UtenteService;
import it.failutti.damiano.exceptions.IstruzioniException;
import it.failutti.damiano.model.Utente;



@CrossOrigin (origins = "http://www.amazon.it" , maxAge = 5000)
@RestController
@RequestMapping("/api")

public class UtenteController {
	
	@Autowired
	private UtenteService utenteS;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping ("/utenteFormat") 
	public Utente utenteFormat() {
		Utente utenteF = new Utente();
		utenteF.setActive(true);
		utenteF.setBirthDate(LocalDate.of(1980, 7, 8));
		utenteF.setEMail("ciccio@ciccio.it");
		utenteF.setFirstName("Ciccio");
		utenteF.setLastName("Di Nonna Papera");
		utenteF.setUsername("viva_le_torte");
		return utenteF;

	}
	@PreAuthorize("hasRole ('ADMIN')")
	@PutMapping(value = "/utente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utente> utente (@RequestBody Utente utente) {
		Utente utenteModificato = utenteS.modificaUtente(utente);
		return new ResponseEntity<Utente> (utenteModificato, HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/utente")
	public ResponseEntity<List<Utente>> getAllUtenti() {
		List<Utente> listaUtenti = utenteS.getAllUtenti();
		ResponseEntity<List<Utente>> risultato = new ResponseEntity<>(listaUtenti, HttpStatus.OK);
		return risultato;
		
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/utente/{idUtente}")
	public ResponseEntity<Utente> getUtenteById (@PathVariable (required=true) Long idUtente) {
		
		Optional<Utente> utenteOp = utenteS.getById(idUtente);
		if(utenteOp.isPresent()) {
			return new ResponseEntity<>(utenteOp.get(), HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
				
		}
		
		
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/utenteUN/{username}")
	public ResponseEntity<Utente> getUtenteByUsername (@PathVariable (required=true) String username) {
		
		Optional<Utente> utenteOp = utenteS.getByUsername(username);
		if(utenteOp.isPresent()) {
			return new ResponseEntity<>(utenteOp.get(), HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
				
		}
		
		
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/utenteFN/{firstName}")
	public ResponseEntity<Utente> getUtenteByFirstName (@PathVariable (required=true) String firstName) {
		
		Optional<Utente> utenteOp = utenteS.getByFirstName(firstName);
		if(utenteOp.isPresent()) {
			return new ResponseEntity<>(utenteOp.get(), HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
				
		}
		
		
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/utenteLN/{lastName}")
	public ResponseEntity<Utente> getUtenteByLastName (@PathVariable (required=true) String lastName) {
		
		Optional<Utente> utenteOp = utenteS.getByLastName(lastName);
		if(utenteOp.isPresent()) {
			return new ResponseEntity<>(utenteOp.get(), HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
				
		}
		
		
		
		
	}
	
//	//@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
//	//@GetMapping("/utente/{eMail}")
//	public ResponseEntity<Utente> getUtenteByEMail (@PathVariable (required=true) String eMail) {
//		
//		Optional<Utente> utenteOp = utenteS.getByEMail(eMail);
//		if(utenteOp.isPresent()) {
//			return new ResponseEntity<>(utenteOp.get(), HttpStatus.OK);
//			
//		} else {
//			
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			
//				
//		}
		
		
		
		
	//}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping ("/utente")
	public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
		try {
			Utente utenteSalvato = utenteS.creaUtente(utente);
			return new ResponseEntity<Utente>(utenteSalvato, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new WebServerException ("Utente non salvato", e);

		}
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@DeleteMapping("/utente/{idUtente}")
	public ResponseEntity<Utente> destroyUtente(@PathVariable (required=true) Long idUtente) {
		utenteS.deleteUtente(idUtente);
		return new ResponseEntity<Utente>(HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/utenteP")
	public ResponseEntity<Page<Utente>> getAllUtentiP (Pageable pageable) {
		try {
			Page<Utente> result = utenteS.getAllUtentiPageable(pageable);
			if (result.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		}catch (Exception e) {
			throw new IstruzioniException ("Errore durante la ricerca dell'utente", Utente.class, e);
		}
	}

	
	
	
}