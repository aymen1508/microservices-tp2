package com.evenement.evenement_microservice.modules.evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    
}
