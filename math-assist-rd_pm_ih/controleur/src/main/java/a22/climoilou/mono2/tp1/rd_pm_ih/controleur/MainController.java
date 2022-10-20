package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    private SerieService bd;
    @Autowired
    public void setBd(SerieService bd) {
        this.bd = bd;
    }
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @FXML
    private VBox vBox1;

    @FXML
    private Pane paneGauche;

    @FXML
    private HBox hBox1;

    @FXML
    private Button btnAPropos;

    @FXML
    private Pane paneDroit;

    @FXML
    private SplitPane splitPanePrincipal;

    @FXML
    private Button btnValiderSerie;

    @FXML
    private Button btnRandom;

    @FXML
    private Button btnFonctions;

    @FXML
    private Button btnConservation;

    @FXML
    private Button btnTraceur;

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
        modificateurController.setStage();
    }

    @FXML
    void editeurEquations(ActionEvent event) throws IOException {
        this.editeurEquationsController.setStage(context);
    }

    @FXML
    void conservationSerie(ActionEvent event) {

    }

    @FXML
    void traceurSerie(ActionEvent event) {

    }

    @FXML
    private void initialize(){

        for (Serie s: bd.GetAllSerie()
             ) {
            this.listViewSeries.getItems().add(s);
        }

    }

    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/monoposte2FXML.fxml"));
        return new Scene(fxmlLoader.load());
    }

    /**
     * todo retourne la série sélectionnée
     * @return
     */
    public Serie getSelectedSerie(){



        return null;
    }

    /**
     * todo retourne toutes les séries dans la listeView.
     * @return
     */
    public List<Serie> getAllSeries(){


        return null;
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

