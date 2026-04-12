package federicolepore.u5w18d5_WeeklyProject.services;

import com.github.javafaker.Faker;
import federicolepore.u5w18d5_WeeklyProject.entities.Edificio;
import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Prenotazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Utente;
import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import federicolepore.u5w18d5_WeeklyProject.exceptions.PostazionePienaException;
import federicolepore.u5w18d5_WeeklyProject.exceptions.PrenotazioneDuplicataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PrenotazioneServiceTest {

    @Autowired
    Faker faker;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private UtenteService utenteService;


    @Test
    void prenotazioneValidaTest() {
        // dati di test
        Edificio e = edificioService.save(new Edificio("Edificio Test", "via del test 1", "Roma"));
        Postazione p = postazioneService.save(new Postazione("Sala test", TipoPostazione.PRIVATO, 1, e));
        Utente u = utenteService.save(new Utente("Tester", faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));

        Prenotazione prenotazione = prenotazioneService.prenota(u, p, LocalDate.now());
        assertNotNull(prenotazione); //verifica che bla prenotazione venga effettivamente creata
        assertNotNull(prenotazione.getId()); // se trovo l'id vuol dire che è stata salvata sul db
    }

    @Test
    void doppiaPrenotazioneUtenteTest() {
        Edificio e = edificioService.save(new Edificio("Edificio Test 2", "via del test 2", "Milano"));
        Postazione p1 = postazioneService.save(new Postazione("P1", TipoPostazione.OPEN_SPACE, 10, e));
        Postazione p2 = postazioneService.save(new Postazione("P2", TipoPostazione.OPEN_SPACE, 10, e));
        Utente u = utenteService.save(new Utente("Tester 2", faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));

        LocalDate d = LocalDate.now();

        prenotazioneService.prenota(u, p1, d);
        // seconda prenotazione per lo stesso utente nello spetto giorno, ma con postazione diversa
        assertThrows(PrenotazioneDuplicataException.class, () -> {
            prenotazioneService.prenota(u, p2, LocalDate.now());
        });
    }

    @Test
    void postazionePienaTest() {
        Edificio e = edificioService.save(new Edificio("Edificio Test 3", "via del test 3", "Napoli"));
        Postazione p = postazioneService.save(new Postazione("P3", TipoPostazione.PRIVATO, 1, e));
        Utente u1 = utenteService.save(new Utente("tester 4", faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));
        Utente u2 = utenteService.save(new Utente("tester 5", faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));

        LocalDate d = LocalDate.now();

        prenotazioneService.prenota(u1, p, d); //occupa la postazione privata (1 posto)
        assertThrows(PostazionePienaException.class, () -> {
            // prova a prenotare una postazione già piena
            prenotazioneService.prenota(u2, p, d);
        });
    }

    @Test
    void listaPrenotazioniPerUtenteTest() {
        Edificio e = edificioService.save(new Edificio("Edificio TErst  4", "via del test 4", "Torino"));
        Postazione p1 = postazioneService.save(new Postazione("P4", TipoPostazione.OPEN_SPACE, 25, e));
        Postazione p2 = postazioneService.save(new Postazione("P5", TipoPostazione.SALA_RIUNIONI, 10, e));
        Utente u = utenteService.save(new Utente("Tester 6", faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));

        prenotazioneService.prenota(u, p1, LocalDate.now());
        prenotazioneService.prenota(u, p2, LocalDate.now().plusDays(1));

        List<Prenotazione> lista = prenotazioneService.findByUtente(u);
        assertTrue(lista.size() >= 2);
    }


}
