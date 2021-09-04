
package it.failutti.damiano.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;





@Data
@Entity
@Table
public class BranoMusicale {
	
	@Id
	@Column(name="id_song")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private Artista artista;
	
	private String titolo;
	 private String genere;
	 private Integer bpm;
	 private String lingua;
	 private Integer anno;
	 
	 
	
	 
	 
	
	

}
