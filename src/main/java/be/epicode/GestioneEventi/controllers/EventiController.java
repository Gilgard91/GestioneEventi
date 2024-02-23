package be.epicode.GestioneEventi.controllers;

import be.epicode.GestioneEventi.entities.Evento;
import be.epicode.GestioneEventi.payloads.EventoDTO;
import be.epicode.GestioneEventi.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventiController {

    @Autowired
    private EventiService eventiService;

    @GetMapping("")
    public List<Evento> getAllEventi(){
        return this.eventiService.getEventi();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento saveEvento(@RequestBody EventoDTO body){
        return this.eventiService.saveEvento(body);
    }

    @PutMapping("/{eventoId}/users")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Evento assegnaPrenotazioni(@PathVariable int eventoId, @RequestBody List<Integer> users){
        return eventiService.setPrenotazioni(eventoId,users);
    }

}
