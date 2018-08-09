package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Materialtyp extends Audit
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name, menge, beschreibung , link;
		
	public long getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getMenge()
	{
		return menge;
	}
	public String getBeschreibung()
	{
		return beschreibung;
	}
	public String getLink()
	{
		return link;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setMenge(String menge)
	{
		this.menge = menge;
	}
	public void setBeschreibung(String beschreibung)
	{
		this.beschreibung = beschreibung;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
}
