package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurGraphique;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurI;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TraceurController implements TraceurI, Fonctionnalite {
    private List<String> nomSeries;
    private List<HashMap<Double, Double>> serieGraphique;
    private TraceurGraphique traceurGraphique;

    private List<Serie> serieOrigine;

    private Stage secondaryStage;

    private SerieService serieService;

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @Autowired
    public void setTraceurGraphique(TraceurGraphique traceurGraphique) {
        this.traceurGraphique = traceurGraphique;
    }

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {

        if (series != null) {
            if (secondaryStage == null) {
                setNomSerie(series);
                setSerieGraphique(series);
                serieOrigine = series;
                FxWeaver fxWeaver = context.getBean(FxWeaver.class);
                FxControllerAndView controllerAndView = fxWeaver.load(TraceurGraphique.class);
                Parent root = (AnchorPane) controllerAndView.getView().get();
                secondaryStage = new Stage();
                secondaryStage.setTitle("Traceur de séries");
                secondaryStage.setScene(new Scene(root));
                secondaryStage.show();

                UIAnimation ui = new UIAnimation();
                ui.deplacerFenetre(secondaryStage, 1, 1, secondaryStage.getWidth(), secondaryStage.getHeight());

            } else {
                setNomSerie(series);
                setSerieGraphique(series);
                serieOrigine = series;
                secondaryStage.show();
                traceurGraphique.resetGraphique();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Traceur information");
            alert.setHeaderText("Selection");
            alert.setContentText("Veuillez selectionner au moins une serie de la liste");
            alert.show();
        }

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

    @Override
    public void setNewData(double x, double y, int indexSerie) {
        //System.out.println(x + " " + y + " " + indexSerie);
        serieOrigine.get(indexSerie).getDonnees().get((int)x).setY(y);
        serieOrigine.get(indexSerie).setDateDerniereModification(LocalDateTime.now());
        serieService.SaveSerie(serieOrigine.get(indexSerie));
    }

    public void setNomSerie(List<Serie> series) {
        nomSeries = series.stream().map(Serie::getNom).collect(Collectors.toList());
    }

    @Override
    public String getNom() {
        return "Traceur";
    }

}

