package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.*;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.services.TronqueurService;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TronqueurVue;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TronqueurVueI;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TronqueurController implements TronqueurVueI, Fonctionnalite {

    private List<String> nomSeries;

    private List<SortedMap<Double, Double>> listSeries;

    private Stage secondaryStage;

    private SerieService serieService;

    private Categorie categorie;

    private TronqueurVue tronqueurVue;

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {
        if (series != null && series.size() > 1) {
            if (secondaryStage == null) {
                categorie = series.get(0).getCategorie();
                setSeries(series);
                setNomSerie(series);
                FxWeaver fxWeaver = context.getBean(FxWeaver.class);
                FxControllerAndView controllerAndView = fxWeaver.load(TronqueurVue.class);
                Parent root = (AnchorPane) controllerAndView.getView().get();
                secondaryStage = new Stage();
                secondaryStage.setTitle("Tronqueur de séries");
                secondaryStage.setScene(new Scene(root));
                secondaryStage.setResizable(false);
                secondaryStage.show();
            } else {
                categorie = series.get(0).getCategorie();
                setSeries(series);
                setNomSerie(series);
                tronqueurVue.reset();
                secondaryStage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tronqueur information");
            alert.setHeaderText("Selection");
            alert.setContentText("Veuillez selectionner deux series de la liste");
            alert.show();
        }
    }

    public List<SortedMap<Double, Double>> getSeries() {
        return listSeries;
    }

    /**
     * @return
     */
    public void setSeries(List<Serie> series) {
        listSeries = new ArrayList<>();
        for (Serie serie : series) {
            SortedMap<Double, Double> map = new TreeMap<>();
            for (Data data : serie.getDonnees()) {
                map.put(data.getX(), data.getY());
            }

            listSeries.add(map);
        }
    }

    public List<String> getNomSeries() {
        return nomSeries;
    }

    @Override
    public void envoieNouvelleSerie(String nomNouvelleSerie, SortedMap<Double, Double> nouvelleSerie) {
        Tronqueur tronqueur = new Tronqueur(nomNouvelleSerie, nouvelleSerie, categorie);
        serieService.SaveSerie(tronqueur.getSerie());
        secondaryStage.close();
    }

    public void setNomSerie(List<Serie> series) {
        nomSeries = series.stream().map(Serie::getNom).collect(Collectors.toList());
    }

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @Autowired
    public void setTronqueurVue(@Lazy TronqueurVue tronqueurVue) {
        this.tronqueurVue = tronqueurVue;
    }

    @Override
    public void close() {
        secondaryStage.hide();
    }

    @Override
    public String getNom() {
        return "Tronqueur";
    }
}
