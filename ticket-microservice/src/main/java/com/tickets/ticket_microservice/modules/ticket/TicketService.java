package com.tickets.ticket_microservice.modules.ticket;

import com.tickets.ticket_microservice.modules.ticket.DTOs.InternauteDTO;
import com.tickets.ticket_microservice.modules.ticket.DTOs.EvenementDTO;
import com.tickets.ticket_microservice.modules.ticket.DTOs.TicketDTO;
import com.tickets.ticket_microservice.modules.ticket.Clients.InternauteClient;
import com.tickets.ticket_microservice.modules.ticket.Clients.EvenementClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private InternauteClient internauteClient;
    
    @Autowired
    private EvenementClient evenementClient;
    
    public EvenementDTO getEvenement(Long id) {
        return evenementClient.getEvenementById(id);
    }

    public InternauteDTO getInternaute(Long id) {
        return internauteClient.getInternauteById(id);
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setIdTicket(ticket.getIdTicket());
        ticketDTO.setCodeTicket(ticket.getCodeTicket());
        ticketDTO.setPrixTicket(ticket.getPrixTicket());
        ticketDTO.setTypeTicket(ticket.getTypeTicket());
        EvenementDTO fetchedEvenement = getEvenement(ticket.getIdEvenement());
        ticketDTO.setEvenement(fetchedEvenement);
        InternauteDTO fetchedInternaute = getInternaute(ticket.getIdInternaute());
        ticketDTO.setInternaute(fetchedInternaute);
        return ticketDTO;
    }

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TicketDTO> getTicketById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) {
            return Optional.empty();
        }
        return ticket.map(this::convertToDTO);
    }

    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(Ticket ticket, int nbTickets) {
        EvenementDTO evenement = getEvenement(ticket.getIdEvenement());
        if (evenement.getNbPlacesRestants() < nbTickets) {
            throw new UnsupportedOperationException("nombre de places demandées indisponible");
        }
        evenement.setNbPlacesRestants(evenement.getNbPlacesRestants() - nbTickets);
        evenementClient.updateEvenement(evenement.getIdEvenement(), evenement);

        int NumberOfTicketsFromInternaute = ticketRepository.countByEvenement(ticket.getIdEvenement());
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < nbTickets; i++) {
            Ticket newTicket = new Ticket();
            newTicket.setCodeTicket(String.valueOf(ticket.getIdEvenement())+"_"
            +String.valueOf(NumberOfTicketsFromInternaute+i+1));
            newTicket.setPrixTicket(ticket.getPrixTicket());
            newTicket.setTypeTicket(ticket.getTypeTicket());
            newTicket.setIdInternaute(ticket.getIdInternaute());
            newTicket.setIdEvenement(ticket.getIdEvenement());
            tickets.add(ticketRepository.save(newTicket));
        }
        return tickets;
    }

    public Optional<Ticket> updateTicket(Long id, Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            Ticket ticketToUpdate = ticketOptional.get();
            ticketToUpdate.setCodeTicket(ticket.getCodeTicket());
            ticketToUpdate.setPrixTicket(ticket.getPrixTicket());
            ticketToUpdate.setTypeTicket(ticket.getTypeTicket());
            ticketToUpdate.setIdInternaute(ticket.getIdInternaute());
            ticketToUpdate.setIdEvenement(ticket.getIdEvenement());
            ticketRepository.save(ticketToUpdate);
        }
        return ticketOptional;
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public double getTotalPrixTickets(Long idEvenement, TypeTicket typeTicket) {
        return ticketRepository.sumPrixTicketByEvenementAndTypeTicket(idEvenement, typeTicket);
    }

    public InternauteDTO getMostActiveInternaute() {
        return internauteClient.getInternauteById(ticketRepository.getMostActiveInternaute().get(0));
    }
}