package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Materialtyp_Einheitentyp extends Audit
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Positive
	private int manzahl;
	
	@ManyToOne
	@JoinColumn(name="materialtyp")
	private Materialtyp materialtyp;
	
	@Positive
	private int eanzahl;
	
	@ManyToOne
	@JoinColumn(name="einheitentyp")
	private Einheitentyp einheitentyp;
	
	private String beschreibung;
	
	public Materialtyp_Einheitentyp()
	{
		super();
	}
	
	public Materialtyp_Einheitentyp(Einheitentyp einheitentyp)
	{
		super();
		this.einheitentyp = einheitentyp;
	}
}