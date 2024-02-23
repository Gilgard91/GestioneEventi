package be.epicode.GestioneEventi.repositories;

import be.epicode.GestioneEventi.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventiDAO extends JpaRepository<Evento, Integer> {
}
