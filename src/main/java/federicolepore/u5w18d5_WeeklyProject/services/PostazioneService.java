package federicolepore.u5w18d5_WeeklyProject.services;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import federicolepore.u5w18d5_WeeklyProject.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    private final PostazioneRepository postazioneRepository;

    @Autowired
    public PostazioneService(PostazioneRepository postazioneRepository) {
        this.postazioneRepository = postazioneRepository;
    }

    public Postazione save(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }

    public List<Postazione> findAll() {
        return postazioneRepository.findAll();
    }

    //implementare query per ricerca solo tipo o solo città, e cercare modo per fare di conseguenza la ricerca con entrambe
    public List<Postazione> findByTipo(TipoPostazione tipo) {
        return postazioneRepository.findByTipoPostazione(tipo);
    }

    public List<Postazione> findByCitta(String citta) {
        return postazioneRepository.findByEdificio_Citta(citta);
    }

    public List<Postazione> findByTipoAndCitta(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndCitta(tipo, citta);
    }


}
