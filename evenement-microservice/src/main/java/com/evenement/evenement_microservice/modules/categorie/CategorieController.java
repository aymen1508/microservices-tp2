package com.evenement.evenement_microservice.modules.categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@RestController
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id);
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    @PutMapping("/{id}")
    public Categorie updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        return categorieService.updateCategorie(id, categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}