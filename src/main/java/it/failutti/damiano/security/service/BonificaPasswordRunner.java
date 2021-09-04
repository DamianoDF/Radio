package it.failutti.damiano.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.failutti.damiano.businessLogic.UtenteService;
import it.failutti.damiano.model.Utente;
import it.failutti.damiano.persistence.UtenteRepository;



public class BonificaPasswordRunner implements CommandLineRunner {
	
	@Autowired
	private UtenteService utenteS;
	
	@Autowired
	private UtenteRepository repoU;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		
		codificaPassword();
		
	}
	
	public void codificaPassword() {
		List<Utente> lista = utenteS.getAllUtenti();
		for (Utente u : lista) {
			if (! u.getPassword().startsWith("$2")) {
				String passwordCode = encoder.encode(u.getPassword());
				u.setPassword(passwordCode);
				repoU.save(u);
			}
		}
		
		
	}

}
