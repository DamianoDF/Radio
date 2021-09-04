package it.failutti.damiano.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table
public class Artista {
	
	@Id
	@Column(name="id_artista")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	private String nome;

	@OneToMany (mappedBy = "artista")
	private List<BranoMusicale> listaBraniMusicali;
	
	

}
