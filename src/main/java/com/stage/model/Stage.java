package com.stage.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Stage")
    @JsonAlias("Id_Stage")
    private Long id;

    @Column(name = "sujet", nullable = false, length = 255)
    private String sujet;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_debut", nullable = false)
    private Date date_debut;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_fin", nullable = false)
    private Date date_fin;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "objectifs", nullable = false, length = 1000)
    private String objectifs;

    @Column(name = "solution", nullable = false, length = 1000)
    private String solution;

    @Column(name = "demarche", nullable = false, length = 1000)
    private String demarche;

    @Column(name = "outils", nullable = false, length = 500)
    private String outils;

    @Column(name = "environnement", nullable = false, length = 500)
    private String environnement;

    @Column(name = "annee_universitaire", nullable = false, length = 20)
    private String anneeUniversitaire;

    @ManyToOne
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_entreprise", nullable = false)
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "id_encadrement_academique", nullable = false)
    private Encadrement_Academique encadrementAcademique;

    @ManyToOne
    @JoinColumn(name = "id_encadrant_entreprise", nullable = false)
    private Encadrant_Entreprise encadrantEntreprise;

    public Long getId_Stage() {
        return id;
    }

    public void setId_Stage(Long idStage) {
        this.id = idStage;
    }
}

//• Sujet du stage PFE
//• Période du stage (date début / date fin)
//• Description détaillée du sujet
//• Objectifs du stage
//• Solution
//• Démarche de réalisation du projet
//• Outils et environnement de développement utilisés 
