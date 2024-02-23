package be.epicode.GestioneEventi.services;

import be.epicode.GestioneEventi.entities.Evento;
import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.exceptions.NotFoundException;
import be.epicode.GestioneEventi.payloads.EventoDTO;
import be.epicode.GestioneEventi.repositories.EventiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventiService {

    @Autowired
    private EventiDAO eventiDAO;
    @Autowired
    private UsersService usersService;

    public Evento findById(int id){
        return eventiDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Evento saveEvento(EventoDTO body){
        Evento newEvento = new Evento(body.titolo(),body.descrizione(),body.data(),body.luogo(),body.postiDisponibili());
        return eventiDAO.save(newEvento);
    }

    public Evento getPrenotazioni(int eventoId, List<Integer> usersIds){
        Evento evento = findById(eventoId);
        List<User> users = usersService.getUsersById(usersIds);
        users.forEach(user -> {
            user.setEvento(evento);
        });
        evento.getUtenti().addAll(users);
        return eventiDAO.save(evento);
    }


}
