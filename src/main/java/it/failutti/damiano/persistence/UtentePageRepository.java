package it.failutti.damiano.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.failutti.damiano.model.Utente;



public interface UtentePageRepository extends PagingAndSortingRepository<Utente,Long> {
	
	Page<Utente> findByActive (Boolean active, Pageable pageable);
	
	Utente findByUsername (String username);
}
