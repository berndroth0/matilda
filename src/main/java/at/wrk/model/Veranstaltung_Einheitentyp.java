package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Veranstaltung_Einheitentyp extends Audit {

	@Id
	private int nummer;
	private String veranstaltung;
	private String einheitentyp;
}
