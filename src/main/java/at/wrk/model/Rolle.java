package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rolle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bezeichnung;
	
	public Rolle()
	{
	}
	public Rolle(String bezeichnung) 
	{
		this.bezeichnung = bezeichnung;
	}

	public long getId()
	{
		return id;
	}

	public String getBezeichnung()
	{
		return bezeichnung;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}
}