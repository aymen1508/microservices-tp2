package com.tickets.ticket_microservice.modules.ticket.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tickets.ticket_microservice.modules.ticket.DTOs.InternauteDTO;

@FeignClient(name = "internaute-microservice")
public interface InternauteClient {
    @GetMapping("/internautes/{id}")
    InternauteDTO getInternauteById(@PathVariable("id") Long id);
}