package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("../vue/Traceur.fxml")
public class TraceurGraphique {


    @FXML
    private LineChart graphiqueSerie;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    private TraceurI traceurI;

    @Autowired
    public void setTraceurI(@Lazy TraceurI traceurI) {
        this.traceurI = traceurI;
    }

    @FXML
    public void initialize() {
        //seriesTest();
        ajoutSeries();
    }

    /**
     * //Test pour recevoir un type de donnee du controleur
     */
    public void ajoutSeries() {
        if (this.traceurI != null) {
            List<HashMap<String, Double>> serieRecu = this.traceurI.getSeries();
            int i = 0;
            for (HashMap<String, Double> serie : serieRecu) {
                XYChart.Series series = new XYChart.Series();
                series.setName(traceurI.getNomSeries().get(i));
                i++;
                for (Map.Entry<String, Double> set : serie.entrySet()) {
                    series.getData().add(new XYChart.Data<String, Number>(set.getKey(), set.getValue()));
                }
                graphiqueSerie.getData().add(series);
            }

        }
    }

    /**
     * //Test pour afficher des series
     */
    public void seriesTest() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Serie 1");

        series.getData().add(new XYChart.Data("0", 0));
        series.getData().add(new XYChart.Data("1", 15));
        series.getData().add(new XYChart.Data("2", 1));
        series.getData().add(new XYChart.Data("3", 5));
        series.getData().add(new XYChart.Data("4", 20));

        graphiqueSerie.getData().add(series);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Serie 2");

        series2.getData().add(new XYChart.Data("0", 0));
        series2.getData().add(new XYChart.Data("1", 1));
        series2.getData().add(new XYChart.Data("2", 2));
        series2.getData().add(new XYChart.Data("3", 3));
        series2.getData().add(new XYChart.Data("4", 4));

        graphiqueSerie.getData().add(series2);
    }
}
