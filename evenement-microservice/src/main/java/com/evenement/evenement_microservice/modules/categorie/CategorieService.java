package com.evenement.evenement_microservice.modules.categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.evenement.evenement_microservice.modules.evenement.Evenement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(Long id) {
        Optional<Categorie> Categorie = categorieRepository.findById(id);
        if (!Categorie.isPresent()) {
            throw new RuntimeException("Categorie not found for id: " + id);
        }
        return Categorie.get();
    }

    public Categorie createCategorie(Categorie Categorie) {
        return categorieRepository.save(Categorie);
    }

    public Categorie updateCategorie(Long id, Categorie categorieDetails) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            categorie.setNomCategorie(categorieDetails.getNomCategorie());
            categorie.setCodeCategorie(categorieDetails.getCodeCategorie());
            categorie.setEvenements(categorieDetails.getEvenements());
            return categorieRepository.save(categorie);
        } else {
            throw new RuntimeException("Categorie not found for id: " + id);
        }
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    public Map<Categorie, List<Evenement>> getEvenementsParCategorie() {
        List<Categorie> categories = categorieRepository.findAll();
        Map<Categorie, List<Evenement>> evenementsParCategorie = new HashMap<>();
        for (Categorie categorie : categories) {
            evenementsParCategorie.put(categorie, categorie.getEvenements());
        }
        return evenementsParCategorie;
    }

    @Scheduled(fixedRate = 15000)
    public void listeEvenementsParCategorie() {
        Map<Categorie, List<Evenement>> evenementsParCategorie = getEvenementsParCategorie();
        evenementsParCategorie.forEach((categorie, evenements) -> {
            System.out.println("Catégorie: " + categorie.getNomCategorie());
            evenements.forEach(evenement -> System.out.println(" - " + evenement.getNomEvenement()));
        });
    }
}