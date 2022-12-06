package a22.climoilou.mono2.tp1.rd_pm_ih;

/*
BD :
 Username :soleil1,
 mdp : abc123
 */

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id", nullable = false)
    private Long Id;

    @Enumerated(EnumType.STRING)
    public TypeCreation typeCreation;


    @Builder.Default
    private LocalDateTime dateCreation;

    @Builder.Default
    private LocalDateTime dateDerniereModification;

    private String nomAuteur;

    private String nomSerie;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @CollectionTable
    private List<Data> donnees;

    @OneToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Serie() {
    }

    public Serie(String nomSerie) {

        this.nomSerie = nomSerie;

        donnees = new ArrayList<>();
        dateCreation = LocalDateTime.now();
        dateDerniereModification = dateCreation;
        nomAuteur = "inconnu(e)";
    }

    public Serie(String nomSerie, Categorie categorie) {

        this.nomSerie = nomSerie;
        this.categorie = categorie;
        donnees = new ArrayList<>();
        dateCreation = LocalDateTime.now();
        dateDerniereModification = dateCreation;
        nomAuteur = "inconnu(e)";
    }



    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public Long getId() {
        return Id;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateDerniereModification() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(LocalDateTime dateDerniereModification) {
        this.dateDerniereModification = dateDerniereModification;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public List<Data> getDonnees() {
        return donnees;
    }

    public void setDonnees(List<Data> donnees) {
        this.donnees = donnees;
        this.dateDerniereModification = LocalDateTime.now();
    }

    public void addData(Data... donnees) {
        for (Data donnee : donnees) {
            this.donnees.add(donnee);
        }
    }

    public void setId(Long id) {
        Id = id;
    }

    public TypeCreation getTypeCreation() {
        return typeCreation;
    }

    public void setTypeCreation(TypeCreation typeCreation) {
        this.typeCreation = typeCreation;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    @Override
    public String toString() {
        return nomSerie +"--"+
                " création: " + dateCreation +
                ", modification: " + dateDerniereModification +
                ", nomAuteur: '" + nomAuteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(Id, serie.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }


}
