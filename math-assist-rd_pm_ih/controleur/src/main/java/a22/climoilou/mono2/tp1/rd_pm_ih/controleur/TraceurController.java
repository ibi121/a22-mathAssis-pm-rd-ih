package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurGraphique;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurI;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class TraceurController implements TraceurI, Fonctionnalite {
    private List<String> nomSeries;
    private List<HashMap<Double, Double>> serieGraphique;
    private TraceurGraphique traceurGraphique;

    @Autowired
    public void setTraceurGraphique(TraceurGraphique traceurGraphique) {
        this.traceurGraphique = traceurGraphique;
    }

    public void setStage(ConfigurableApplicationContext context, List<Serie> series) throws IOException {
        setSerieGraphique(series);
        setNomSerie(series);
        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView = fxWeaver.load(TraceurGraphique.class);
        Parent root = (AnchorPane) controllerAndView.getView().get();
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Traceur de séries");
        secondaryStage.setScene(new Scene(root));
        secondaryStage.setResizable(false);
        secondaryStage.show();
    }
    public List<HashMap<Double, Double>> getSeries() {
        return serieGraphique;
    }

    /**
     *
     * @return
     */
    public void setSerieGraphique(List<Serie> series) {
        serieGraphique = new ArrayList<>();
        for (Serie serie : series) {
            HashMap<Double, Double> map = new HashMap<>();
            for (Data data : serie.getDonnees()) {
                map.put(data.getX(), data.getY());
            }
            serieGraphique.add(map);
        }
    }

    public List<String> getNomSeries() {
        return nomSeries;
    }

    public void setNomSerie(List<Serie> series) {
        nomSeries = new ArrayList<>();
        for (Serie serie : series) {
            this.nomSeries.add(serie.getNomSerie());
        }
    }

    @Override
    public String getNom() {
        return "Traceur";
    }
}

