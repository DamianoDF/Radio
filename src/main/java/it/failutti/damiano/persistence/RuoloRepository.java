package it.failutti.damiano.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.failutti.damiano.model.Ruolo;
import it.failutti.damiano.model.TipoDiRuolo;



public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
	
	public List<Ruolo> findByTipoDiRuolo(TipoDiRuolo tipoDiRuolo);
	
	

}
