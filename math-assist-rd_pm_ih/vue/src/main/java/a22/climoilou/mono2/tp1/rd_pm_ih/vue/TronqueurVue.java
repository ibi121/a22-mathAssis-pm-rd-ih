package a22.climoilou.mono2.tp1.rd_pm_ih.vue;

import a22.climoilou.mono2.tp1.rd_pm_ih.vue.services.TronqueurBarService;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.services.TronqueurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@FxmlView("../vue/Tronqueur.fxml")
public class TronqueurVue {

    private SortedMap<Double, Double> nouvelleSerie;

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

    @FXML
    private Button tronquerBtn;

    @FXML
    private Button creerBtn;

    private TronqueurVueI tronqueurVueI;

    @FXML
    private ProgressBar progressBar;

    @Autowired
    public void setTronqueurVueI(TronqueurVueI tronqueurVueI) {
        this.tronqueurVueI = tronqueurVueI;
    }

    @FXML
    void afficherNouvelleSerie(ActionEvent event) {
        listNouvelleSerie.getItems().clear();
        nouvelleSerie = new TreeMap<>();
        TronqueurService tronqueurService = new TronqueurService(tronqueurVueI.getSeries().get(0), tronqueurVueI.getSeries().get(1));
        TronqueurBarService tronqueurBarService = new TronqueurBarService();
        tronqueurBarService.setOnGoing(true);

        progressBar.progressProperty().bind(tronqueurBarService.progressProperty());

        disableUI();

        tronqueurService.messageProperty().addListener((a, o, n) -> {
            listNouvelleSerie.getItems().add(n);
        });

        tronqueurService.setOnSucceeded((e) -> {
            enableUI();
            progressBar.progressProperty().unbind();
            tronqueurBarService.cancel();
            nouvelleSerie = tronqueurService.getValue();
        });

        tronqueurService.start();
    }

    @FXML
    void creerNouvelleSerie(ActionEvent event) {
        try {
            if (!nouvelleSerie.isEmpty() && !nomNouvelleSerie.getText().isEmpty()) {
                tronqueurVueI.envoieNouvelleSerie(nomNouvelleSerie.getText(), nouvelleSerie);
                tronqueurVueI.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tronqueur information");
                alert.setHeaderText("Information");
                alert.setContentText("Veuillez ajouter un nom a la nouvelle série.");
                alert.show();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tronqueur information");
            alert.setHeaderText("Information");
            alert.setContentText("Veuillez tronquer les séries.");
            alert.show();
        }
    }

    public void reset() {
        nomSerie1.setText(tronqueurVueI.getNomSeries().get(0));
        nomSerie2.setText(tronqueurVueI.getNomSeries().get(1));
        listSerie1.getItems().clear();
        listSerie2.getItems().clear();
        ajoutSerie(listSerie1, tronqueurVueI.getSeries().get(0));
        ajoutSerie(listSerie2, tronqueurVueI.getSeries().get(1));
        listNouvelleSerie.getItems().clear();
    }

    @FXML
    public void initialize() {
        nomSerie1.setText(tronqueurVueI.getNomSeries().get(0));
        nomSerie2.setText(tronqueurVueI.getNomSeries().get(1));
        ajoutSerie(listSerie1, tronqueurVueI.getSeries().get(0));
        ajoutSerie(listSerie2, tronqueurVueI.getSeries().get(1));
        listNouvelleSerie.getItems().clear();
    }

    private void ajoutSerie(ListView<String> listSerie, SortedMap<Double, Double> series) {
        if (this.tronqueurVueI != null) {
            listSerie.getItems().clear();
            for (Map.Entry<Double, Double> set : series.entrySet()) {
                listSerie.getItems().add("x = " + set.getKey() + " : y = " + set.getValue());
            }
        }
    }

    private void disableUI() {
        progressBar.setVisible(true);
        creerBtn.setDisable(true);
        tronquerBtn.setDisable(true);
    }

    private void enableUI() {
        progressBar.setVisible(false);
        creerBtn.setDisable(false);
        tronquerBtn.setDisable(false);
    }
}

