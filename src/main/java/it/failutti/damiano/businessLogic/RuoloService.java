package it.failutti.damiano.businessLogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.failutti.damiano.model.Ruolo;
import it.failutti.damiano.model.TipoDiRuolo;
import it.failutti.damiano.persistence.RuoloRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RuoloService {
	
	@Autowired
	private RuoloRepository repoR;
	
	public List<Ruolo> getAllRuoli() {
		List<Ruolo> listaR = repoR.findAll();
		return listaR;
	}
	
	public Optional<Ruolo> getById (Long id) {
		return repoR.findById(id);
		
	}
	
	public Ruolo creaRuolo(Ruolo ruolo) {
		return repoR.save(ruolo);
			
	}
	
	public void deleteRuolo(long id) {
		repoR.deleteById(id);
	}
	
	public void popolaRuolo() {
	
	Ruolo ruoloAdmin = new Ruolo();
	ruoloAdmin.setTipoDiRuolo(TipoDiRuolo.ROLE_ADMIN);
	repoR.save(ruoloAdmin);
	
	Ruolo ruoloUser = new Ruolo();
	ruoloUser.setTipoDiRuolo(TipoDiRuolo.ROLE_USER);
	repoR.save(ruoloUser);
	
	
	}
		

}
