package federicolepore.u5w18d5_WeeklyProject.repositories;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {
}
