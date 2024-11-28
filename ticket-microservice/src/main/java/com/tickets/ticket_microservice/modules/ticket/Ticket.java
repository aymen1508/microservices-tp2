package com.tickets.ticket_microservice.modules.ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    private String codeTicket;
    private Double prixTicket;
    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;
    private Long idInternaute;
    private Long idEvenement;
}
