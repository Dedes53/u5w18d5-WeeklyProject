package federicolepore.u5w18d5_WeeklyProject.runners;

import com.github.javafaker.Faker;
import federicolepore.u5w18d5_WeeklyProject.entities.Edificio;
import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
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

        // creo gli edifici e li aggiungo in una lista
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

        // adesso ciclando la lista degli edifici per ognuno di questi creo tre postazioni, ua per tipo
        for (int i = 0; i < edifici.size(); i++) {
            Postazione p1 = new Postazione(
                    "Postazione di lavoro privata per lavorare senza disturbi",
                    TipoPostazione.PRIVATO,
                    1,
                    edifici.get(i)
            );
            Postazione p2 = new Postazione(
                    "Postazione di lavoro in ambiente condiviso per condividere idee e ispirazione",
                    TipoPostazione.OPEN_SPACE,
                    10,
                    edifici.get(i)
            );
            Postazione p3 = new Postazione(
                    "Sala riunioni super attrezzata per connetterti con colleghi e clienti da tutto il mondo come se fossero insieme a te ",
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

        for (int i = 0; i < 10; i++) {
            Utente u = new Utente(
                    faker.name().username(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            );
            utenteService.save(u);
            System.out.println(u);
        }

    }
}
