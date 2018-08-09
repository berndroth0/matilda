package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Veranstaltung_Einheitentyp extends Audit {

	@Id
	private int nummer;
	private String veranstaltung;
	private String einheitentyp;
	
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public String getVeranstaltung() {
		return veranstaltung;
	}
	public void setVeranstaltung(String veranstaltung) {
		this.veranstaltung = veranstaltung;
	}
	public String getEinheitentyp() {
		return einheitentyp;
	}
	public void setEinheitentyp(String einheitentyp) {
		this.einheitentyp = einheitentyp;
	}
}
