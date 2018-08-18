package at.wrk.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Lagerstandort extends Audit
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name, adresse, beschreibung;
	
	@ManyToOne
	@JoinColumn(name="benutzer")
	private Benutzer benutzer;
   
	
	public Lagerstandort()
	{
		super();
	}
}