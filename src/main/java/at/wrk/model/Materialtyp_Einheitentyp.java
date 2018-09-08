package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Materialtyp_Einheitentyp extends Audit
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int manzahl;
	
	@ManyToOne
	@JoinColumn(name="materialtyp")
	private Materialtyp materialtyp;
	
	private int eanzahl;
	
	@ManyToOne
	@JoinColumn(name="einheitentyp")
	private Einheitentyp einheitentyp;
	
	private String beschreibung;
	
	public Materialtyp_Einheitentyp()
	{
		super();
	}
}