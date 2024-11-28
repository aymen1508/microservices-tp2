package com.evenement.evenement_microservice.modules.evenement;

import java.sql.Date;
import java.util.List;

import com.evenement.evenement_microservice.modules.categorie.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEvenement;
    String nomEvenement;
    Long nbPlacesRestants;
    Date dateEvenement;
    
    @ManyToMany
    @JoinTable(
        name = "categories_evenements",
        joinColumns = @JoinColumn(name = "id_evenement"),
        inverseJoinColumns = @JoinColumn(name = "id_categorie")
    )
    @JsonIgnoreProperties("evenements")
    private List<Categorie> categories;
}
