package com.evenement.evenement_microservice.modules.evenement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Evenement getEvenementById(Long id) {
        Optional<Evenement> evenement = evenementRepository.findById(id);
        if (!evenement.isPresent()) {
            throw new RuntimeException("Evenement not found for id: " + id);
        }
        return evenement.get();
    }

    public Evenement createEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public Evenement updateEvenement(Long id, Evenement evenementDetails) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (evenementOptional.isPresent()) {
            Evenement evenement = evenementOptional.get();
            evenement.setNomEvenement(evenementDetails.getNomEvenement());
            evenement.setDateEvenement(evenementDetails.getDateEvenement());
            evenement.setNbPlacesRestants(evenementDetails.getNbPlacesRestants());
            evenement.setCategories(evenementDetails.getCategories());
            return evenementRepository.save(evenement);
        } else {
            throw new RuntimeException("Evenement not found for id: " + id);
        }
    }

    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }

    public Evenement updateNbPlacesRestants(Long id, Long nbPlaces) {
        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (evenementOptional.isPresent()) {
            Evenement evenement = evenementOptional.get();
            evenement.setNbPlacesRestants(nbPlaces);
            return evenementRepository.save(evenement);
        } else {
            throw new RuntimeException("Evenement not found for id: " + id);
        }
    }
}