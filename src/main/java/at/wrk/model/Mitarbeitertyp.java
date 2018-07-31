package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mitarbeitertyp")
public class Mitarbeitertyp
{
	@Id
	private String kuerzel;
	private String name;
	
	public Mitarbeitertyp(String kuerzel, String name)
	{
		super();
		this.kuerzel = kuerzel;
		this.name = name;
	}
	public String getKuerzel()
	{
		return kuerzel;
	}
	public String getName()
	{
		return name;
	}
	public void setKuerzel(String kuerzel)
	{
		this.kuerzel = kuerzel;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
