package com.stage.model;

import com.fasterxml.jackson.annotation.JsonAlias;

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
    @Column(name = "id_etudiant")
    @JsonAlias("id_etudiant")
    private Long id;

    // email, CNE, Nom, prénom, télefone
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "CNE", nullable = false, length = 50)
    @JsonAlias("CNE")
    private String cne;
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;
    @Column(name = "telephone", nullable = false, length = 50)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "id_filiere")
    private Filiere filiere;

    public Long getId_etudiant() {
        return id;
    }

    public void setId_etudiant(Long idEtudiant) {
        this.id = idEtudiant;
    }

    public String getCNE() {
        return cne;
    }

    public void setCNE(String cne) {
        this.cne = cne;
    }

}
