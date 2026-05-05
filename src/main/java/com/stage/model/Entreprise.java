package com.stage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_entreprise;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    
    @Column(name = "adresse", nullable = false, length = 255)
    private String adresse;

    @Column(name = "telephone", nullable = false, length = 50)
    private String telephone;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "ville", nullable = false, length = 50)
    private String ville;   
    @Column(name = "pays", nullable = false, length = 50)
    private String pays;

    @OneToOne
    @JoinColumn(name = "id_responsable_entreprise")
    private Responsable_Entreprise responsableEntreprise;

    @OneToOne
    @JoinColumn(name = "id_encadrant_entreprise")
    private Encadrant_Entreprise encadrantEntreprise;
}
//• Nom de l’entreprise
//• Coordonnées (adresse, téléphone, email)
//• Lieu (ville, pays)
//• Responsable de l’entreprise (email)
//• Encadrant en entreprise (email et tel)