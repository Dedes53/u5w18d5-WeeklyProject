package federicolepore.u5w18d5_WeeklyProject.entities;

import jakarta.persistence.*;
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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazioneID;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utenteID;

    private LocalDate giorno;

    public Prenotazione(Postazione postazioneID, Utente utenteID, LocalDate giorno) {
        this.postazioneID = postazioneID;
        this.utenteID = utenteID;
        this.giorno = giorno;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", postazioneID=" + postazioneID +
                ", utenteID=" + utenteID +
                ", giorno=" + giorno +
                '}';
    }


}
