package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Veranstaltung_Einheitentyp extends Audit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private Veranstaltung veranstaltung;
	private Einheitentyp einheitentyp;
	
	private int nummer;

	public Veranstaltung_Einheitentyp()
	{
		super();
	}
}
