package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.wrk.model.Benutzer;

@Repository
public interface UserRepository extends JpaRepository<Benutzer, Long>
{
	Benutzer findByBenutzername(String benutzername);
}
