package federicolepore.u5w18d5_WeeklyProject.repositories;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Prenotazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    // controllo quante prenotazioni ha una postazione in un daato gioron (serve per verificare che non superi il numero massimo
    long countByPostazioneAndGiorno(Postazione postazione, LocalDate giorno);

    // devo verificare che un uttente non abbia già una prenotazione per un dato giorno (limite 1/giorno)
    boolean existsByUtenteAndGiorno(Utente utente, LocalDate giorno);


}
