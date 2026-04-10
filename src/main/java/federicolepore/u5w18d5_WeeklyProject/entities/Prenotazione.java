package federicolepore.u5w18d5_WeeklyProject.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

//Lombok
@Getter
@Setter
@NoArgsConstructor

@Entity
public class Prenotazione {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;
    @Column(nullable = false)
    private LocalDate giorno;

    public Prenotazione(Postazione postazione, Utente utente, LocalDate giorno) {
        this.postazione = postazione;
        this.utente = utente;
        this.giorno = giorno;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", postazione=" + postazione +
                ", utenteID=" + utente +
                ", giorno=" + giorno +
                '}';
    }


}
