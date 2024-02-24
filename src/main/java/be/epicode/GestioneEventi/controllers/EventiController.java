package be.epicode.GestioneEventi.controllers;

import be.epicode.GestioneEventi.entities.Evento;
import be.epicode.GestioneEventi.entities.User;
import be.epicode.GestioneEventi.payloads.EventoDTO;
import be.epicode.GestioneEventi.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/{id}")
    public Evento findById(@PathVariable int id){
        return this.eventiService.findById(id);
    }

    @PutMapping("/{id}/addPrenotazione")
    @PreAuthorize("hasAuthority('UTENTE_NORMALE')")
    public void addPrenotazione(@PathVariable int id, @AuthenticationPrincipal User user){
        this.eventiService.addPrenotazione(id, user);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento saveEvento(@RequestBody EventoDTO body, @AuthenticationPrincipal User user){
        return this.eventiService.saveEvento(body);
    }

    @DeleteMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int eventoId, @AuthenticationPrincipal User user){
        eventiService.findByIdAndDelete(eventoId);
    }

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Evento findAndUpdate(@PathVariable int eventoId, @RequestBody EventoDTO updatedEvento, @AuthenticationPrincipal User user){
        return eventiService.findByIdAndUpdate(eventoId, updatedEvento);
    }


//    @PutMapping("/{eventoId}/users")
//    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
//    public Evento assegnaPrenotazioni(@PathVariable int eventoId, @RequestBody List<Integer> users){
//        return eventiService.setPrenotazioni(eventoId,users);
//    }



}
