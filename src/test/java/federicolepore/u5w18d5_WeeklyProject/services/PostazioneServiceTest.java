package federicolepore.u5w18d5_WeeklyProject.services;

import federicolepore.u5w18d5_WeeklyProject.entities.Edificio;
import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostazioneServiceTest {
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private EdificioService edificioService;

    @Test
    void trovaPerTipoECittaTest() {

        Edificio e = edificioService.save(new Edificio("Torre A", "Via Test", "Milano"));
        Postazione p1 = postazioneService.save(new Postazione("Open Milano", TipoPostazione.OPEN_SPACE, 10, e));
        Postazione p2 = postazioneService.save(new Postazione("Privato Milano", TipoPostazione.PRIVATO, 1, e));
        Edificio eRoma = edificioService.save(new Edificio("Palazzo B", "Via Roma", "Roma"));
        postazioneService.save(new Postazione("Open Roma", TipoPostazione.OPEN_SPACE, 10, eRoma));


        List<Postazione> openSpaceMIlano = postazioneService.findByTipoAndCitta(TipoPostazione.OPEN_SPACE, "Milano");
        assertTrue(openSpaceMIlano.size() >= 1);
        assertEquals(TipoPostazione.OPEN_SPACE, openSpaceMIlano.get(0).getTipoPostazione());
        assertEquals("Milano", openSpaceMIlano.get(0).getEdificio().getCitta());


        List<Postazione> privatiMilano = postazioneService.findByTipoAndCitta(TipoPostazione.PRIVATO, "Milano");
        assertTrue(privatiMilano.size() >= 1);
        assertEquals(TipoPostazione.PRIVATO, privatiMilano.get(0).getTipoPostazione());


        List<Postazione> openSpaceRoma = postazioneService.findByTipoAndCitta(TipoPostazione.OPEN_SPACE, "Roma");
        assertTrue(openSpaceRoma.size() >= 1);
        assertEquals("Roma", openSpaceRoma.get(0).getEdificio().getCitta());
    }

    @Test
    void listaTutteLePostazioniTest() {
        List<Postazione> tutte = postazioneService.findAll();
        assertNotNull(tutte);
        assertTrue(tutte.size() >= 0);
    }

}
