package a22.climoilou.mono2.tp1.rd_pm_ih;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
public class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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

    @javax.persistence.Id
    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
}
