package federicolepore.u5w18d5_WeeklyProject.services;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
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

    
    // metodo epr controllare numero di occupanti ed in caso incrementare validando la prenotazione


}
