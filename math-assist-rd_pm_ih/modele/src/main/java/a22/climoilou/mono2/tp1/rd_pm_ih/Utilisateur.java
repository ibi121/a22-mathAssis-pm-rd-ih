package a22.climoilou.mono2.tp1.rd_pm_ih;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Utilisateur {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String Prenom;
    private String nom;
    private String courriel;
    private String recommendationl;

    public Utilisateur(String prenom, String nom, String courriel, String recommendationl) {
        Prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.recommendationl = recommendationl;
    }

    public Utilisateur() {
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setRecommendationl(String recommendationl) {
        this.recommendationl = recommendationl;
    }


    public String getPrenom() {
        return Prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getRecommendationl() {
        return recommendationl;
    }


    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return recommendationl + "De  : " + nom;
    }



}
