package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
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

import java.io.IOException;
import java.util.List;

public class MainController {

    private AProposController aProposController;
    private GenerateurController generateurController;
    private ModificateurController modificateurController;

    private EditeurEquationsController editeurEquationsController;

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
    private ListView<Serie> listViewSeries;

    @FXML
    void aPropos(ActionEvent event) throws IOException {
        aProposController = new AProposController();
        aProposController.setStage();
    }

    @FXML
    void randomSerie(ActionEvent event) throws IOException {
        generateurController = new GenerateurController();
        generateurController.setStage();
    }

    @FXML
    void validerLaSerie(ActionEvent event) throws IOException {
        modificateurController = new ModificateurController();
        modificateurController.setStage();
    }

    @FXML
    void editeurEquations(ActionEvent event) throws IOException {
        editeurEquationsController = new EditeurEquationsController();
        editeurEquationsController.setStage();
    }


    @FXML
    private void initialize(){
        vBox1 = new VBox();
        paneGauche = new Pane();
        hBox1 = new HBox();
        btnAPropos = new Button();
        paneDroit = new Pane();
        splitPanePrincipal = new SplitPane();
        btnValiderSerie = new Button();
        btnRandom = new Button();
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

    public void setAProposController(AProposController aProposController) {
        this.aProposController = aProposController;
    }

    public void setGenerateurController(GenerateurController generateurController) {
        this.generateurController = generateurController;
    }

    public void setModificateurController(ModificateurController modificateurController) {
        this.modificateurController = modificateurController;
    }



}

