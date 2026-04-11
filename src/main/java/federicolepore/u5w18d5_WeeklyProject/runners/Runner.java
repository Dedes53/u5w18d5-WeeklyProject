package federicolepore.u5w18d5_WeeklyProject.runners;

import com.github.javafaker.Faker;
import federicolepore.u5w18d5_WeeklyProject.entities.Edificio;
import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Prenotazione;
import federicolepore.u5w18d5_WeeklyProject.entities.Utente;
import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import federicolepore.u5w18d5_WeeklyProject.services.EdificioService;
import federicolepore.u5w18d5_WeeklyProject.services.PostazioneService;
import federicolepore.u5w18d5_WeeklyProject.services.PrenotazioneService;
import federicolepore.u5w18d5_WeeklyProject.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    // services
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private Faker faker;

    @Autowired
    public Runner() {
        //riempire con i serices
    }

    @Override
    public void run(String... args) throws Exception {

        // CREO LE ISTANZE DI PROVA CHE MI SERVONO: EDIFICI, POSTAZIONI E UTENTI

        List<Edificio> edifici = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Edificio e = new Edificio(
                    faker.company().name(),
                    faker.address().streetAddress(),
                    faker.address().city()
            );
            edifici.add(edificioService.save(e));
            System.out.println(e);
        }


        for (int i = 0; i < edifici.size(); i++) {
            Postazione p1 = new Postazione(
                    "postazione PRIVATA dell'edificio " + edifici.get(i).getNome(),
                    TipoPostazione.PRIVATO,
                    1,
                    edifici.get(i)
            );
            Postazione p2 = new Postazione(
                    "postazione OPEN SPACE dell'edificio " + edifici.get(i).getNome(),
                    TipoPostazione.OPEN_SPACE,
                    10,
                    edifici.get(i)
            );
            Postazione p3 = new Postazione(
                    "postazione SALA RIUNIONI dell'edificio " + edifici.get(i).getNome(),
                    TipoPostazione.SALA_RIUNIONI,
                    25,
                    edifici.get(i)
            );
            postazioneService.save(p1);
            postazioneService.save(p2);
            postazioneService.save(p3);
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
        }

        for (int i = 0; i < 15; i++) {
            Utente u = new Utente(
                    faker.name().username(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            );
            utenteService.save(u);
            System.out.println(u);
        }


        List<Utente> utenti = utenteService.findAll();
        List<Postazione> postazioni = postazioneService.findAll();

        for (int i = 0; i < 15; i++) {
            try {
                Prenotazione p = prenotazioneService.prenota(
                        utenti.get(i),
                        postazioni.get(i),
                        LocalDate.now().plusDays(i)
                );
                log.info("Prenotazione creata " + p);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }


        // test per prenotazioni che non vanno abuon fine
        try {
            //utente stesso giorno
            prenotazioneService.prenota(utenti.get(0), postazioni.get(5), LocalDate.now());
            log.info("Test prova utente stesso giorno fallito");

        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        try {
            //postazione già piena
            prenotazioneService.prenota(utenti.get(5), postazioni.get(0), LocalDate.now());
            log.info("Test prova postazione già piena fallito");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }


        //        liste di tutte le postazioni e prenotazioni
        // con il log non mi prende il toString della entity
        System.out.println("Liste di tutte le prenotazioni e postazioni ");
        postazioneService.findAll().forEach(System.out::println);
        prenotazioneService.findAll().forEach(System.out::println);

        //        prenotazioni per utente
        Utente u = utenti.getFirst();
        System.out.println("Lista prenotazioni di utente " + u.getName() + ' ' + u.getSurname());
        prenotazioneService.findByUtente(u).forEach(System.out::println);

        //        ricerca delle postazioni: per tipo, per città e per entrambi
        System.out.println("Ricerca delle postazioni filtrate per tipo, città ed entrambe");
        postazioneService.findByTipoAndCitta(TipoPostazione.OPEN_SPACE, edifici.getFirst().getCitta()).forEach(System.out::println);
        postazioneService.findByTipo(TipoPostazione.SALA_RIUNIONI).forEach(System.out::println);
        postazioneService.findByCitta(edifici.get(4).getCitta()).forEach(System.out::println);

    }
}
