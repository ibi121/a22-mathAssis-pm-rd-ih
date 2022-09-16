package a22.climoilou.mono2.tps.tp1.model.series;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Serie {

    public enum TypeCreation {ALEATOIRE, EXPRESSION_MATHEMATIQUE}

    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereModification;
    private String nomAuteur;

    private String nomSerie;

    private List<Data> donnees;

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

    @Override
    public String toString() {
        return nomSerie +"--"+
                " création: " + dateCreation +
                ", modification: " + dateDerniereModification +
                ", nomAuteur: '" + nomAuteur;
    }

    public class Data {
        double x;
        double y;

        public Data(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Data() {
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Data data)) return false;
            return Double.compare(data.x, x) == 0 && Double.compare(data.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Data{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }

}
