package federicolepore.u5w18d5_WeeklyProject.repositories;

import federicolepore.u5w18d5_WeeklyProject.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
}
