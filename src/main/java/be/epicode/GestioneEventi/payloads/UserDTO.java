package be.epicode.GestioneEventi.payloads;

import be.epicode.GestioneEventi.entities.Ruolo;

public record UserDTO(String nome, String cognome, String email, String password, Ruolo ruolo) {
}
