package it.failutti.damiano.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Ruolo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruolo_generator")
	@SequenceGenerator(name="ruolo_generator", sequenceName = "ruolo_seq")
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoDiRuolo tipoDiRuolo;

}