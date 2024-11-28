package com.tickets.ticket_microservice.modules.ticket.DTOs;

import com.tickets.ticket_microservice.modules.ticket.TypeTicket;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class TicketDTO {
    @Id
    private Long idTicket;
    private String codeTicket;
    private Double prixTicket;
    private TypeTicket typeTicket;
    private EvenementDTO evenement;
    private InternauteDTO internaute;
}
