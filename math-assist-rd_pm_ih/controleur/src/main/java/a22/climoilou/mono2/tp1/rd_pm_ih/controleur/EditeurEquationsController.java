package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EditeurEquationsController implements Fonctionnalite {

    @FXML
    private Text textSaisie;

    @FXML
    private VBox vbox2;

    @FXML
    private Button btnEffacer;

    @FXML
    private VBox vbox1;

    @FXML
    private Button btnModifier;

    @FXML
    private ListView<?> listViewFonctions;

    @FXML
    private HBox hbox1;

    @FXML
    private Button btnAjout;

    @FXML
    private Pane paneBase;

    @FXML
    private TextField inputEquation;

    @FXML
    private void initialize(){
        btnAjout = new Button();
        btnEffacer = new Button();
        btnModifier = new Button();
    }

    @FXML
    void ajouterEquation(ActionEvent event) {

    }

    @FXML
    void effacerEquation(ActionEvent event) {

    }

    @FXML
    void modifierEquation(ActionEvent event) {

    }

    @Override
    public String getNom() {
        return "Editeur  de fonctions";
    }

    public void setStage() throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Editeur d'equations");
        secondaryStage.setScene(getScene());
        secondaryStage.show();
    }


    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/EditeurEquations.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}
