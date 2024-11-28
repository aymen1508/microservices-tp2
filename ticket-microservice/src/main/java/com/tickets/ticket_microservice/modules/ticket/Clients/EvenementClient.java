package com.tickets.ticket_microservice.modules.ticket.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.tickets.ticket_microservice.modules.ticket.DTOs.EvenementDTO;

@FeignClient(name = "evenement-microservice")
public interface EvenementClient {
    @GetMapping("/evenements/{id}")
    EvenementDTO getEvenementById(@PathVariable("id") Long id);

    @PutMapping("/evenements/{id}")
    EvenementDTO updateEvenement(@PathVariable("id") Long id, EvenementDTO evenementDTO);
}
