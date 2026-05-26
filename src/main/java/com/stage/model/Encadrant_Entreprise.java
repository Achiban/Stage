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
@Table(name = "Encadrant_Entreprise")
public class Encadrant_Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encadrant_entreprise")
    @JsonAlias("id_encadrant_entreprise")
    private Long id;

    private String nom;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telephone;

    public Long getId_encadrant_entreprise() {
        return id;
    }

    public void setId_encadrant_entreprise(Long idEncadrantEntreprise) {
        this.id = idEncadrantEntreprise;
    }
}
