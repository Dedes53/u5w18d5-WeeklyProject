package federicolepore.u5w18d5_WeeklyProject.runners;

import com.github.javafaker.Faker;
import federicolepore.u5w18d5_WeeklyProject.entities.Edificio;
import federicolepore.u5w18d5_WeeklyProject.services.EdificioService;
import federicolepore.u5w18d5_WeeklyProject.services.PostazioneService;
import federicolepore.u5w18d5_WeeklyProject.services.PrenotazioneService;
import federicolepore.u5w18d5_WeeklyProject.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        // creo gli edifici
        for (int i = 0; i < 5; i++) {
            Edificio edificio = new Edificio(
                    faker.company().name(),
                    faker.address().streetAddress(),
                    faker.address().city()
            );
            edificioService.save(edificio);
        }


    }
}
