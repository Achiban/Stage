package com.stage.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Responsable_Entreprise")
public class Responsable_Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsable_entreprise")
    @JsonAlias("id_responsable_entreprise")
    private Long id;

    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public Long getId_responsable_entreprise() {
        return id;
    }

    public void setId_responsable_entreprise(Long idResponsableEntreprise) {
        this.id = idResponsableEntreprise;
    }
}
