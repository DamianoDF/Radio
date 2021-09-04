package it.failutti.damiano.presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
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


import it.failutti.damiano.businessLogic.BranoMusicaleService;
import it.failutti.damiano.model.BranoMusicale;
import it.failutti.damiano.persistence.ArtistaRepository;



@CrossOrigin (origins = "http://www.amazon.it" , maxAge = 5000)
@RestController
@RequestMapping("/api")

public class BranoMusicaleController {
	
	@Autowired
	private BranoMusicaleService branoS;
	
	@Autowired
	private ArtistaRepository  repoA;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping ("/branoMusicaleFormat") 
	public BranoMusicale branoMusicaleFormat() {
		BranoMusicale BranoF = new BranoMusicale();
		BranoF.setAnno(2021);
		BranoF.setArtista(repoA.getById(1l));
		BranoF.setBpm(110);
		BranoF.setGenere("Rock");
		BranoF.setLingua("Inglese");
		BranoF.setTitolo("I wanna be your slave");
		return BranoF;

	}
	@PreAuthorize("hasRole ('ADMIN')")
	@PutMapping("/branoMusicale")
	public ResponseEntity<BranoMusicale> branoMusicale (@RequestBody BranoMusicale branoMusicale) {
		BranoMusicale branoMusicaleModificato = branoS.modificaBranoMusicale(branoMusicale);
		return new ResponseEntity<BranoMusicale> (branoMusicaleModificato, HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/branoMusicale")
	public ResponseEntity<List<BranoMusicale>> getAllBraniMusicali() {
		List<BranoMusicale> listaBrani = branoS.getAllBraniMusicali();
		ResponseEntity<List<BranoMusicale>> risultato = new ResponseEntity<>(listaBrani, HttpStatus.OK);
		return risultato;

	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/branoMusicale/{idBranoMusicale}")
	public ResponseEntity<BranoMusicale> getBranoMusicaleById (@PathVariable (required=true) Long idBranoMusicale) {
		Optional<BranoMusicale> branoMusicaleOp = branoS.getById(idBranoMusicale);
		if(branoMusicaleOp.isPresent()) {
			return new ResponseEntity<>(branoMusicaleOp.get(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}




	}

	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping ("/branoMusicale")
	public ResponseEntity<BranoMusicale> createBranoMusicale(@RequestBody BranoMusicale branoMusicale) {
		try {
			BranoMusicale branoMusicaleSalvato = branoS.creaBranoMusicale(branoMusicale);
			return new ResponseEntity<BranoMusicale>(branoMusicaleSalvato, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new WebServerException ("Brano musicale non salvato", e);

		}
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@DeleteMapping("/branoMusicale/{idBranoMusicale}")
	public ResponseEntity<BranoMusicale> destroyBranoMusicale(@PathVariable (required=true) Long idBranoMusicale) {
		branoS.deleteBranoMusicale(idBranoMusicale);
		return new ResponseEntity<BranoMusicale>(HttpStatus.OK);

	}





}
