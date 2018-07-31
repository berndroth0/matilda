package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="einheitentyp_mitarbeitertyp")
public class EinheitentypMitarbeitertyp
{
	@ManyToOne
	@JoinColumn(name="einheitentyp",referencedColumnName="id")
	private int einheitentyp;
	@ManyToOne
	@JoinColumn(name="mitarbeitertyp",referencedColumnName="kuerzel")
	private String mitarbeitertyp;
	
	public EinheitentypMitarbeitertyp(int einheitentyp, String mitarbeitertyp)
	{
		super();
		this.einheitentyp = einheitentyp;
		this.mitarbeitertyp = mitarbeitertyp;
	}
	public int getEinheitentyp()
	{
		return einheitentyp;
	}
	public String getMitarbeitertyp()
	{
		return mitarbeitertyp;
	}
	public void setEinheitentyp(int einheitentyp)
	{
		this.einheitentyp = einheitentyp;
	}
	public void setMitarbeitertyp(String mitarbeitertyp)
	{
		this.mitarbeitertyp = mitarbeitertyp;
	}
	
	
}
