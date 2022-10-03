package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    private AProposController aProposController;
    private GenerateurController generateurController;
    private ModificateurController modificateurController;


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
    void APropos(ActionEvent event) throws IOException {
        aProposController = new AProposController();
        aProposController.setStage();
    }

    @FXML
    void RandomSerie(ActionEvent event) throws IOException {
        generateurController = new GenerateurController();
        generateurController.setStage();
    }

    @FXML
    void ValiderLaSerie(ActionEvent event) throws IOException {
        modificateurController = new ModificateurController();
        modificateurController.setStage();
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

}

