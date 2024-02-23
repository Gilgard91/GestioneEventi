package be.epicode.GestioneEventi.services;

import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.exceptions.UnauthorizedException;
import be.epicode.GestioneEventi.payloads.UserDTO;
import be.epicode.GestioneEventi.payloads.UserLoginDTO;
import be.epicode.GestioneEventi.repositories.UsersDAO;
import be.epicode.GestioneEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;


    public User save(UserDTO body){
        User newUser = new User(body.nome(),body.cognome(),body.email(),body.password(),body.ruolo());
        return usersDAO.save(newUser);
    }

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        User user = usersService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }


    }

}
