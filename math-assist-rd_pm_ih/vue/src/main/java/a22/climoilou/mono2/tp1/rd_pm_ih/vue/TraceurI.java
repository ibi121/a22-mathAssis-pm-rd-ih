package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import java.util.HashMap;
import java.util.List;

public interface TraceurI {
    public List<HashMap<Double, Double>> getSeries();

    public List<String> getNomSeries();

    public void setNewData(double x, double y, int i);
}
