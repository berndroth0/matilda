package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "kuerzel"))
public class Mitarbeitertyp extends Audit
{
	@Id
	private long id;
	private String kuerzel;
	private String name;
	
	public Mitarbeitertyp()
	{
		super();
	}	
}