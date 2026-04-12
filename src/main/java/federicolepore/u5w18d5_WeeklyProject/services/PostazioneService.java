package federicolepore.u5w18d5_WeeklyProject.services;

import federicolepore.u5w18d5_WeeklyProject.entities.Postazione;
import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import federicolepore.u5w18d5_WeeklyProject.exceptions.PostazioneNonTrovata;
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


    public List<Postazione> findByTipo(TipoPostazione tipo) {
        List<Postazione> res = postazioneRepository.findByTipoPostazione(tipo);
        if (res.isEmpty())
            throw new PostazioneNonTrovata("Non è stato possibile trovare nessuna postazione corrispondente");
        return res;
    }

    public List<Postazione> findByCitta(String citta) {
        List<Postazione> res = postazioneRepository.findByEdificio_Citta(citta);
        if (res.isEmpty()) throw new PostazioneNonTrovata("Non ci sono postazioni in città ");
        return res;
    }

    public List<Postazione> findByTipoAndCitta(TipoPostazione tipo, String citta) {
        List<Postazione> res = postazioneRepository.findByTipoAndCitta(tipo, citta);
        if (res.isEmpty()) throw new PostazioneNonTrovata("Non ci sono postazioni corrispoondendi in citta");
        return res;

    }


}
