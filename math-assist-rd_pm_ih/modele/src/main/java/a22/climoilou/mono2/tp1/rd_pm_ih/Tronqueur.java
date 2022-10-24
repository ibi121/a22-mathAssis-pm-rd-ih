package a22.climoilou.mono2.tp1.rd_pm_ih;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tronqueur {
    private Serie serie;

    public Tronqueur(String nomNouvelleSerie, HashMap<Double, Double> nouvelleSerie) {
        setSerie(nomNouvelleSerie, nouvelleSerie);
    }

    public Serie getSerie() {
        return serie;
    }

    private void setSerie(String nomNouvelleSerie, HashMap<Double, Double> nouvelleSerie) {
        this.serie = new Serie(nomNouvelleSerie);
        List<Data> listData = new ArrayList<>();
        for (Map.Entry<Double, Double> set : nouvelleSerie.entrySet()) {
            listData.add(new Data(set.getKey(), set.getValue()));
        }
        this.serie.setDonnees(listData);
    }
}
