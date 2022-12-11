package a22.climoilou.mono2.tp1.rd_pm_ih.vue.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.SortedMap;
import java.util.TreeMap;

public class TronqueurService extends Service<SortedMap<Double, Double>> {
    private SortedMap<Double, Double> serie1;
    private SortedMap<Double, Double> serie2;

    public TronqueurService(SortedMap<Double, Double> serie1, SortedMap<Double, Double> serie2) {
        super();
        this.serie1 = serie1;
        this.serie2 = serie2;
    }

    @Override
    protected Task<SortedMap<Double, Double>> createTask() {
        return new Task<>() {
            @Override
            protected SortedMap<Double, Double> call() throws Exception {
                return simulateProgress();
            }

            private SortedMap<Double, Double> simulateProgress() {
                SortedMap<Double, Double> nouvelleSerie = new TreeMap<>();
                int minSize = Math.min(serie1.size(), serie2.size());
                for (double i = 0; i < minSize; i += 1) {
                    nouvelleSerie.put(i, ((serie1.get(i) + serie2.get(i)) / 2));
                    updateMessage("x = " + i + " : y = " + (serie1.get(i) + serie2.get(i)) / 2);
                    SlowHelper.slowRandom(500, 1000);
                }

                return nouvelleSerie;
            }
        };
    }
}
