package a22.climoilou.mono2.tp1.rd_pm_ih;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Serie {
    public enum TypeCreation {ALEATOIRE, EXPRESSION_MATHEMATIQUE}

    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereModification;
    private String nomAuteur;

    private String nomSerie;

    private List<a22.climoilou.mono2.tp1.rd_pm_ih.origine.Serie.Data> donnees;

    public Serie(String nomSerie) {

        this.nomSerie = nomSerie;

        donnees = new ArrayList<>();
        dateCreation = LocalDateTime.now();
        dateDerniereModification = dateCreation;
        nomAuteur = "inconnu(e)";
    }


    public LocalDateTime getDateCreation() {
        return dateCreation;
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

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public List<a22.climoilou.mono2.tp1.rd_pm_ih.origine.Serie.Data> getDonnees() {
        return donnees;
    }

    public void setDonnees(List<a22.climoilou.mono2.tp1.rd_pm_ih.origine.Serie.Data> donnees) {
        this.donnees = donnees;
        this.dateDerniereModification = LocalDateTime.now();
    }

    public void addData(a22.climoilou.mono2.tp1.rd_pm_ih.origine.Serie.Data... donnees) {
        for (a22.climoilou.mono2.tp1.rd_pm_ih.origine.Serie.Data donnee : donnees) {
            this.donnees.add(donnee);
        }
    }

    @Override
    public String toString() {
        return nomSerie +"--"+
                " création: " + dateCreation +
                ", modification: " + dateDerniereModification +
                ", nomAuteur: '" + nomAuteur;
    }
}
