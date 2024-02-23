package be.epicode.GestioneEventi.repositories;

import be.epicode.GestioneEventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersDAO extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
