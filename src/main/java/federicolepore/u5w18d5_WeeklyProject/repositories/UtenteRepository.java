package federicolepore.u5w18d5_WeeklyProject.repositories;

import federicolepore.u5w18d5_WeeklyProject.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {

    
}
