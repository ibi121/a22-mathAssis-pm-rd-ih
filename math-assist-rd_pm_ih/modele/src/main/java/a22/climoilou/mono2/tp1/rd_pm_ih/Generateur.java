package a22.climoilou.mono2.tp1.rd_pm_ih;

import java.util.ArrayList;
import java.util.List;

public class Generateur {

    private int nombreMinimum;
    private int nombreMaximum;

    private int nombreValeurs;

    private int nbrSeries;
    private String nomSerie;

    private String nomAuteur;

    private List<Serie> seriesCrees;

    private List<Data> valeurs;
    private Categorie categorie;

    public Generateur(int nombreMinimum, int nombreMaximum, int nombreValeurs, int nbrSeries, String nomSerie, String nomAuteur, Categorie c) {
        this.nombreMinimum = nombreMinimum;
        this.nombreMaximum = nombreMaximum;
        this.nombreValeurs = nombreValeurs;
        this.nbrSeries = nbrSeries;
        this.nomSerie = nomSerie;
        this.nomAuteur = nomAuteur;
        this.seriesCrees = new ArrayList<>();
        this.valeurs = new ArrayList<>();
        this.categorie = c;
    }

    public int getNombreMinimum() {
        return nombreMinimum;
    }

    public void setNombreMinimum(int nombreMinimum) {
        this.nombreMinimum = nombreMinimum;
    }

    public int getNombreMaximum() {
        return nombreMaximum;
    }

    public void setNombreMaximum(int nombreMaximum) {
        this.nombreMaximum = nombreMaximum;
    }

    public int getNombreValeurs() {
        return nombreValeurs;
    }

    public void setNombreValeurs(int nombreValeurs) {
        this.nombreValeurs = nombreValeurs;
    }

    public int getNbrSeries() {
        return nbrSeries;
    }

    public void setNbrSeries(int nbrSeries) {
        this.nbrSeries = nbrSeries;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    public List<Serie> getSeriesCrees() {
        return seriesCrees;
    }

    public void setSeriesCrees(List<Serie> seriesCrees) {
        this.seriesCrees = seriesCrees;
    }

    public List<Data> getValeurs() {
        return valeurs;
    }

    public void setValeurs(List<Data> valeurs) {
        this.valeurs = valeurs;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void creationValeurs() {
        int yAleatoire = 0;
        for (int i = 0; i < this.nombreValeurs; i++) {
            yAleatoire = (int) (Math.random() * (this.nombreMaximum - this.nombreMinimum)) + this.nombreMinimum;

            this.valeurs.add(new Data(i, yAleatoire));
        }
    }

    public void creationSeries() {
        Serie s = new Serie(this.nomSerie);
        s.setNomAuteur(this.nomAuteur);
        s.setDonnees(this.valeurs);
        s.setCategorie(this.categorie);
        this.seriesCrees.add(s);
    }

    @Override
    public String toString() {
        return "Generateur{" +
                "nombreMinimum=" + nombreMinimum +
                ", nombreMaximum=" + nombreMaximum +
                ", nombreValeurs=" + nombreValeurs +
                ", nbrSeries=" + nbrSeries +
                ", nomSerie='" + nomSerie + '\'' +
                ", nomAuteur='" + nomAuteur + '\'' +
                ", seriesCrees=" + seriesCrees +
                ", valeurs=" + valeurs +
                ", categorie=" + categorie +
                '}';
    }
}
