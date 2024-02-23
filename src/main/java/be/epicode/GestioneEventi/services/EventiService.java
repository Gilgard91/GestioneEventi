package be.epicode.GestioneEventi.services;

import be.epicode.GestioneEventi.entities.Evento;
import be.epicode.GestioneEventi.payloads.EventoDTO;
import be.epicode.GestioneEventi.repositories.EventiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventiService {
    @Autowired
    private EventiService eventiService;
    @Autowired
    private EventiDAO eventiDAO;

    public Evento save(EventoDTO body){
        Evento newEvento = new Evento(body.titolo(),body.descrizione(),body.data(),body.luogo(),body.postiDisponibili());
        return eventiDAO.save(newEvento);
    }



}
