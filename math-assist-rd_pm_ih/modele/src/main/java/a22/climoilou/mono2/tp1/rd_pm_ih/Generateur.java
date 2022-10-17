package a22.climoilou.mono2.tp1.rd_pm_ih;

import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.BD;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Generateur {

    private int nombreMinimum;
    private int nombreMaximum;

    private int nombreValeurs;

    private int nbrSeries;
    private String nomSerie;

    private List<Serie> seriesCrees;

    private List<Data> valeurs;


    public Generateur(int nombreMinimum, int nombreMaximum, int nombreValeurs, int nbrSeries, String nomSerie) {
        this.nombreMinimum = nombreMinimum;
        this.nombreMaximum = nombreMaximum;
        this.nombreValeurs = nombreValeurs;
        this.nbrSeries = nbrSeries;
        this.nomSerie = nomSerie;
        this.seriesCrees = new ArrayList<>();
        this.valeurs = new ArrayList<>();
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

    public void creationValeurs(){
        int yAleatoire = 0;
        for(int i = 0; i < this.nombreValeurs; i++){
            yAleatoire = (int) (Math.random() * (this.nombreMaximum - this.nombreMinimum)) + this.nombreMinimum;

            this.valeurs.add(new Data(i + 1, yAleatoire));

        }
    }

    public void creationSeries(){
        Serie s = new Serie(this.nomSerie);
        s.setDonnees(this.valeurs);

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
                '}';
    }
}
