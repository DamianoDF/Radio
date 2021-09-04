package it.failutti.damiano.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.failutti.damiano.model.Artista;



public interface ArtistaRepository extends JpaRepository< Artista ,Long> {
	
	public List<Artista> findByNome(String nome);

}
