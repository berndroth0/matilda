package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotEmpty
	@Size(min=2, max=20)
	private String name;
	
	@NotEmpty
	@Size(min=4, max=99)
	private String adresse;
	
	private String beschreibung;
	
	@ManyToOne
	@JoinColumn(name="benutzer")
	private Benutzer benutzer;
   
	public Lagerstandort()
	{
		super();
	}
}