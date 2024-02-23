package be.epicode.GestioneEventi.services;

import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.exceptions.NotFoundException;
import be.epicode.GestioneEventi.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;

    public User findById(int id) {
        return usersDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email){
        return usersDAO.findByEmail(email).orElseThrow( () -> new NotFoundException("Email " + email + " non trovata"));
    }

    public List<User> getUsers(){
        return usersDAO.findAll();
    }

    public List<User> getUsersById(List<Integer> usersIds){
        return usersDAO.findAllById(usersIds);
    }


}
