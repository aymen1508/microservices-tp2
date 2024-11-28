package com.evenement.evenement_microservice.modules.categorie;

import java.util.List;

import com.evenement.evenement_microservice.modules.evenement.Evenement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCategorie;
    String nomCategorie;
    String codeCategorie;
    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("categories")
    private List<Evenement> evenements;
}
