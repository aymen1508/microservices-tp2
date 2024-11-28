package com.tickets.ticket_microservice.modules.ticket;

import lombok.Data;

@Data
public class TicketsWrapper {
    private Ticket ticket=new Ticket();
    private int nbTickets;

    public TicketsWrapper() {
        this.ticket.setCodeTicket("x");
    }
}
