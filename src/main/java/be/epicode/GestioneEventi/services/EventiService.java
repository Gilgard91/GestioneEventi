package be.epicode.GestioneEventi.services;

import be.epicode.GestioneEventi.entities.Evento;
import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.exceptions.BadRequestException;
import be.epicode.GestioneEventi.exceptions.NotFoundException;
import be.epicode.GestioneEventi.payloads.EventoDTO;
import be.epicode.GestioneEventi.repositories.EventiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventiService {

    @Autowired
    private EventiDAO eventiDAO;
    @Autowired
    private UsersService usersService;

    public Evento findById(int id) {
        return eventiDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Evento saveEvento(EventoDTO body) {
        Evento newEvento = new Evento(body.titolo(), body.descrizione(), body.data(), body.luogo(), body.postiDisponibili());
        return eventiDAO.save(newEvento);
    }

    public List<Evento> getEventi() {
        return eventiDAO.findAll();
    }

    public void findByIdAndDelete(int id){
        Evento found = this.findById(id);
        eventiDAO.delete(found);
    }

    public Evento findByIdAndUpdate(int id, EventoDTO body){
        Evento found = this.findById(id);
        found.setTitolo(body.titolo());
        found.setDescrizione(body.descrizione());
        found.setData(body.data());
        found.setLuogo(body.luogo());
        found.setPostiDisponibili(body.postiDisponibili());
        return eventiDAO.save(found);
    }

    @Transactional
    public void addPrenotazione(int eventoId, User user){
        Evento evento = findById(eventoId);
        long postiDisponibili = evento.getPostiDisponibili();
        
        if (postiDisponibili <= 0) {
            throw new IllegalStateException("Non ci sono abbastanza posti disponibili per prenotare questo evento");
        }

        if (evento.getUtenti().contains(user)) {
            throw new BadRequestException("L'utente è già prenotato per questo evento");
        }

            evento.setPostiDisponibili(postiDisponibili - 1);
            evento.getUtenti().add(user);
            user.getEventi().add(evento);
            eventiDAO.save(evento);

    }


//    public Evento setPrenotazioni(int eventoId, List<Integer> usersIds) {
//        Evento evento = findById(eventoId);
//        long postiDisponibili = evento.getPostiDisponibili();
//        List<User> users = usersService.getUsersById(usersIds);
//
//        if (users.size() > postiDisponibili) {
//            throw new IllegalStateException("Non ci sono abbastanza posti disponibili per tutti gli utenti richiesti");
//        }
//
//        if (postiDisponibili - users.size() < 0) {
//            throw new IllegalStateException("Non ci sono abbastanza posti disponibili per tutti gli utenti richiesti");
//        }
//
//        users.forEach(user -> {
//            user.setEvento(evento);
//        });
//        evento.getUtenti().addAll(users);
//        evento.setPostiDisponibili(postiDisponibili - users.size());
//        return eventiDAO.save(evento);
//    }


}
