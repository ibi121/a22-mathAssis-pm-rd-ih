package a22.climoilou.mono2.tp1.rd_pm_ih;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "sous_categorie_id")
    private Categorie sousCategorie;

    public Categorie(String nom, Categorie sousCategorie) {
        this.nom = nom;
        this.sousCategorie = sousCategorie;
    }

    public Categorie() {

    }

    public Categorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(Categorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", sousCategorie=" + sousCategorie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return Objects.equals(id, categorie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
