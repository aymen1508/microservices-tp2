package com.tickets.ticket_microservice.modules.ticket.DTOs;

import java.util.List;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CategorieDTO {
    @Id
    Long idCategorie;
    String nomCategorie;
    String codeCategorie;
    private List<EvenementDTO> evenements;
}
