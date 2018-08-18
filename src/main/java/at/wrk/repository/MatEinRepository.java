package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Materialtyp_Einheitentyp;

public interface MatEinRepository extends JpaRepository<Materialtyp_Einheitentyp, Long>
{
	Materialtyp_Einheitentyp findById(long id);
}
