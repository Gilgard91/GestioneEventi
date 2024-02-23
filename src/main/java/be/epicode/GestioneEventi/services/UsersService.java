package be.epicode.GestioneEventi.services;

import be.epicode.GestioneEventi.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;


}
