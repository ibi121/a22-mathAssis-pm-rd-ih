package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
    private AnchorPane iAmGroot;

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
        graphiqueSerie.prefWidthProperty().bind(iAmGroot.widthProperty());
        graphiqueSerie.prefHeightProperty().bind(iAmGroot.heightProperty());
        y.setAutoRanging(false);
        ajoutSeries();
    }

    public void resetGraphique() {
        graphiqueSerie.getData().clear();
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
                    Data<Number, Number> datanew = new XYChart.Data<Number, Number>(set.getKey(), set.getValue());
                    datanew.setNode(new Rectangle(8, 8));
                    datanew.getNode().setCursor(Cursor.HAND);
                    int finalI = i;
                    datanew.getNode().setOnMouseDragged(e -> {
                        Point2D point = new Point2D(e.getX(), e.getY());
                        double yAxis = y.screenToLocal(point).getY();
                        Number yNum = y.getValueForDisplay(yAxis);
                        datanew.setYValue(yNum);
                        datanew.getNode().setStyle("-fx-fill: red");

                    });
                    datanew.getNode().setOnMouseReleased(e -> {
                        datanew.getNode().setStyle("-fx-fill: blue");
                        traceurI.setNewData(datanew.getXValue().doubleValue(), datanew.getYValue().doubleValue(), finalI - 1);
                    });
                    series.getData().add(datanew);
                }

                graphiqueSerie.getData().add(series);

            }
        }
    }
}
