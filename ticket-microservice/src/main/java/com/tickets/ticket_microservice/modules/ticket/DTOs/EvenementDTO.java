package com.tickets.ticket_microservice.modules.ticket.DTOs;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EvenementDTO {
    @Id
    private Long idEvenement;
    private String nomEvenement;
    private Long nbPlacesRestants;
    private LocalDate dateEvenement;
    private List<CategorieDTO> categories;
}