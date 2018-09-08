package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Mitarbeitertyp_Einheitentyp;

public interface MitEinRepository extends JpaRepository<Mitarbeitertyp_Einheitentyp, Long>
{
	Mitarbeitertyp_Einheitentyp findById(long id);
	
}
