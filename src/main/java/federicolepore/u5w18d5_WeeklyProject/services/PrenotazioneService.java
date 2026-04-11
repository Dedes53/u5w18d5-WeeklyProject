package federicolepore.u5w18d5_WeeklyProject.services;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Prenotazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Utente;
import federicolepore.u5w18d5_WeeklyProject.repositories.PrenotazioneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
    }

    public Prenotazione save(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }


    public Prenotazione prenota(Utente u, Postazione p, LocalDate giorno) {

        // query per vedere se l'utente ha già una prenotazione quel giorno
        // if(utente ha già prenotato) return null;
        if (prenotazioneRepository.existsByUtenteAndGiorno(u, giorno)) {
            throw new RuntimeException("L' utente" + u.getName() + ' ' + u.getSurname() + " ha già una prenotazione per il giorno " + giorno);
        }

        // query per vedere se la postazione non supera il numero massimo di utenti
        // if(postazione tutta occupata) return null;
        long numeroPrenotazioni = prenotazioneRepository.countByPostazioneAndGiorno(p, giorno);
        if (numeroPrenotazioni >= p.getNumMax()) {
            throw new RuntimeException("La postazione " + p.getDescrizione() + " è già piena per il giorno " + giorno);
        }

        // se pasa tutti e edue i controlli allora la salvo
        Prenotazione prenotazione = new Prenotazione(p, u, giorno);
        return prenotazioneRepository.save(prenotazione);
    }

}
