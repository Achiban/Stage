package com.stage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_etudiant;

    // email, CNE, Nom, prénom, télefone
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "CNE", nullable = false, length = 50)
    private String CNE;
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;
    @Column(name = "telephone", nullable = false, length = 50)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "id_filiere")
    private Filiere filiere;

}
