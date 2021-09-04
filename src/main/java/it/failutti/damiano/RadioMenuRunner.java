package it.failutti.damiano;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import it.failutti.damiano.businessLogic.ArtistaService;
import it.failutti.damiano.businessLogic.BranoMusicaleService;
import it.failutti.damiano.businessLogic.RuoloService;
import it.failutti.damiano.businessLogic.UtenteService;
import it.failutti.damiano.model.Utente;



public class RadioMenuRunner implements CommandLineRunner {

	@Autowired
	private BranoMusicaleService bs;

	@Autowired 
	private ArtistaService as;

	@Autowired
	private UtenteService us;


	@Autowired
	private RuoloService rs;



	@Override
	public void run(String... args) throws Exception {

		bs.popolaBranoMusicale();
		as.popolaArtista();

		
		rs.popolaRuolo();
		us.popolaUtente();


		Optional<Utente> utente1 = us.getById(1l);



	}

}