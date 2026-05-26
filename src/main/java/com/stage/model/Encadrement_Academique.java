package com.stage.model;


import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Encadrement_Academique")
public class Encadrement_Academique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encadrement_academique")
    @JsonAlias("id_encadrement_academique")
    private Long id;

    @Column(name = "nom_encadrant_academique")
    private String nomEncadrantAcademique;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private com.stage.model.enums.Type_Encadrement_Academique typeEncadrementAcademique = com.stage.model.enums.Type_Encadrement_Academique.Département;

    public Long getId_encadrement_academique() {
        return id;
    }

    public void setId_encadrement_academique(Long idEncadrementAcademique) {
        this.id = idEncadrementAcademique;
    }

}
//• Nom de l’encadrant académique
//• Département / établissement 
