package at.wrk.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Veranstaltung extends Audit
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Size(min=2, max=20)
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate beginn;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate ende;
	
	public Veranstaltung()
	{
		super();
	}
}
