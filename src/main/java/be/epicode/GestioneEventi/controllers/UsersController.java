package be.epicode.GestioneEventi.controllers;

import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public List<User> getAllUsers(){
        return this.usersService.getUsers();
    }


}
