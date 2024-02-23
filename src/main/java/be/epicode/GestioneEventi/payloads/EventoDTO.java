package be.epicode.GestioneEventi.payloads;

import be.epicode.GestioneEventi.entities.User;

import java.time.LocalDate;
import java.util.List;

public record EventoDTO(String titolo, String descrizione, LocalDate data,
                        String luogo, long postiDisponibili, List<User> utenti) {
}
