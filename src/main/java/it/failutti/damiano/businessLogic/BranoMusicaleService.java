package it.failutti.damiano.businessLogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.failutti.damiano.model.BranoMusicale;
import it.failutti.damiano.persistence.ArtistaRepository;
import it.failutti.damiano.persistence.BranoMusicaleRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BranoMusicaleService {
	
	@Autowired
	BranoMusicaleRepository repoB;
	
	@Autowired
	ArtistaRepository repoA;
	
	
	public void popolaBranoMusicale() {
	
	BranoMusicale track1 = new BranoMusicale();
	track1.setArtista(repoA.getById(1l));
	track1.setTitolo ("Zitti e buoni");
	track1.setAnno(2021);
	track1.setBpm(120);
	track1.setGenere("Rock");
	track1.setLingua("Italiano");
	repoB.save(track1);
	
	BranoMusicale track2 = new BranoMusicale();
	track1.setArtista(repoA.getById(1l));
	track1.setTitolo ("I wanna be your slave");
	track1.setAnno(2021);
	track1.setBpm(110);
	track1.setGenere("Rock");
	track1.setLingua("Inglese");
	repoB.save(track2);
	
	
	
	}
	
	public List<BranoMusicale> getAllBraniMusicali() {
		List<BranoMusicale> listaB = repoB.findAll();
		return listaB;
	}
	
	public BranoMusicale creaBranoMusicale(BranoMusicale branoMusicale) {
		return repoB.save(branoMusicale);
			
	}
	
	public void deleteBranoMusicale(Long id) {
		repoB.deleteById(id);
	}
	
	public BranoMusicale modificaBranoMusicale (BranoMusicale branoMusicale) {
		return repoB.save(branoMusicale);
	}
		
	
	
	
	public Optional<BranoMusicale> getById(Long id) {
		return repoB.findById(id);	
		
	}
	
	

}
