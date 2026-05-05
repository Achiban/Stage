package com.stage.model;

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
@Table(name = "Responsable_Filiere")

public class Responsable_Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_responsable;
    // nom, prénom, grade, email et télephone
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;
    @Column(name = "grade", nullable = false, length = 50)
    private String grade;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "telephone", nullable = false, length = 50)
    private String telephone;
}
