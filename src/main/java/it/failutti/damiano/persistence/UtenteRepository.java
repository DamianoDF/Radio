package it.failutti.damiano.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.failutti.damiano.model.Utente;



public interface UtenteRepository extends JpaRepository<Utente ,Long>  {

	List<Utente> findListByUsername(String  username);
	
	Optional<Utente> findByUsername(String  username);
	
	Optional<Utente> findByFirstName(String  firstName);
	
	Optional<Utente> findByLastName(String  lastName);
	
	Optional<Utente> findByeMail (String eMail);

}
