package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public interface TronqueurVueI {
    public List<SortedMap<Double, Double>> getSeries();

    public List<String> getNomSeries();

    public void envoieNouvelleSerie(String nomNouvelleSerie, SortedMap<Double, Double> nouvelleSerie);

    public void close();
}
