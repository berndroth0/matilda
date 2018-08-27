package at.wrk.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "benutzername"))
public class Benutzer extends Audit
{

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="benutzer_idbenutzer_seq")
    	@SequenceGenerator(name="benutzer_idbenutzer_seq",
    	sequenceName="benutzer_idbenutzer_seq",
    	allocationSize=1)
	private Long id;
	
	private String benutzername;
	private String passwort;
	private String anzeigename;
	private String dienstnummer;	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "benutzer_rolle",
            joinColumns = @JoinColumn(
                    name = "benutzer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rolle_id", referencedColumnName = "id"))
    private Collection<Rolle> rollen;

	public Benutzer(String benutzername, String passwort, String anzeigename, String dienstnummer,
			Collection<Rolle> rollen)
	{
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.anzeigename = anzeigename;
		this.dienstnummer = dienstnummer;
		
		this.rollen = rollen;
	}
}
