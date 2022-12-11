package a22.climoilou.mono2.tp1.rd_pm_ih;

import java.util.*;
import java.util.stream.Collectors;

public class Tronqueur {
    private Serie serie;

    public Tronqueur(String nomNouvelleSerie, SortedMap<Double, Double> nouvelleSerie, Categorie categorie) {
        setSerie(nomNouvelleSerie, nouvelleSerie, categorie);
    }

    public Serie getSerie() {
        return serie;
    }

    private void setSerie(String nomNouvelleSerie, SortedMap<Double, Double> nouvelleSerie, Categorie categorie) {
        this.serie = new Serie(nomNouvelleSerie);
        List<Data> listData = nouvelleSerie.entrySet().stream().map(doubleDoubleEntry -> {
            return new Data(doubleDoubleEntry.getKey(), doubleDoubleEntry.getValue());
        }).collect(Collectors.toList());
//        for (Map.Entry<Double, Double> set : nouvelleSerie.entrySet()) {
//            listData.add(new Data(set.getKey(), set.getValue()));
//        }
        this.serie.setDonnees(listData);
        this.serie.setCategorie(categorie);
    }
}
