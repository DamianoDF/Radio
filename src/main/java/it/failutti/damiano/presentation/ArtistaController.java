package it.failutti.damiano.presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import it.failutti.damiano.businessLogic.ArtistaService;
import it.failutti.damiano.model.Artista;
import it.failutti.damiano.persistence.BranoMusicaleRepository;




@CrossOrigin (origins = "http://www.amazon.it" , maxAge = 5000)
@RestController
@RequestMapping("/api")
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaS;
	
	@Autowired
	private BranoMusicaleRepository  repoB;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping ("/artistaFormat") 
	public Artista artistaFormat() {
		Artista artF = new Artista();
			
		
		artF.setNome("Maneskin");
		return artF;

	}
	@PreAuthorize("hasRole ('ADMIN')")
	@PutMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Artista> artista (@RequestBody Artista artista) {
		Artista artistaModificato = artistaS.modificaArtista(artista);
		return new ResponseEntity<Artista> (artistaModificato, HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/artista")
	public ResponseEntity<List<Artista>> getAllArtisti() {
		List<Artista> listaArtisti = artistaS.getAllArtisti();
		ResponseEntity<List<Artista>> risultato = new ResponseEntity<>(listaArtisti, HttpStatus.OK);
		return risultato;
		
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/artista/{idArtista}")
	public ResponseEntity<Artista> getEdificioById (@PathVariable (required=true) Long idArtista) {
		Optional<Artista> artistaOp = artistaS.getById(idArtista);
		if(artistaOp.isPresent()) {
			return new ResponseEntity<>(artistaOp.get(), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping ("/artista")
	public ResponseEntity<Artista> createArtista(@RequestBody Artista artista) {
	try {
		Artista artistaSalvato = artistaS.creaArtista(artista);
		return new ResponseEntity<Artista>(artistaSalvato, HttpStatus.CREATED);
	} catch (Exception e) {
		throw new WebServerException ("Artista non salvato", e);
		
	}
}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/artista/{idArtista}")
	public ResponseEntity<Artista> destroyArtista(@PathVariable (required=true) Long idArtista) {
		artistaS.deleteArtista(idArtista);
		return new ResponseEntity<Artista>(HttpStatus.OK);
		
	}


}
