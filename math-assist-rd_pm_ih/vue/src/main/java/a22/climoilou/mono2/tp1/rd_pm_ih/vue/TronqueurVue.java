package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("../vue/Tronqueur.fxml")
public class TronqueurVue {

    private HashMap<Double, Double> nouvelleSerie;

    @FXML
    private ListView<String> listNouvelleSerie;

    @FXML
    private ListView<String> listSerie1;

    @FXML
    private ListView<String> listSerie2;

    @FXML
    private TextField nomNouvelleSerie;

    @FXML
    private Text nomSerie1;

    @FXML
    private Text nomSerie2;

    private TronqueurVueI tronqueurVueI;

    @Autowired
    public void setTronqueurVueI(TronqueurVueI tronqueurVueI) {
        this.tronqueurVueI = tronqueurVueI;
    }

    @FXML
    void afficherNouvelleSerie(ActionEvent event) {
        nouvelleSerie = new HashMap<>();
        HashMap<Double, Double> serie1 = tronqueurVueI.getSeries().get(0);
        HashMap<Double, Double> serie2 = tronqueurVueI.getSeries().get(1);
        int min = Math.min(serie1.size(), serie2.size());
        for (double i = 0; i < min; i++) {
            nouvelleSerie.put(i, ((serie1.get(i) + serie2.get(i)) / 2));
        }
        ajoutSerie(listNouvelleSerie, nouvelleSerie);
    }

    @FXML
    void creerNouvelleSerie(ActionEvent event) {
        if (!nouvelleSerie.isEmpty() && !nomNouvelleSerie.getText().isEmpty()) {
            tronqueurVueI.envoieNouvelleSerie(nomNouvelleSerie.getText(), nouvelleSerie);
            System.out.println("Bonjour");
        } else {
            System.out.println(":o(");
        }
    }

    @FXML
    public void initialize() {
        nomSerie1.setText(tronqueurVueI.getNomSeries().get(0));
        nomSerie2.setText(tronqueurVueI.getNomSeries().get(1));
        ajoutSerie(listSerie1, tronqueurVueI.getSeries().get(0));
        ajoutSerie(listSerie2, tronqueurVueI.getSeries().get(1));
        listSerie1.setMouseTransparent(true);
        listSerie2.setMouseTransparent(true);
        listNouvelleSerie.setMouseTransparent(true);
    }

    private void ajoutSerie(ListView<String> listSerie, HashMap<Double, Double> series) {
        if (this.tronqueurVueI != null) {
            for (Map.Entry<Double, Double> set : series.entrySet()) {
                listSerie.getItems().add("x = " + set.getKey() + " : y = " + set.getValue());
            }
        }
    }
}

