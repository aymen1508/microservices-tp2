package com.tickets.ticket_microservice.modules.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tickets.ticket_microservice.modules.ticket.DTOs.InternauteDTO;
import com.tickets.ticket_microservice.modules.ticket.DTOs.TicketDTO;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Optional<TicketDTO> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);         
    }

    @PostMapping
    public List<Ticket> createTickets(@RequestBody TicketsWrapper ticketsWrapper) {
        Ticket ticket=ticketsWrapper.getTicket();
        int nbTickets=ticketsWrapper.getNbTickets();
        return ticketService.ajouterTicketsEtAffecterAEvenementEtInternaute(ticket,nbTickets);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket).get();
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping("/total/{idEvenement}/{type}")
    public Double montantRecupereParEvtEtTypeTicket(@PathVariable Long idEvenement, @PathVariable String type) {
        TypeTicket typeTicket = TypeTicket.valueOf(type);
        return ticketService.getTotalPrixTickets(idEvenement, typeTicket);
    }

    @GetMapping("/most_active")
    public InternauteDTO internauteLePlusActif() {
        return ticketService.getMostActiveInternaute();
    }
}