package be.epicode.GestioneEventi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    @Column(name = "posti_disponibili")
    private long postiDisponibili;

    @OneToMany(mappedBy = "evento")
    List<User> utenti;

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, long postiDisponibili) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.postiDisponibili = postiDisponibili;
    }
}
