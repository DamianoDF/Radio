package it.failutti.damiano.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table 
public class Utente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String username;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String nomeCompleto;
	private Boolean active;
	private String eMail;
	//@Convert(converter = StringAttributeConverter.class)
	private String password;
	
	@ManyToMany
	@JoinTable(name = "utente_ruoli",joinColumns = @JoinColumn(name = "utente_id"),inverseJoinColumns
	= @JoinColumn(name = "ruolo_id"))
	private Set<Ruolo> roles = new HashSet<>();	
	

	
	//@OneToMany(mappedBy = "utente")
	//private List<Prenotazione> listaPrenotazioni;


	public Utente get() {

		return null;
	}

}
