package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import java.util.HashMap;
import java.util.List;

public interface TraceurI {
    /**
     * //HashMap en attendant d'avoir les modeles
     */
    public List<HashMap<String, Double>> getSeries();
    public List<String> getNomSeries();
}
