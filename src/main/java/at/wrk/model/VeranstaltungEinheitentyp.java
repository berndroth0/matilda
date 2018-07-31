package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="veranstaltung_einheitentyp")
public class VeranstaltungEinheitentyp
{
	@ManyToOne
	@JoinColumn(name="veranstaltung", referencedColumnName="id")
	private int veranstaltung;
	@ManyToOne
	@JoinColumn(name="einheitentyp", referencedColumnName="id")
	private int einheitentyp;
	@Id
	private int zahl;
	
	public VeranstaltungEinheitentyp(int veranstaltung, int einheitentyp, int zahl)
	{
		super();
		this.veranstaltung = veranstaltung;
		this.einheitentyp = einheitentyp;
		this.zahl = zahl;
	}
	public int getVeranstaltung()
	{
		return veranstaltung;
	}
	public int getEinheitentyp()
	{
		return einheitentyp;
	}
	public int getZahl()
	{
		return zahl;
	}
	public void setVeranstaltung(int veranstaltung)
	{
		this.veranstaltung = veranstaltung;
	}
	public void setEinheitentyp(int einheitentyp)
	{
		this.einheitentyp = einheitentyp;
	}
	public void setZahl(int zahl)
	{
		this.zahl = zahl;
	}
	
	
}
