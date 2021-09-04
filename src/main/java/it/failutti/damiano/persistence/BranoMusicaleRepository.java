package it.failutti.damiano.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.failutti.damiano.model.BranoMusicale;



public interface BranoMusicaleRepository extends JpaRepository< BranoMusicale ,Long> {
	
	public List<BranoMusicale> findByNome(String nome);

}
