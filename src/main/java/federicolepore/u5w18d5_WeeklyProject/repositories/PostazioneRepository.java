package federicolepore.u5w18d5_WeeklyProject.repositories;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {

    @Query("select p from Postazione p where p.tipoPostazione = :tipo and p.edificio.citta = :citta")
    List<Postazione> findByTipoAndCitta(@Param("tipo") TipoPostazione tipo, @Param("citta") String citta);

    List<Postazione> findByTipoPostazione(TipoPostazione tipoPostazione);

    List<Postazione> findByEdificio_Citta(String citta);
}
