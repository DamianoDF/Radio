package it.failutti.damiano.businessLogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import it.failutti.damiano.model.Artista;
import it.failutti.damiano.persistence.ArtistaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArtistaService {

	@Autowired
	private ArtistaRepository repoA;



	public void popolaArtista() {

		Artista artista1 = new Artista();
		artista1.setNome("Maneskin");
		repoA.save(artista1);

		Artista artista2 = new Artista();
		artista2.setNome("Michele Zarrillo");

		Artista artista3 = new Artista();
		artista3.setNome("David Guetta");

		Artista artista4 = new Artista();
		artista4.setNome("Nena");


	}
	
	public void recuperaTuttiArtisti() {
		log.info("RECUPERA TUTTI GLI ARTISTI");
		List<Artista> listaA = repoA.findAll();
		for (Artista a : listaA) {
			log.info(a.toString());
		}
		
	}
	
	public List<Artista> getAllArtisti() {
		List<Artista> listaA = repoA.findAll();
		return listaA;
	}
	
	public Page<Artista> getAllArtistiP (Pageable pageable) {
		
			Page<Artista> result = repoA.findAll(pageable);
			return result;
			
		
	}
	
	public Optional<Artista> getById (Long id) {
		return repoA.findById(id);
	
		
	}
	
	public Artista creaArtista(Artista artista) {
		return repoA.save(artista);
			
	}
	
	public Artista modificaArtista (Artista artista) {
		return repoA.save(artista);
	}
	
	public void deleteArtista(long id) {
		repoA.deleteById(id);
	}

}
