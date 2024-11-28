package com.evenement.evenement_microservice.modules.evenement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evenement.evenement_microservice.modules.categorie.CategorieService;
import com.evenement.evenement_microservice.modules.categorie.Categorie;

import java.util.List;




@RestController
@RequestMapping("/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @GetMapping("/{id}")
    public Evenement getEvenementById(@PathVariable Long id) {
        return evenementService.getEvenementById(id);
    }

    @PostMapping
    public Evenement ajouterEvenement(@RequestBody Evenement evenement) {
        return evenementService.createEvenement(evenement);
    }

    @PutMapping("/{id}")
    public Evenement updateEvenement(@PathVariable Long id, @RequestBody Evenement evenement) {
        return evenementService.updateEvenement(id, evenement);
    }

    @DeleteMapping("/{id}")
    public void deleteEvenement(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
    }

    @Autowired
    CategorieService categorieService;
    
    @PostMapping("/{evenementId}/categories/{categorieId}")
    public ResponseEntity<Evenement> addCategoryToEvenement(@PathVariable Long evenementId, @PathVariable Long categorieId) {
        Evenement evenement = evenementService.getEvenementById(evenementId);
        Categorie categorie = categorieService.getCategorieById(categorieId);

        if (evenement!=null && categorie!=null) {
            evenement.getCategories().add(categorie);
            Evenement updatedEvenement = evenementService.updateEvenement(evenementId, evenement);
            return ResponseEntity.ok(updatedEvenement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}