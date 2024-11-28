package com.internaute.internaute_microservice.modules.internaute;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternauteService {
    
    @Autowired
    private final InternauteRepository internauteRepository;

    public InternauteService(InternauteRepository internauteRepository) {
        this.internauteRepository = internauteRepository;
    }

    public List<Internaute> getAllInternautes() {
        return internauteRepository.findAll();
    }

    public Optional<Internaute> getInternauteById(Long id) {
        return internauteRepository.findById(id);
    }

    public Internaute createInternaute(Internaute internaute) {
        return internauteRepository.save(internaute);
    }

    public void deleteInternaute(Long id) {
        internauteRepository.deleteById(id);
    }

    public Optional<Internaute> updateInternaute(Long id, Internaute internauteDetails) {
        Optional<Internaute> internaute = internauteRepository.findById(id);
        if (internaute.isPresent()){
            Internaute existingInternaute = internaute.get();
            existingInternaute.setIdentifiant(internauteDetails.getIdentifiant());
            existingInternaute.setTrancheAge(internauteDetails.getTrancheAge());
            return Optional.of(internauteRepository.save(existingInternaute));
        }
        return null;
    }
    
}