package at.wrk.model;

import java.util.Date;

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
public class Material extends Audit
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="lagerstandort")
	private Lagerstandort lagerstandort;
	
	@ManyToOne
	@JoinColumn(name="materialtyp")
	private Materialtyp materialtyp;
	
	private String seriennummer;
	private Date einkaufsdatum, letztesudatum, naechsteudatum;
	private boolean einsatzbereitschaft;
	private int anzahl;
	
	public Material()
	{
		super();
	}
	public boolean isEinsatzbereitschaft()
	{
		return einsatzbereitschaft;
	}
}