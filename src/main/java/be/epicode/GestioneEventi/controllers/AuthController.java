package be.epicode.GestioneEventi.controllers;

import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.payloads.LoginResponseDTO;
import be.epicode.GestioneEventi.payloads.UserDTO;
import be.epicode.GestioneEventi.payloads.UserLoginDTO;
import be.epicode.GestioneEventi.services.AuthService;
import be.epicode.GestioneEventi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserLoginDTO body){
        return new LoginResponseDTO(authService.authenticateUserAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserDTO body){
        return this.authService.saveUser(body);
    }
}
