package it.failutti.damiano.businessLogic;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.failutti.damiano.exceptions.IstruzioniException;
import it.failutti.damiano.model.Ruolo;
import it.failutti.damiano.model.TipoDiRuolo;
import it.failutti.damiano.model.Utente;
import it.failutti.damiano.persistence.RuoloRepository;
import it.failutti.damiano.persistence.UtenteRepository;
import it.failutti.damiano.security.RegistrazioneUtente;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {

	@Autowired
	private UtenteRepository repoU;

	@Autowired
	private RuoloRepository repoR;

	@Autowired
	private RegistrazioneUtente registrazioneUtente;

	public List<Utente> getAllUtenti() {
		List<Utente> listaU = repoU.findAll();
		return listaU;
	}

	public Optional<Utente> getById (Long id) {
		return repoU.findById(id);

	}

	public Optional<Utente> getByUsername (String username) {
		return repoU.findByUsername(username);

	}

	public Optional<Utente> getByFirstName (String firstName) {
		return repoU.findByFirstName(firstName);

	}

	public Optional<Utente> getByLastName (String lastName) {
		return repoU.findByLastName(lastName);

	}

	public Optional<Utente> getByEMail (String eMail) {
		return repoU.findByeMail(eMail);

	}

	public Utente creaUtente(Utente utente) {
		return repoU.save(utente);

	}

	public void deleteUtente(long id) {
		repoU.deleteById(id);
	}


	public void popolaUtente() {
		
		List<Ruolo> listaRuoloA = repoR.findByTipoDiRuolo(TipoDiRuolo.ROLE_ADMIN);
		Ruolo ra = listaRuoloA.get(0);
		
		List<Ruolo> listaRuoloU = repoR.findByTipoDiRuolo(TipoDiRuolo.ROLE_USER);

		
		Ruolo ru = listaRuoloU.get(0);

		Utente utente1 = new Utente();
		utente1.setNomeCompleto("Gino Paoli");
		utente1.setUsername("sapore_di_sale");
		utente1.setEMail("gino@paoli.it");
		utente1.setActive(true);
		utente1.setBirthDate(LocalDate.of(1934, 7, 1));
		utente1.setFirstName("Gino");
		utente1.setLastName("Paoli");
		utente1.setPassword("ginopaolino");
		utente1.getRoles().add(repoR.getById(1l));
		utente1.getRoles().add(repoR.getById(2l));

		repoU.save(utente1);

		Utente utente2 = new Utente();
		utente2.setNomeCompleto("Pino Daniele");
		utente2.setUsername("scarrafone");
		utente2.setEMail("pino@daniele.it");
		utente2.setActive(true);
		utente2.setBirthDate(LocalDate.of(1955, 7, 1));
		utente2.setFirstName("Pino");
		utente2.setLastName("Daniele");
		utente2.setPassword("pinodanielino");
		utente2.getRoles().add(repoR.getById(2l));
		repoU.save(utente2);


	}

	public Utente modificaUtente (Utente utente) {
		return repoU.save(utente);
	}


	public void recuperaUtentePerUsername() {
		log.info("RECUPERA L'USERNAME DELL'UTENTE DAL NOME");
		String username = null;
		List<Utente> listaU = repoU.findListByUsername(username);
		for (Utente u: listaU) {
			log.info(u.getNomeCompleto());

		}



	}	

	public Page<Utente> getAllUtentiPageable (Pageable pageable) {
		try {
			Page<Utente> result = repoU.findAll(pageable);
			return result;

		} catch (Exception e) {
			throw new IstruzioniException("Errore durante la ricerca dell'utente", Utente.class, e);
		}

	}

	@Autowired
	private PasswordEncoder encoder;
	//public Utente?
	public RegistrazioneUtente registraUtente (RegistrazioneUtente registrazioneUtente) {

		Utente utente = new Utente();
		utente.setId(registrazioneUtente.getId());
		utente.setUsername(registrazioneUtente.getUsername());
		String hashedPassword = encoder.encode(registrazioneUtente.getPlainPassword());
		utente.setPassword(hashedPassword);

		Set<Ruolo> setDiRuoli = new HashSet<Ruolo>();

		for (String s : registrazioneUtente.getNomiRuolo()) {
			TipoDiRuolo tipo = TipoDiRuolo.valueOf(s);
			List<Ruolo> listaRuolo = repoR.findByTipoDiRuolo(tipo);
			Ruolo r = listaRuolo.get(0);
			setDiRuoli.add(r);

		}
		utente.setRoles(setDiRuoli);
		repoU.save(utente);
		registrazioneUtente.setId(utente.getId());

		return registrazioneUtente;


	}		











}





