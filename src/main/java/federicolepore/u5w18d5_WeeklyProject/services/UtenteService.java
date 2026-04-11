package federicolepore.u5w18d5_WeeklyProject.services;

import federicolepore.u5w18d5_WeeklyProject.entities.Utente;
import federicolepore.u5w18d5_WeeklyProject.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

}

