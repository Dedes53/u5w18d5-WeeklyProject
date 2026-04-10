package federicolepore.u5w18d5_WeeklyProject.repositories;

import federicolepore.u5w18d5_WeeklyProject.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, UUID> {
}
