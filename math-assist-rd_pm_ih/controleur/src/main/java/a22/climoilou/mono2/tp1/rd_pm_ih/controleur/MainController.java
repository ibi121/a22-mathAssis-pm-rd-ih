package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@FxmlView("../vue/monoposte2FXML.fxml")
public class MainController {

    @FXML
    public Button btnSupprimer;
    public Button FunctionIbrahim;
    @FXML
    private Button btnAPropos;

    @FXML
    private Button btnConservation;

    @FXML
    private Button btnFonctionRD;

    @FXML
    private Button btnFonctions;

    @FXML
    private Button btnRandom;

    @FXML
    private Button btnTraceur;

    @FXML
    private Button btnValiderSerie;


    private ConfigurableApplicationContext context;
    private AProposController aProposController;
    private GenerateurController generateurController;
    private ModificateurController modificateurController;


    private EditeurEquationsController editeurEquationsController;

    private TraceurController traceurController;

    private TableauDesValeursController tableauDesValeursController;

    private AvisController avisController;

    private SerieService bd;

    @Autowired
    public void setBd(SerieService bd) {
        this.bd = bd;
    }

    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @FXML
    private ListView<Serie> listViewSeries;

    @FXML
    public void OuvrirCommentaire(ActionEvent actionEvent) throws IOException {
        avisController.setStage(this.context);
    }

    @FXML
    void aPropos(ActionEvent event) throws IOException {
        btnAPropos.setText(aProposController.getNom());
        aProposController.setStage(this.context);
    }

    @FXML
    void randomSerie(ActionEvent event) throws IOException {
        this.generateurController.setStage(context);
    }

    @FXML
    void validerLaSerie(ActionEvent event) throws IOException {
        if (getSelectedSerie() != null) {
            Serie serie = getSelectedSerie();
            modificateurController.setStage(context, serie);
        }
    }

    @FXML
    void editeurEquations(ActionEvent event) throws IOException {
        this.editeurEquationsController.setStage(context);
    }


    @FXML
    void traceurSerie(ActionEvent event) throws IOException {
        if (getAllSeries() != null) {
            List<Serie> series = getAllSeries();
            traceurController.setStage(context, series);
        }

    }

    @FXML
    void creationTableauValeurs(ActionEvent actionEvent) {
        if (getSelectedSerie() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Vous devez choisir un série pour visionner ces données.");
            alert.show();
        } else {
            Serie serie = getSelectedSerie();
            this.tableauDesValeursController.setStage(context, serie);
        }

    }

    @FXML
    private void initialize() {
        for (Serie s : bd.GetAllSerie()) {
            this.listViewSeries.getItems().add(s);
        }
        listViewSeries.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }



    @Autowired
    public void setAvisController(AvisController avisController) {
        this.avisController = avisController;
    }

    /**
     * Prends un ou des elements de la liste de serie et les supprime de ls BD
     *
     * @param actionEvent
     */

    @FXML
    public void supprimerSerie(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.NONE);

        ObservableList<Serie> listeDeSerie = this.listViewSeries.getSelectionModel().getSelectedItems();

        if (listeDeSerie.isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("attention vous n'avez pas selectionné de Serie!");
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Etes vous sure de vouloir supprimer la serie : ");
            alert.showAndWait().ifPresent(reponse -> {
                if (reponse == ButtonType.OK) {
                    for (Serie serie : listeDeSerie) {
                        bd.SupprimerSerie(serie);
                    }
                }
            });
        }

        List<Serie> listView = new ArrayList<>();
        listView.addAll(listViewSeries.getItems());

        List<Serie> seriesBd = new ArrayList<>();
        seriesBd.addAll(bd.GetAllSerie());

        if (!seriesBd.containsAll(listView)) {
            this.listViewSeries.getItems().removeAll(listView);
            for (Serie s : bd.GetAllSerie()) {
                this.listViewSeries.getItems().add(s);
            }

        }

    }

    @FXML
    public void refreshTable(MouseEvent mouseEvent) {
        List<Serie> listView = new ArrayList<>();
        listView.addAll(listViewSeries.getItems());

        List<Serie> seriesBd = new ArrayList<>();
        seriesBd.addAll(bd.GetAllSerie());

        if (!listView.containsAll(seriesBd)) {
            this.listViewSeries.getItems().removeAll(listView);
            for (Serie s : bd.GetAllSerie()) {
                this.listViewSeries.getItems().add(s);
            }

        }

    }


    /**
     * Prend toutes les séries sélectionnée
     *
     * @return la premiere des séries sélectionnées
     */
    public Serie getSelectedSerie() {
        return listViewSeries.getSelectionModel().getSelectedItems().size() != 0
                ? listViewSeries.getSelectionModel().getSelectedItems().get(0) : null;
    }

    /**
     * Prend toutes les séries sélectionnés
     *
     * @return toutes les séries.
     */
    public List<Serie> getAllSeries() {
        return listViewSeries.getSelectionModel().getSelectedItems().size() != 0
                ? listViewSeries.getSelectionModel().getSelectedItems() : null;
    }

    @Autowired
    public void setAProposController(AProposController aProposController) {
        this.aProposController = aProposController;
    }

    @Autowired
    public void setGenerateurController(GenerateurController generateurController) {
        this.generateurController = generateurController;
    }

    @Autowired
    public void setModificateurController(ModificateurController modificateurController) {
        this.modificateurController = modificateurController;
    }

    @Autowired
    public void setEditeurController(EditeurEquationsController editeurEquationsController) {
        this.editeurEquationsController = editeurEquationsController;
    }

    @Autowired
    public void setTraceurController(TraceurController traceurController) {
        this.traceurController = traceurController;
    }

    @Autowired
    public void setTableauDesValeursController(TableauDesValeursController tableauDesValeursController) {
        this.tableauDesValeursController = tableauDesValeursController;
    }



}

