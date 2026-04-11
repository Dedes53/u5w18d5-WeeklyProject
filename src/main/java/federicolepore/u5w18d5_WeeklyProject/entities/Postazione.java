package federicolepore.u5w18d5_WeeklyProject.entities;

import federicolepore.u5w18d5_WeeklyProject.enumerators.TipoPostazione;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//Lombok
@Getter
@Setter
@NoArgsConstructor

@Entity
public class Postazione {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String descrizione;
    @Column(name = "tipo_postazione", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;
    @Column(name = "posti_massimi", nullable = false)
    private int numMax;
  

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    public Postazione(String descrizione, TipoPostazione tipoPostazione, int numMax, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        this.numMax = numMax;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", tipoPostazione=" + tipoPostazione +
                ", numMax=" + numMax +
                ", edificio=" + edificio +
                '}';
    }


}
