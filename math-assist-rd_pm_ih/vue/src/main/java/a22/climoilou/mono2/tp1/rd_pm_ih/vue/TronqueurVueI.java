package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import java.util.HashMap;
import java.util.List;

public interface TronqueurVueI {
    public List<HashMap<Double, Double>> getSeries();

    public List<String> getNomSeries();

    public void envoieNouvelleSerie(String nomNouvelleSerie, HashMap<Double, Double> nouvelleSerie);
}
