package com.internaute.internaute_microservice.modules.internaute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InternauteRepository extends JpaRepository<Internaute, Long> {
    
}
