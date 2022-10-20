package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Scope("prototype")
@FxmlView("../vue/monoposte2FXML.fxml")
public class MainController {

    private ConfigurableApplicationContext context;
    private AProposController aProposController;
    private GenerateurController generateurController;
    private ModificateurController modificateurController;

    private EditeurEquationsController editeurEquationsController;

    private TraceurController traceurController;

    private SerieService bd;
    @Autowired
    public void setBd(SerieService bd) {
        this.bd = bd;
    }
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setTraceurController(TraceurController traceurController) {
        this.traceurController = traceurController;
    }

    @FXML
    private ListView<Serie> listViewSeries;

    @FXML
    void aPropos(ActionEvent event) throws IOException {
        aProposController.setStage(this.context);
    }

    @FXML
    void randomSerie(ActionEvent event) throws IOException {
        this.generateurController.setStage(context);
    }

    @FXML
    void validerLaSerie(ActionEvent event) throws IOException {
        if (getSelectedSerie() != null) {
            modificateurController.setStage(context);
        }
    }

    @FXML
    void editeurEquations(ActionEvent event) throws IOException {
        this.editeurEquationsController.setStage(context);
    }

    @FXML
    void conservationSerie(ActionEvent event) {

    }

    @FXML
    void traceurSerie(ActionEvent event) throws IOException {
        if (getAllSeries().size() > 0) {
            traceurController.setStage(context);
        }
    }

    @FXML
    private void initialize() {
        for (Serie s: bd.GetAllSerie()) {
            this.listViewSeries.getItems().add(s);
        }
    }

    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/monoposte2FXML.fxml"));
        return new Scene(fxmlLoader.load());
    }

    /**
     * Prend toutes les séries sélectionnée
     * @return la premiere des séries sélectionnées
     */
    public Serie getSelectedSerie(){
        return listViewSeries.getSelectionModel().getSelectedItems().size() != 0
                ? listViewSeries.getSelectionModel().getSelectedItems().get(0) : null;
    }

    /**
     * Prend toutes les séries sélectionnés
     * @return toutes les séries.
     */
    public List<Serie> getAllSeries(){
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

}

