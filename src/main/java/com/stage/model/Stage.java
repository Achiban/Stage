package com.stage.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Stage;

    @Column(name = "sujet", nullable = false, length = 255)
    private String sujet;

    @Column(name = "date_debut", nullable = false, length = 50)
    private Date date_debut;

    @Column(name = "date_fin", nullable = false, length = 50)
    private Date date_fin;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "objectifs", nullable = false, length = 255)
    private String objectifs;

    @Column(name = "solution", nullable = false, length = 255)
    private String solution;
    
    @Column(name = "demarche", nullable = false, length = 255)
    private String demarche;

    @Column(name = "outils", nullable = false, length = 255)
    private String outils;

    @Column(name = "environnement", nullable = false, length = 255)
    private String environnement;
    

}

//• Sujet du stage PFE
//• Période du stage (date début / date fin)
//• Description détaillée du sujet
//• Objectifs du stage
//• Solution
//• Démarche de réalisation du projet
//• Outils et environnement de développement utilisés 
