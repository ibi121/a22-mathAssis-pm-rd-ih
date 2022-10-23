package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("../vue/Traceur.fxml")
public class TraceurGraphique {


    @FXML
    private LineChart graphiqueSerie;

    @FXML
    private NumberAxis x;

    @FXML
    private NumberAxis y;

    private TraceurI traceurI;

    @Autowired
    public void setTraceurI(@Lazy TraceurI traceurI) {
        this.traceurI = traceurI;
    }

    @FXML
    public void initialize() {
        ajoutSeries();
    }

    /**
     * //Test pour recevoir un type de donnee du controleur
     */
    public void ajoutSeries() {
        if (this.traceurI != null) {
            List<HashMap<Double, Double>> serieRecu = this.traceurI.getSeries();
            int i = 0;
            for (HashMap<Double, Double> serie : serieRecu) {
                XYChart.Series series = new XYChart.Series();
                series.setName(traceurI.getNomSeries().get(i));
                i++;
                for (Map.Entry<Double, Double> set : serie.entrySet()) {
                    series.getData().add(new XYChart.Data<Number, Number>(set.getKey(), set.getValue()));
                }
                graphiqueSerie.getData().add(series);
            }

        }
    }
}
