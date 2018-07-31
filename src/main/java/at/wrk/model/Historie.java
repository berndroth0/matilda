package at.wrk.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Historie {

	@Id
	private int id;
	private String typ;
	private Time uhrzeit;
	private int anzahl;
	private String beschreibung;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public Time getUhrzeit() {
		return uhrzeit;
	}
	public void setUhrzeit(Time uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
}
