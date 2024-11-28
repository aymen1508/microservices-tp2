package com.tickets.ticket_microservice.modules.ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.idEvenement = :idEvenement")
    int countByEvenement(Long idEvenement);    

    @Query("SELECT COALESCE(SUM(t.prixTicket), 0) FROM Ticket t WHERE t.idEvenement = :idEvenement AND t.typeTicket = :typeTicket")
    Double sumPrixTicketByEvenementAndTypeTicket(Long idEvenement, TypeTicket typeTicket);

    @Query("SELECT t.idInternaute FROM Ticket t GROUP BY t.idInternaute ORDER BY COUNT(t.idTicket) DESC")
    List<Long> getMostActiveInternaute();
}
