package com.tickets.ticket_microservice.modules.ticket.DTOs;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class InternauteDTO {
    @Id
    private Long idInternaute;
    private String identifiant;
    private String trancheAge;
}