package it.failutti.damiano.security;

import java.time.LocalDate;

import lombok.Data;
@Data
public class RegistrazioneUtente {
	
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String nomeCompleto;
	private Boolean active;
	private String eMail;
	private String plainPassword;
	private String[] nomiRuolo;

}
