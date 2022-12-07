package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Tronqueur;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TronqueurVue;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TronqueurVueI;
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
public class TronqueurController implements TronqueurVueI, Fonctionnalite {

    private List<String> nomSeries;

    private List<HashMap<Double, Double>> listSeries;

    private Stage secondaryStage;

    private SerieService serieService;

    public void setStage(ConfigurableApplicationContext context, Serie s, List<Serie> series) throws IOException {
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

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);
    }

    public List<HashMap<Double, Double>> getSeries() {
        return listSeries;
    }

    /**
     * @return
     */
    public void setSeries(List<Serie> series) {
        listSeries = new ArrayList<>();
        for (Serie serie : series) {
            HashMap<Double, Double> map = new HashMap<>();
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
    public void envoieNouvelleSerie(String nomNouvelleSerie, HashMap<Double, Double> nouvelleSerie) {
        Tronqueur tronqueur = new Tronqueur(nomNouvelleSerie, nouvelleSerie);
        serieService.SaveSerie(tronqueur.getSerie());
        secondaryStage.close();
    }

    public void setNomSerie(List<Serie> series) {
        nomSeries = new ArrayList<>();
        for (Serie serie : series) {
            this.nomSeries.add(serie.getNomSerie());
        }
    }

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @Override
    public String getNom() {
        return "Tronqueur";
    }

}
